package GUI;

import static Utils.Class.LOGIN_FORM;
import static Utils.Class.REGISTER_FORM;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Home extends JFrame {

    private JPanel contentPane;
    private JLabel lbl_ImageBackground;
    private JButton btnLogin, btnRegister, btnQuit;
    int widthLeft;
    public Client client;

    public Home(Client client) {
        this.client = client;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(50, 100, 633, 350);
        setUndecorated(true);
        setLocationRelativeTo(null);
        initComponens();
        addEvents();
    }

    private void initComponens() {
        contentPane = new JPanel();
        contentPane.setLayout(null);
        setContentPane(contentPane);

        btnLogin = new JButton("Đăng nhập");
        btnLogin.setForeground(new Color(255, 255, 255));
        btnLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnLogin.setBackground(new Color(51, 51, 102));
        btnLogin.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnLogin.setBounds(211, 76, 206, 40);
        contentPane.add(btnLogin);

        btnRegister = new JButton("Đăng ký");
        btnRegister.setForeground(new Color(255, 255, 255));
        btnRegister.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnRegister.setBackground(new Color(51, 51, 102));
        btnRegister.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnRegister.setBounds(211, 154, 206, 40);
        contentPane.add(btnRegister);

        btnQuit = new JButton("Thoát");
        btnQuit.setForeground(new Color(255, 255, 255));
        btnQuit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnQuit.setBackground(new Color(51, 51, 102));
        btnQuit.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnQuit.setBounds(211, 226, 206, 40);
        contentPane.add(btnQuit);

        lbl_ImageBackground = new JLabel("");
        lbl_ImageBackground.setHorizontalAlignment(SwingConstants.LEFT);
        lbl_ImageBackground.setIcon(new ImageIcon(LoginForm.class.getResource("/images/background_home.png")));
        lbl_ImageBackground.setBounds(0, 0, 633, 350);
        contentPane.add(lbl_ImageBackground);
        widthLeft = (int) contentPane.getPreferredSize().getWidth();

    }

    private void addEvents() {
        // Login
        btnLogin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                loadLoginForm(client);
            }
        });

        btnRegister.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                LoadRegisterForm(client);
            }
        });

        btnQuit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }
        });
    }

    public void CloseThisFrame() {
        this.dispose();
//        this.setVisible(false);
//        this.setEnabled(false);
    }

    public void LoadRegisterForm(Client client) {
        REGISTER_FORM = new RegisterForm(client);
        REGISTER_FORM.setVisible(true);
        CloseThisFrame();
    }

    public void loadLoginForm(Client client) {
        LOGIN_FORM = new LoginForm(client);
        LOGIN_FORM.setVisible(true);
        CloseThisFrame();
    }
}
