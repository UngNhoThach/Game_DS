package GUI;

import static Utils.Constant.PANEL_WIDTH;
import static Utils.Constant.TILE_SIZE;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PointPanel extends JPanel {

    static int height = TILE_SIZE;
    private JButton powerUp;

    private JLabel number, time, player1, player2;
    ImageIcon ic;
    Color background = new Color(0, 70, 80);

    public PointPanel() {
        this.setPreferredSize(new Dimension(PANEL_WIDTH, height));
        this.setBackground(background);
        FlowLayout layout = new FlowLayout();
        layout.setAlignment(FlowLayout.CENTER);
        layout.setHgap(50);
        this.setLayout(layout);
        init();
        this.setVisible(true);

    }

    protected final void init() {
        ic = new ImageIcon();
        powerUp = new JButton();
        number = new JLabel();
        time = new JLabel();
        player1 = new JLabel();
        player2 = new JLabel();
        Font font = new Font("Time New Roman", Font.PLAIN, 30);
        //this.setLayout(new FlowLayout(1, 50, 10));

        ImageIcon imageIcon = new ImageIcon(new ImageIcon(PointPanel.class.getResource("/images/thunder.png")).getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
        powerUp.setIcon(imageIcon);
        powerUp.setBackground(background);
        powerUp.setBorderPainted(false);
        powerUp.setEnabled(false);
        powerUp.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                powerUp.setEnabled(false);
                mainFrame.PowerUp();
            }
        });

        imageIcon = new ImageIcon(new ImageIcon(PointPanel.class.getResource("/images/timer-icon-small.png")).getImage());
        time.setIcon(imageIcon);
        time.setFont(font);
        time.setForeground(Color.WHITE);

        number.setForeground(Color.WHITE);
        number.setFont(font);
        number.setBackground(background);

        player1.setText("Player 1: ");
        player2.setText("Player 2: ");

        this.add(powerUp);
        this.add(player1);
        this.add(number);
        this.add(player2);
        this.add(time);

    }

    public void update_Score(String id, String score) {
        if (!id.equals("")) {
            if (id.equals("1")) {
                player1.setForeground(Color.GREEN);
            } else {
                player2.setForeground(Color.GREEN);
            }
        }
    }

    public void setTime(String time) {
        this.time.setText(time);
    }

    public void setNumber(String number) {
        this.number.setForeground(Color.WHITE);
        this.number.setText(number);
    }

    public void setLuckyNumber(String number) {
        this.number.setForeground(Color.YELLOW);
        this.number.setText(number);
    }

    public void setPlayer1(String point) {
        this.player1.setText("Player 1: " + point);
    }

    public void setPlayer2(String point) {
        this.player2.setText("Player 2: " + point);
    }

    public JButton getPowerUp() {
        return powerUp;
    }

}
