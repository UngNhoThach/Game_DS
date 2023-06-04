package GUI;

import static Utils.Class.HOME_SELECTION;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.io.IOException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

public class EditInforForm extends JFrame {

    private Client client;
    private Date date = new Date();
    int posX, posY;
    int widthLeft;
    private JLabel lbl_IconDeliveryman;
    private JTextField txtName;
    private JButton btnUpdateInfor, btnBack;
    private JLabel lblGender;
    private JComboBox GenderBox;
    private JLabel lblName, lblMtKhu;
    private JLabel lblDoB;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private JDatePickerImpl datePickerStart;
    private JPanel contentPane;
    private JPasswordField txt_pass;

    public int check(String name, String dob) {
        if (name.equals("") || dob.equals("")) {
            return 0;
        } else {
            return 1;
        }
    }

    public void loadInfoInEdit(String info) throws IOException {
        String[] detail = info.split("\\]\\:\\$\\:\\[");
        if (detail[0].equals("UserInfo")) {
            txtName.setText(detail[1]);
            GenderBox.setSelectedItem(detail[4]);
            // datePicker.se
        } else {
            JOptionPane.showMessageDialog(contentPane, "Opps! Show information fail! ", "Error Message", JOptionPane.ERROR_MESSAGE);
            txtName.setText("");
            GenderBox.setSelectedItem(1);
        }
    }

    public EditInforForm(Client client) {
        this.client = client;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(50, 100, 633, 350);
        setUndecorated(true);
        setLocationRelativeTo(null);
        initComponens();
        setEnabled(true);
    }

    private void initComponens() {
        contentPane = new JPanel();
        contentPane.setLayout(null);
        setContentPane(contentPane);
        JPanel pnHeader = new JPanel();
        pnHeader.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 128, 128)));
        pnHeader.setBounds(0, 0, 633, 38);
        getContentPane().add(pnHeader);

        JLabel lblQunLKhch = new JLabel("QUẢN LÝ THÔNG TIN");
        lblQunLKhch.setHorizontalAlignment(SwingConstants.CENTER);
        lblQunLKhch.setForeground(new Color(0, 128, 128));
        lblQunLKhch.setFont(new Font("Dialog", Font.BOLD, 15));
        pnHeader.add(lblQunLKhch);

        btnBack = new JButton("Quay lại");
        btnBack.setIcon(new ImageIcon(RegisterForm.class.getResource("/images/left-arrow.png")));
        btnBack.setForeground(new Color(255, 255, 225));
        btnBack.setBackground(new Color(225, 225, 225));
        btnBack.setBounds(10, 306, 127, 30);
        btnBack.addActionListener((ActionEvent e) -> {
            System.out.println("button back clieck");
            HOME_SELECTION = new HomeSelection(client);
            HOME_SELECTION.setVisible(true);
            closeThisWindow();
        });
        contentPane.add(btnBack);

        txtName = new JTextField();
        txtName.setOpaque(false);
        txtName.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        txtName.setDisabledTextColor(new Color(0, 139, 139));
        txtName.setCaretColor(new Color(255, 255, 255));
        txtName.setBounds(257, 82, 179, 30);
        txtName.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
                changed();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                changed();
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                changed();
            }

            public void changed() {
                if (txtName.getText().equals("")) {
                    btnUpdateInfor.setEnabled(false);
                } else {
                    btnUpdateInfor.setEnabled(true);
                }
            }
        });
        txtName.setColumns(10);
        contentPane.add(txtName);

        lblName = new JLabel("*Tên:");
        lblName.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        lblName.setFont(new Font("Segoe UI", Font.PLAIN, 17));
        lblName.setForeground(new Color(255, 255, 255));
        lblName.setBounds(132, 88, 64, 16);
        contentPane.add(lblName);

        lblGender = new JLabel("Giới tính:");
        lblGender.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        lblGender.setFont(new Font("Segoe UI", Font.PLAIN, 17));
        lblGender.setForeground(new Color(255, 255, 255));
        lblGender.setBounds(132, 210, 80, 16);
        contentPane.add(lblGender);

        GenderBox = new JComboBox();
        GenderBox.setOpaque(false);
        GenderBox.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        GenderBox.setBounds(258, 204, 178, 28);
        GenderBox.setModel(new DefaultComboBoxModel(new String[]{"Female", "Male", "Other"}));
        contentPane.add(GenderBox);

        lblDoB = new JLabel("*Sinh nhật:");
        lblDoB.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        lblDoB.setFont(new Font("Segoe UI", Font.PLAIN, 17));
        lblDoB.setForeground(new Color(255, 255, 255));
        lblDoB.setBounds(132, 173, 84, 16);
        contentPane.add(lblDoB);

        UtilDateModel modeStart = new UtilDateModel();
        JDatePanelImpl datePanelStart = new JDatePanelImpl(modeStart);
        datePickerStart = new JDatePickerImpl(datePanelStart);
        datePickerStart.setBounds(258, 164, 178, 28);
        contentPane.add(datePickerStart);

        lblMtKhu = new JLabel("M\u1EADt kh\u1EA9u");
        lblMtKhu.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        lblMtKhu.setForeground(new Color(255, 255, 255));
        lblMtKhu.setFont(new Font("Segoe UI", Font.PLAIN, 17));
        lblMtKhu.setBounds(132, 121, 80, 25);
        contentPane.add(lblMtKhu);

        txt_pass = new JPasswordField();
        txt_pass.setOpaque(false);
        txt_pass.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        txt_pass.setDisabledTextColor(new Color(0, 139, 139));
        txt_pass.setCaretColor(new Color(255, 255, 255));
        txt_pass.setBounds(257, 123, 179, 30);
        txt_pass.setColumns(10);
        contentPane.add(txt_pass);

        btnUpdateInfor = new JButton("Sửa thông tin");
        btnUpdateInfor.setForeground(new Color(255, 255, 255));
        btnUpdateInfor.setIcon(new ImageIcon(RegisterForm.class.getResource("/images/edit.png")));
        btnUpdateInfor.setBackground(new Color(225, 225, 225));
        btnUpdateInfor.setBounds(489, 306, 134, 30);
        btnUpdateInfor.addActionListener((ActionEvent e) -> {
            try {
                Date startdate = (Date) datePickerStart.getModel().getValue();
                Date now = new Date(System.currentTimeMillis());
                DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                String reportDate = df.format(startdate);
                boolean flag = false;
                String gender = String.valueOf(GenderBox.getSelectedItem());
                if (check(txtName.getText(), reportDate) == 0) {
                    JOptionPane.showMessageDialog(contentPane, "You must fill out all the field. Try again.", "Error Message", JOptionPane.ERROR_MESSAGE);
                } else {
                    client.editInfo(txtName.getText(), gender, reportDate, txt_pass.getPassword());
                }
            } catch (HeadlessException e1) {
                JOptionPane.showMessageDialog(contentPane, "Something wrong!! Please try later", "Error Message", JOptionPane.ERROR_MESSAGE);
            }
        });
        contentPane.add(btnUpdateInfor);

        lbl_IconDeliveryman = new JLabel("Quay lại");
        lbl_IconDeliveryman.setHorizontalAlignment(SwingConstants.LEFT);
        lbl_IconDeliveryman.setIcon(new ImageIcon(LoginForm.class.getResource("/images/background_home.png")));
        lbl_IconDeliveryman.setBounds(0, 0, 633, 350);
        contentPane.add(lbl_IconDeliveryman);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                try {
                    client.getInfoOfUser();
                } catch (IOException ex) {
                    Logger.getLogger(EditInforForm.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

    }

    public void EditInfoSuccess() {
        JOptionPane.showMessageDialog(contentPane, "Edit successfully.", "Information", JOptionPane.INFORMATION_MESSAGE);
        dispose();
    }

    public void EditInfoFail() {
        JOptionPane.showMessageDialog(contentPane, "Something wrong!! Please try later", "Error Message", JOptionPane.ERROR_MESSAGE);
    }

    public void closeThisWindow() {
        this.dispose();
    }
}
