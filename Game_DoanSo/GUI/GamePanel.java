package GUI;

import static Utils.Constant.*;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JPanel;
import DTO.Number;
import java.awt.event.MouseAdapter;

public class GamePanel extends JPanel {
    
    private final ArrayList<Number> numbers = new ArrayList<>();
    
    private final Random rand;
    static boolean Result_CheckNum = false;
    static int Total_Score = 0;

    // Send, Received
    public GamePanel() {
        
        this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        this.setDoubleBuffered(true);
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.addMouseListener(new Mouse());
        
        rand = new Random();
        
        int map[][] = new int[ROW][COLUMN];
        for (int i = 0; i < NUMBER_AMOUNT; i++) {
            
            int mRow = rand.nextInt(ROW);
            int mCol = rand.nextInt(COLUMN);
            
            while (map[mRow][mCol] == 1) {
                mRow = rand.nextInt(ROW);
                mCol = rand.nextInt(COLUMN);
            }
            
            map[mRow][mCol] = 1;
            
            Number n = new Number(i, mRow, mCol, rand.nextInt(TILE_SIZE - ORIGINAL_TILE_SIZE), rand.nextInt(TILE_SIZE - ORIGINAL_TILE_SIZE), ORIGINAL_TILE_SIZE + 10, ORIGINAL_TILE_SIZE + 10, Color.BLUE, TILE_SIZE);
            numbers.add(n);
            
        }
    }
    
    int arc = 0;
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        // Zooming
        g2.setStroke(new BasicStroke(2f));
        g2.setFont(new Font("Times New Roman", Font.BOLD, 20));
        
        g2.setColor(Color.green);
        
//        if (isEndgame()) {
//            g2.setColor(Color.green);
//            g2.drawString("Game Over", 0, TILE_SIZE / 2);
//            g2.dispose();
//            return;
//        }

//        for (int i = 0; i < COLUMN; i++) {
//            g2.drawLine(i * TILE_SIZE, 0, i * TILE_SIZE, panelHeight);
//        }
//        for (int i = 0; i < ROW; i++) {
//            g2.drawLine(0, i * TILE_SIZE, panelWidth, i * TILE_SIZE);
//        }
        g2.setFont(new Font("MV Boli", Font.BOLD, 15));
        FontMetrics metrics = getFontMetrics(g.getFont());
        for (Number n : numbers) {
            g2.setColor(n.getColor());
            if (n.isFill()) {
                g2.fillRoundRect(n.getX(), n.getY(), n.getWidth(), n.getHeight(), 10, 10);
            } else {
                if (n.getId() == mainFrame.currentNumber) {
                    g2.drawOval(n.getX() - arc / 2, n.getY() - arc / 2, n.getWidth() + arc, n.getHeight() + arc);
                }
                g2.drawRoundRect(n.getX(), n.getY(), n.getWidth(), n.getHeight(), 10, 10);
            }
            g2.drawString("" + (n.getId()), n.getX() + (n.getWidth() - metrics.stringWidth("" + (n.getId() + 1))) / 2, n.getY() + n.getHeight() / 2 + metrics.getHeight() / 4);
        }
        
        arc = (arc + 1) % 20;
        
        g2.dispose();
    }
    
//    public boolean isEndgame() {
//        if (mainFrame.time < 0) {
//            return true;
//        }
//        for (Number n : numbers) {
//            if (!n.isFill()) {
//                return false;
//            }
//        }
//        return true;
//    }
    
    public int checkCollision(int x, int y) {
        for (Number n : numbers) {
            if (n.getHitbox().contains(x, y)) {
                //n.setFill(true);
                arc = 0;
//                Result_CheckNum = true;
                mainFrame.Click(n.getId());
                return ++Total_Score;
            }
        }
        return Total_Score;
    }
    
    public void Fill(int id, Color c) {
        Number n = numbers.get(id);
        n.setColor(c);
        n.setFill(true);
    }
    
    class Mouse extends MouseAdapter {
        
        @Override
        public void mouseClicked(MouseEvent e) {
            int x = e.getX();
            int y = e.getY();
            Total_Score = checkCollision(x, y);
        }
    }
}
