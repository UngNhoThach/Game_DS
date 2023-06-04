package GUI;

import static Utils.Class.HOME_SELECTION;
import static Utils.Constant.PANEL_HEIGHT;
import static Utils.Constant.PANEL_WIDTH;
import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class AfterMatchFrame extends JFrame {

    AfterMatchPanel afterMatch;
    String result = "SS";
    Client client;

    public AfterMatchFrame(Client c, String result) {
        this.result = result;
        client = c;
        System.out.println("Result2 = " + result);
        this.afterMatch = new AfterMatchPanel(client, this);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(afterMatch);
        //this.setBackground(Color.yellow);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}

class AfterMatchPanel extends JPanel {

    AfterMatchFrame frame;
    Client client;

    public AfterMatchPanel(Client c, AfterMatchFrame aThis) {
        client = c;
        this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
//        this.setBounds(0, 0, PANEL_WIDTH, PANEL_HEIGHT);
        this.frame = aThis;
        if (frame.result.equals("You Win")) {
            initComponents(new java.awt.Color(255, 255, 0));
        } else {
            initComponents(new java.awt.Color(0, 153, 153));
        }

        this.setVisible(true);

    }

    private void initComponents(Color colorResult) {

        lblResult = new javax.swing.JLabel();
        btnPlayAgain = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        lblTitle = new javax.swing.JLabel();

        setBackground(new java.awt.Color(0, 0, 0));

        lblResult.setBackground(new java.awt.Color(255, 255, 255));
        lblResult.setFont(new java.awt.Font("MV Boli", 1, 90)); // NOI18N
        lblResult.setForeground(colorResult);
        lblResult.setText(frame.result);

        btnPlayAgain.setBackground(new java.awt.Color(0, 0, 0));
        btnPlayAgain.setFont(new java.awt.Font("MV Boli", 1, 15)); // NOI18N
        btnPlayAgain.setForeground(new java.awt.Color(255, 204, 51));
        btnPlayAgain.setText("Play Again");
        btnPlayAgain.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 102, 0)));
        btnPlayAgain.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPlayAgain.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnPlayAgainMouseClicked(evt);
            }

            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnPlayAgainMouseEntered(evt);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnPlayAgainMouseExited(evt);
            }
        });

        btnExit.setBackground(new java.awt.Color(0, 0, 0));
        btnExit.setFont(new java.awt.Font("MV Boli", 1, 15)); // NOI18N
        btnExit.setForeground(new java.awt.Color(255, 204, 51));
        btnExit.setText("Exit");
        btnExit.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 102, 0)));
        btnExit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnExit.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnExitMouseClicked(evt);
            }

            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnExitMouseEntered(evt);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnExitMouseExited(evt);
            }
        });

        lblTitle.setFont(new java.awt.Font("MV Boli", 1, 70)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(255, 102, 0));
        lblTitle.setText("Play Again?");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(450, 450, 450)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 427, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblResult, javax.swing.GroupLayout.PREFERRED_SIZE, 427, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(btnPlayAgain, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(108, 108, 108)
                                                .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(287, 287, 287))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(127, 127, 127)
                                .addComponent(lblResult, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(32, 32, 32)
                                .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnPlayAgain, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(213, Short.MAX_VALUE))
        );
    }

    private void btnPlayAgainMouseEntered(java.awt.event.MouseEvent evt) {
        btnPlayAgain.setBackground(new Color(255, 102, 0));
        btnPlayAgain.setForeground(new Color(255, 255, 255));
        btnPlayAgain.setFont(new Font("MV Boli", Font.BOLD, 18));
    }

    private void btnPlayAgainMouseExited(java.awt.event.MouseEvent evt) {
        btnPlayAgain.setBackground(new Color(0, 0, 0));
        btnPlayAgain.setForeground(new Color(255, 204, 51));
        btnPlayAgain.setFont(new Font("MV Boli", Font.BOLD, 15));
    }

    private void btnExitMouseEntered(java.awt.event.MouseEvent evt) {
        btnExit.setBackground(new Color(255, 102, 0));
        btnExit.setForeground(new Color(255, 255, 255));
        btnExit.setFont(new Font("MV Boli", Font.BOLD, 18));
    }

    private void btnExitMouseExited(java.awt.event.MouseEvent evt) {
        btnExit.setBackground(new Color(0, 0, 0));
        btnExit.setForeground(new Color(255, 204, 51));
        btnExit.setFont(new Font("MV Boli", Font.BOLD, 15));
    }

    private void btnPlayAgainMouseClicked(java.awt.event.MouseEvent evt) {
        HOME_SELECTION = new HomeSelection(client);
        HOME_SELECTION.setVisible(true);
        frame.dispose();
    }

    private void btnExitMouseClicked(java.awt.event.MouseEvent evt) {
        HOME_SELECTION  = new HomeSelection(client);
        HOME_SELECTION.setVisible(true);
        frame.dispose();
    }

    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnPlayAgain;
    public javax.swing.JLabel lblResult;
    private javax.swing.JLabel lblTitle;
}
