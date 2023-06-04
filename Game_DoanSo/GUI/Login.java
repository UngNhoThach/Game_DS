//package GUI;
//
//import java.io.*;
//import java.security.NoSuchAlgorithmException;
//import java.awt.Dimension;
//
//import javax.swing.JFrame;
//import javax.swing.JPanel;
//import javax.swing.border.EmptyBorder;
//import javax.swing.event.DocumentEvent;
//import javax.swing.event.DocumentListener;
//import java.awt.HeadlessException;
//
//import javax.swing.JLabel;
//import javax.swing.JOptionPane;
//import javax.swing.JTextField;
//
//import javax.swing.JPasswordField;
//import javax.swing.JButton;
//import java.awt.Cursor;
//import java.awt.event.MouseAdapter;
//import java.awt.event.MouseEvent;
//import java.awt.event.ActionEvent;
//
//public class Login extends JFrame {
//
//    public Client cl;
//    //public Login frame = new Login(cl);
//    static public String mailregex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
//
//    private JPanel contentPane;
//    private JTextField emailField;
//    private JPasswordField passwordField;
//
//    JButton btnLogin = new JButton("Login");
//
//    public int verifyLogin(String email, char[] passwd) {
//        if (email.equals("") || passwd.toString().equals("")) {
//            return 0;
//        } else if (!email.matches(mailregex)) {
//            return 3;
//        }
//        return 1;
//    }
//
//    private void btnLoginActionPerform(ActionEvent e) throws HeadlessException, IOException, NoSuchAlgorithmException {
//        String flag = "false";
//
//        switch (verifyLogin(emailField.getText(), passwordField.getPassword())) {
//            case 0:
//                JOptionPane.showMessageDialog(contentPane, "Email or Password cannot be blank. Try again.", "Error Message", JOptionPane.ERROR_MESSAGE);
//                emailField.setText("");
//                passwordField.setText("");
//                break;
//            case 3:
//                JOptionPane.showMessageDialog(contentPane, "Invalid Email. Try again.", "Error Message", JOptionPane.ERROR_MESSAGE);
//                emailField.setText("");
//                passwordField.setText("");
//                break;
//            default:
//                flag = "true";
//                break;
//        }
//
//        if (flag.equals("true")) {
//            cl.verifyLogin(emailField.getText(), passwordField.getPassword());
//        }
//    }
//
//    public void LoginFail() {
//        JOptionPane.showMessageDialog(contentPane, "Wrong email or password! Please try again.", "Error Message", JOptionPane.ERROR_MESSAGE);
//        passwordField.setText("");
//
//    }
//
//    /**
//     * Create the frame.
//     */
//    public Login(Client cl) {
//        this.cl = cl;
//        setTitle("Login");
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setBounds(100, 100, 488, 317);
//        contentPane = new JPanel();
//        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
//        setContentPane(contentPane);
//        contentPane.setLayout(null);
//
//        JPanel loginPanel = new JPanel();									//LOGIN PANE
//        loginPanel.setBounds(5, 5, 471, 265);
//        loginPanel.setPreferredSize(new Dimension(335, 420));
//        contentPane.add(loginPanel);
//        loginPanel.setLayout(null);
//
//        JLabel lblEmail = new JLabel("Email");
//        lblEmail.setBounds(10, 27, 46, 14);
//        loginPanel.add(lblEmail);
//
//        emailField = new JTextField();
//        emailField.setBounds(55, 24, 136, 20);
//        emailField.getDocument().addDocumentListener(new DocumentListener() {
//            @Override
//            public void changedUpdate(DocumentEvent e) {
//                changed();
//            }
//
//            @Override
//            public void removeUpdate(DocumentEvent e) {
//                changed();
//            }
//
//            @Override
//            public void insertUpdate(DocumentEvent e) {
//                changed();
//            }
//
//            public void changed() {
//                if (emailField.getText().equals("")) {
//                    btnLogin.setEnabled(false);
//                } else {
//                    btnLogin.setEnabled(true);
//                }
//            }
//        });
//        loginPanel.add(emailField);
//        emailField.setColumns(5);
//
//        JLabel lblPassword = new JLabel("Password");
//        lblPassword.setBounds(201, 27, 64, 14);
//        loginPanel.add(lblPassword);
//
//        passwordField = new JPasswordField();
//        passwordField.setBounds(275, 24, 169, 20);
//        passwordField.setColumns(5);
//        loginPanel.add(passwordField);
//        btnLogin.setBounds(176, 77, 77, 23);
//
//        btnLogin.addActionListener((ActionEvent e) -> {
//            try {
//                btnLoginActionPerform(e);
//            } catch (HeadlessException | IOException | NoSuchAlgorithmException e1) {
//                // TODO Auto-generated catch block
//                System.err.println(e1);
//            }
//        });
//        loginPanel.add(btnLogin);
//
//        JLabel lblSignUp = new JLabel("Sign Up");
//        lblSignUp.setBounds(121, 105, 57, 14);
//        lblSignUp.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                new RegisterForm(cl).setVisible(true);
//                dispose();
//            }
//        });
//        lblSignUp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//        loginPanel.add(lblSignUp);
//
//        JPanel panel_1 = new JPanel();										//IMAGE PANE
//        panel_1.setBounds(486, 5, 183, 265);
//        panel_1.setPreferredSize(new Dimension(330, 420));
//        contentPane.add(panel_1);
//        panel_1.setLayout(null);
//    }
//
//}
