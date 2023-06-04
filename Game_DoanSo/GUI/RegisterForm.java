package GUI;

import static Utils.Class.HOME_SELECTION;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

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
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

public class RegisterForm extends JFrame {

    public Client client;
    private JPanel contentPane;
    int posX, posY;
    int widthLeft;
    private JLabel lbl_IconDeliveryman;
    private JTextField emailField;
    private JLabel lblPassword;
    private JPasswordField passwordField;
    private JLabel lblConfirm;
    private JPasswordField confirmField;
    private JButton btnSignUp, btnBack;
    private JLabel lblGender;
    private JComboBox GenderBox;
    private JLabel lblName;
    private JTextField nameField;
    private JLabel lblDoB;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private JDatePickerImpl datePickerStart;
    public String mailregex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";

    public RegisterForm(Client client) {
        this.client = client;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(50, 100, 633, 350);
        setUndecorated(true);
        setLocationRelativeTo(null);
        initComponens(client); // initComponens
//		addEvents();
    }

    private void initComponens(Client client) {
        contentPane = new JPanel();
        contentPane.setLayout(null);
        setContentPane(contentPane);

        btnBack = new JButton("Quay lại");
        btnBack.setIcon(new ImageIcon(RegisterForm.class.getResource("/images/left-arrow.png")));
        btnBack.setForeground(new Color(255, 255, 225));
        btnBack.setBackground(new Color(225, 225, 225));
        btnBack.setBounds(27, 300, 125, 30);
        btnBack.addActionListener((ActionEvent e) -> {
            Home h = new Home(client);
            h.setVisible(true);
            closeThisWindow();
        });
        contentPane.add(btnBack);

        JLabel lblSignUp = new JLabel("SIGN UP");
        lblSignUp.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        lblSignUp.setForeground(Color.WHITE);
        lblSignUp.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        lblSignUp.setBounds(129, 10, 350, 30);
        lblSignUp.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(lblSignUp);

        JLabel lblEmail = new JLabel("*Email:");
        lblEmail.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        lblEmail.setFont(new Font("Segoe UI", Font.PLAIN, 17));
        lblEmail.setForeground(new Color(255, 255, 255));
        lblEmail.setBounds(139, 47, 70, 16);
        contentPane.add(lblEmail);

        emailField = new JTextField();
        emailField.setOpaque(false);
        emailField.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        emailField.setDisabledTextColor(new Color(0, 139, 139));
        emailField.setCaretColor(new Color(255, 255, 255));
        emailField.setBounds(220, 41, 206, 30);
        emailField.getDocument().addDocumentListener(new DocumentListener() {
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
                if (emailField.getText().equals("")) {
                    btnSignUp.setEnabled(false);
                } else {
                    btnSignUp.setEnabled(true);
                    lblEmail.setForeground(new Color(0, 255, 127));
                }
            }
        });
        contentPane.add(emailField);
        emailField.setColumns(10);

        lblPassword = new JLabel("*Mật Khẩu:");
        lblPassword.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        lblPassword.setFont(new Font("Segoe UI", Font.PLAIN, 17));
        lblPassword.setForeground(new Color(255, 255, 255));
        lblPassword.setBounds(118, 129, 92, 16);
        contentPane.add(lblPassword);

        passwordField = new JPasswordField();
        passwordField.setOpaque(false);
        passwordField.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        passwordField.setDisabledTextColor(new Color(0, 139, 139));
        passwordField.setCaretColor(new Color(255, 255, 255));
        passwordField.setBounds(220, 123, 206, 30);
        passwordField.setColumns(10);
        contentPane.add(passwordField);

        lblConfirm = new JLabel("*Nhập lại mật khẩu:");
        lblConfirm.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        lblConfirm.setFont(new Font("Segoe UI", Font.PLAIN, 17));
        lblConfirm.setForeground(new Color(255, 255, 255));
        lblConfirm.setBounds(90, 166, 120, 16);

        contentPane.add(lblConfirm);

        confirmField = new JPasswordField();
        confirmField.setOpaque(false);
        confirmField.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        confirmField.setDisabledTextColor(new Color(0, 139, 139));
        confirmField.setCaretColor(new Color(255, 255, 255));
        confirmField.setBounds(220, 160, 206, 30);
        confirmField.setColumns(10);
        contentPane.add(confirmField);

        lblName = new JLabel("*Tên:");
        lblName.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        lblName.setFont(new Font("Segoe UI", Font.PLAIN, 17));
        lblName.setForeground(new Color(255, 255, 255));
        lblName.setBounds(146, 88, 64, 16);
        contentPane.add(lblName);

        nameField = new JTextField();
        nameField.setOpaque(false);
        nameField.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        nameField.setDisabledTextColor(new Color(0, 139, 139));
        nameField.setCaretColor(new Color(255, 255, 255));
        nameField.setBounds(220, 82, 206, 30);
        nameField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (nameField.getText().equals("")) {
                    btnSignUp.setEnabled(false);
                } else {
                    btnSignUp.setEnabled(true);
                }
            }
        });
        contentPane.add(nameField);
        nameField.setColumns(10);

        lblGender = new JLabel("Giới tính:");
        lblGender.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        lblGender.setFont(new Font("Segoe UI", Font.PLAIN, 17));
        lblGender.setForeground(new Color(255, 255, 255));
        lblGender.setBounds(129, 245, 80, 16);
        contentPane.add(lblGender);

        GenderBox = new JComboBox();
        GenderBox.setOpaque(false);
        GenderBox.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        GenderBox.setBounds(220, 239, 206, 28);
        GenderBox.setModel(new DefaultComboBoxModel(new String[]{"Female", "Male", "Other"}));
        contentPane.add(GenderBox);

        lblDoB = new JLabel("*Sinh nhật:");
        lblDoB.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        lblDoB.setFont(new Font("Segoe UI", Font.PLAIN, 17));
        lblDoB.setForeground(new Color(255, 255, 255));
        lblDoB.setBounds(109, 201, 100, 16);
        contentPane.add(lblDoB);

        UtilDateModel modeStart = new UtilDateModel();
        JDatePanelImpl datePanelStart = new JDatePanelImpl(modeStart);
        datePickerStart = new JDatePickerImpl(datePanelStart);
        datePickerStart.setBounds(220, 201, 206, 28);
        contentPane.add(datePickerStart);

        btnSignUp = new JButton("Đăng ký");
        btnSignUp.setForeground(new Color(255, 255, 255));
        btnSignUp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnSignUp.setBackground(new Color(51, 51, 102));
        btnSignUp.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnSignUp.setBounds(249, 278, 150, 40);
        btnSignUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Date startdate = (Date) datePickerStart.getModel().getValue();
                    Date now = new Date(System.currentTimeMillis());
                    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                    String reportDate = df.format(startdate);
                    int check = check(emailField.getText(), passwordField.getPassword(), confirmField.getPassword(), nameField.getText(), reportDate);
                    int checkDate = now.compareTo(startdate);
                    boolean flag = false;
                    String gender = String.valueOf(GenderBox.getSelectedItem());

                    if (check == 0) {
                        JOptionPane.showMessageDialog(contentPane, "Bạn phải nhập tất cả các thông tin ở trên!!!", "Error Message", JOptionPane.ERROR_MESSAGE);
                    } else if (check == 2) {
                        JOptionPane.showMessageDialog(contentPane, "Password sai!!!", "Error Message", JOptionPane.ERROR_MESSAGE);
                    } else if (check == 3) {
                        JOptionPane.showMessageDialog(contentPane, "Email không hợp lệ!!!", "Error Message", JOptionPane.ERROR_MESSAGE);
                    } else if (checkDate < 0) {
                        JOptionPane.showMessageDialog(contentPane, "Ngày sinh không hợp lệ!!!", "Error Message", JOptionPane.ERROR_MESSAGE);
                    } else if (checkSpecialChar(nameField.getText()) == false) {
                        JOptionPane.showMessageDialog(contentPane, "Tên không hợp lệ!!!", "Error Message", JOptionPane.ERROR_MESSAGE);
                    } else {
                        flag = true;
                    }

                    if (flag == true) {
                        client.signup(emailField.getText(), passwordField.getPassword(), nameField.getText(), gender, reportDate);
                    }

                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(contentPane, "Bạn phải nhập đầy đủ thông tin", "Error Message", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        contentPane.add(btnSignUp);
        lbl_IconDeliveryman = new JLabel("");
        lbl_IconDeliveryman.setHorizontalAlignment(SwingConstants.LEFT);
        lbl_IconDeliveryman.setIcon(new ImageIcon(RegisterForm.class.getResource("/images/background_home.png")));
        lbl_IconDeliveryman.setBounds(0, 0, 633, 350);
        contentPane.add(lbl_IconDeliveryman);
        widthLeft = (int) contentPane.getPreferredSize().getWidth();
    }

    public int check(String email, char[] passwd, char[] confirm, String name, String dob) {
        if (email.equals("") || passwd.toString().equals("") || confirm.toString().equals("") || name.equals("")
                || dob.equals("")) {
            return 0;
        } else if (!Arrays.equals(passwd, confirm)) {
            return 2;
        } else if (!email.matches(mailregex)) {
            return 3;
        } else {
            return 1;
        }
    }

    public static boolean checkSpecialChar(String s) {
        boolean kq = false;
        String patt = "[^\\/\\*\\+\\.\\;\\=\\_\\}\\{\\:\\?\\>\\<\\!\\@\\#\\$\\%\\^\\&\\*\\(\\)\\`\\~\\[\\]]+";
        String patt2 = "\\D+";
        if (s.matches(patt) && s.matches(patt2) && s.equals(s.trim())) {
            kq = true; // false là có chứa kí tự đặc biệt
        }
        return kq;
    }

    public void EmailHopLe() {
        HOME_SELECTION = new HomeSelection(client);
        HOME_SELECTION.setVisible(true);
        HOME_SELECTION.setEnabled(true);
        closeThisWindow();
    }

    public void EmailKhongHopLe() {
        JOptionPane.showMessageDialog(contentPane, "Email đã có người đăng ký. Mời bạn nhập email khác", "Error Message", JOptionPane.ERROR_MESSAGE);
    }

    public void SignUpFail() {
        JOptionPane.showMessageDialog(contentPane, "Đăng ký thất bại. Vui lòng thử lại", "Error Message", JOptionPane.ERROR_MESSAGE);

    }

    public void closeThisWindow() {
        this.dispose();
    }
}
