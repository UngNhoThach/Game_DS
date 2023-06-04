package GUI;

import static Utils.Class.HOME;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.Font;
import java.awt.Cursor;
import java.awt.event.ActionEvent;

public class LoginForm extends JFrame {

    int posX, posY;
    int widthLeft;
    private JPanel contentPane;
    JPanel pnHome;
    private JLabel lblNewLabel, lblMtKhu;
    private JPasswordField txt_pass;
    private JLabel lbl_IconDeliveryman;
    private JButton btnLogin, btnBack;
    private JTextField txt_username;
    public Client client;
    static public String mailregex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";

    public LoginForm(Client client) {
        this.client = client;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(50, 100, 633, 350);
        setUndecorated(true);
        setLocationRelativeTo(null);
        initComponens(client);
    }

    private void initComponens(Client client) {
        contentPane = new JPanel();
        contentPane.setLayout(null);
        setContentPane(contentPane);

        btnBack = new JButton("Quay lại");
        btnBack.setIcon(new ImageIcon(LoginForm.class.getResource("/images/left-arrow.png")));
        btnBack.setForeground(new Color(255, 255, 225));
        btnBack.setBackground(new Color(225, 225, 225));
        btnBack.setBounds(27, 300, 125, 30);
        btnBack.addActionListener((ActionEvent e) -> {
            HOME = new Home(client);
            HOME.setVisible(true);
            closeThisWindow();
        });
        contentPane.add(btnBack);

        JLabel lblSignUp = new JLabel("SIGN IN");
        lblSignUp.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        lblSignUp.setForeground(Color.WHITE);
        lblSignUp.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        lblSignUp.setBounds(129, 10, 350, 30);
        lblSignUp.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(lblSignUp);

        lblNewLabel = new JLabel("Email");
        lblNewLabel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        lblNewLabel.setForeground(Color.WHITE);
        lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 17));
        lblNewLabel.setBounds(210, 38, 100, 25);
        contentPane.add(lblNewLabel);

        txt_username = new JTextField();
        txt_username.setOpaque(false);
        txt_username.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        txt_username.setDisabledTextColor(new Color(0, 139, 139));
        txt_username.setCaretColor(new Color(255, 255, 255));
        txt_username.setBounds(210, 63, 206, 30);
        txt_username.setColumns(10);
        contentPane.add(txt_username);

        lblMtKhu = new JLabel("M\u1EADt kh\u1EA9u");
        lblMtKhu.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        lblMtKhu.setForeground(new Color(255, 255, 255));
        lblMtKhu.setFont(new Font("Segoe UI", Font.PLAIN, 17));
        lblMtKhu.setBounds(210, 107, 206, 25);
        contentPane.add(lblMtKhu);

        txt_pass = new JPasswordField();
        txt_pass.setOpaque(false);
        txt_pass.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        txt_pass.setDisabledTextColor(new Color(0, 139, 139));
        txt_pass.setCaretColor(new Color(255, 255, 255));
        txt_pass.setBounds(210, 132, 206, 30);
        txt_pass.setColumns(10);
        contentPane.add(txt_pass);

        lblNewLabel = new JLabel("Email");
        lblNewLabel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        lblNewLabel.setForeground(Color.WHITE);
        lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 17));
        lblNewLabel.setBounds(210, 38, 100, 25);
        contentPane.add(lblNewLabel);

        btnLogin = new JButton("Đăng nhập");
        btnLogin.setEnabled(true);
        btnLogin.setForeground(new Color(255, 255, 255));
        btnLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnLogin.setBackground(new Color(51, 51, 102));
        btnLogin.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnLogin.setBounds(210, 192, 206, 40);
        contentPane.add(btnLogin);
        btnLogin.addActionListener((ActionEvent e) -> {
            btnLoginActionPerform(e);
        });

        lbl_IconDeliveryman = new JLabel("");
        lbl_IconDeliveryman.setHorizontalAlignment(SwingConstants.LEFT);
        lbl_IconDeliveryman.setIcon(new ImageIcon(LoginForm.class.getResource("/images/background_home.png")));
        lbl_IconDeliveryman.setBounds(0, 0, 633, 350);
        contentPane.add(lbl_IconDeliveryman);
        widthLeft = (int) contentPane.getPreferredSize().getWidth();

    }

    public int verifyLogin(String email, char[] passwd) {
        if (email.equals("") || passwd.toString().equals("")) {
            return 0;
        } else if (!email.matches(mailregex)) {
            return 3;
        }
        return 1;
    }

    private void btnLoginActionPerform(ActionEvent e) {
        switch (verifyLogin(txt_username.getText(), txt_pass.getPassword())) {
            case 0:
                JOptionPane.showMessageDialog(contentPane, "Email or Password cannot be blank. Try again.", "Error Message", JOptionPane.ERROR_MESSAGE);
                txt_username.setText("");
                txt_pass.setText("");
                break;
            case 3:
                JOptionPane.showMessageDialog(contentPane, "Invalid Email. Try again.", "Error Message", JOptionPane.ERROR_MESSAGE);
                txt_username.setText("");
                txt_pass.setText("");
                break;
            default:
                client.verifyLogin(txt_username.getText(), txt_pass.getPassword());
                break;
//              Thay thế bằng LoginFail
//                if (rs == -1) {
//                    JOptionPane.showMessageDialog(contentPane, "Wrong email or password! Please try again.", "Error Message", JOptionPane.ERROR_MESSAGE);
//                    txt_pass.setText("");
                
//              Thay thế bằng LoginSuccess
//                } else if (rs == 1) {
//                    HomeSelection.status = 1;
//                    new HomeSelection(client).setVisible(true);
//                    this.dispose();
//                }
        }
    }

    public void LoginFail() {
        JOptionPane.showMessageDialog(contentPane, "Wrong email or password! Please try again.", "Error Message", JOptionPane.ERROR_MESSAGE);
        txt_pass.setText("");
    }

    public void LoginSuccess() {
        HomeSelection.status = 1;
        new HomeSelection(client).setVisible(true);
        this.dispose();
    }

    public void closeThisWindow() {
        this.dispose();
    }

    public void moveWindow(int positionX, int positionY) {
        this.setLocation(positionX, positionY);
    }

}
