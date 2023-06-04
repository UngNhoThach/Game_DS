package GUI;

import static Utils.Class.EDIT_INFO_FORM;
import static Utils.Class.HOME;
import static Utils.Class.STATISTIC;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import javax.swing.JLabel;
import static Utils.Class.MAIN_FRAME;
public class HomeSelection extends JFrame {

    public static Client client;
    public static int status;
    private JPanel contentPane;
    private JLabel lbl_ImageBackground, lbl_IconGame, lbl_IconLogout, lbl_IconEdit, lbl_IconTop;
    int widthLeft;

    public HomeSelection(Client client) {
        this.client = client;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(50, 100, 633, 350);
        setUndecorated(true);
        setLocationRelativeTo(null);
        initComponens(); // initComponens
    }

    private void initComponens() {
        contentPane = new JPanel();
        contentPane.setLayout(null);
        setContentPane(contentPane);

        // icon vào game
        lbl_IconGame = new JLabel("");
        lbl_IconGame.setBackground(new Color(255, 255, 255));
        lbl_IconGame.setHorizontalAlignment(SwingConstants.LEFT);
        lbl_IconGame.setIcon(new ImageIcon(LoginForm.class.getResource("/images/iconplay.png")));
        lbl_IconGame.setBounds(212, 104, 250, 125);
        contentPane.add(lbl_IconGame);
        lbl_IconGame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MAIN_FRAME = new mainFrame(client);
                client.sendOnlyCmd("startgame");
                closeThisWindow();
            }
        }
        );
        // icon đăng xuất
        lbl_IconLogout = new JLabel("");
        lbl_IconLogout.setBackground(new Color(255, 255, 255));
        lbl_IconLogout.setHorizontalAlignment(SwingConstants.LEFT);
        lbl_IconLogout.setIcon(new ImageIcon(LoginForm.class.getResource("/images/logout.png")));
        lbl_IconLogout.setBounds(594, 0, 29, 43);
        lbl_IconLogout.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                client.sendOnlyCmd("LogOut"); // TODO Auto-generated catch block
                HomeSelection.status = 0;
                HOME = new Home(client);
                HOME.setVisible(true);
                closeThisWindow();
            }
        });
        contentPane.add(lbl_IconLogout);

        // icon xếp hạng
        lbl_IconTop = new JLabel("");
        lbl_IconTop.setBounds(20, 287, 49, 37);
        lbl_IconTop.setBackground(new Color(255, 255, 255));
        lbl_IconTop.setHorizontalAlignment(SwingConstants.LEFT);
        lbl_IconTop.setIcon(new ImageIcon(LoginForm.class.getResource("/images/analysis.png")));
        lbl_IconTop.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                STATISTIC = new Statistic(client); // TODO Auto-generated catch block
                STATISTIC.setVisible(true);
                closeThisWindow();
            }
        });
        contentPane.add(lbl_IconTop);

        // icon chỉnh sửa thông tin cá nhân
        lbl_IconEdit = new JLabel("");
        lbl_IconEdit.setBackground(new Color(255, 255, 255));
        lbl_IconEdit.setHorizontalAlignment(SwingConstants.LEFT);
        lbl_IconEdit.setIcon(new ImageIcon(LoginForm.class.getResource("/images/edit.png")));
        lbl_IconEdit.setBounds(20, 233, 37, 43);
        lbl_IconEdit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                EDIT_INFO_FORM = new EditInforForm(client);
                EDIT_INFO_FORM.setVisible(true);
                closeThisWindow();
            }
        });
        contentPane.add(lbl_IconEdit);

        // hình nền game
        lbl_ImageBackground = new JLabel("");
        lbl_ImageBackground.setHorizontalAlignment(SwingConstants.LEFT);
        lbl_ImageBackground.setIcon(new ImageIcon(LoginForm.class.getResource("/images/background_home.png")));
        lbl_ImageBackground.setBounds(0, 0, 633, 350);
        contentPane.add(lbl_ImageBackground);

    }

    public void closeThisWindow() {
        this.dispose();
    }

}
