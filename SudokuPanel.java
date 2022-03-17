import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

public class SudokuPanel extends JPanel implements MouseListener, KeyListener{
    public int x;
    public int y;
    public static ArrayList<JLabel> labelList = new ArrayList<JLabel>();
    public static JLabel holdedLabel;
    public static int change[][] = new int[Sudoku.grid.length][Sudoku.grid.length];
    public static int check[][] = new int[Sudoku.grid.length][Sudoku.grid.length];

    public SudokuPanel(){
        this.setBounds(((Constants.FRAME_WIDHT-300)-Constants.PANEL_WIDHT)/2,(Constants.FRAME_HEIGHT - Constants.PANEL_HEIGHT)/2,Constants.PANEL_WIDHT,Constants.PANEL_HEIGHT);
        this.setBackground(new Color(255,255,255));
        this.setVisible(true);
        this.setLayout(null);
        this.setFocusable(true);
        addMouseListener(this);
        addKeyListener(this);

        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                JLabel label = new JLabel("",SwingConstants.CENTER);
                label.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
                label.setVisible(true);
                label.setBounds(i*Constants.BOX_SIZE, j*Constants.BOX_SIZE, Constants.BOX_SIZE, Constants.BOX_SIZE);
                this.add(label);
                labelList.add(label);
            }
        }
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        drawLinesP(g);
        drawLinesH(g);
        dyeSquare(g);
    }

    public void drawLinesP(Graphics g){
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(Color.BLACK);
        for(int i = 0; i <= Constants.PANEL_WIDHT; i += Constants.BOX_SIZE){
            if(i % 3 == 0){
                g2d.setStroke(new BasicStroke(4,BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            }
            else{
                g2d.setStroke(new BasicStroke(1,BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            }
            g2d.drawLine(i,0,i,Constants.PANEL_HEIGHT);
        }
    }

    public void drawLinesH(Graphics g){
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(Color.BLACK);
        for(int i = 0; i <= Constants.PANEL_HEIGHT; i += Constants.BOX_SIZE){
            if(i % 3 == 0){
                g2d.setStroke(new BasicStroke(4,BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            }
            else{
                g2d.setStroke(new BasicStroke(1,BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            }
            g2d.drawLine(0,i,Constants.PANEL_WIDHT,i);
        }
    }

    public void dyeSquare(Graphics g){
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(Color.RED);
        g2d.setStroke(new BasicStroke(3,BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2d.drawLine(x*Constants.BOX_SIZE, y*Constants.BOX_SIZE, x*Constants.BOX_SIZE, y*Constants.BOX_SIZE + Constants.BOX_SIZE);
        g2d.drawLine(x*Constants.BOX_SIZE, y*Constants.BOX_SIZE, x*Constants.BOX_SIZE + Constants.BOX_SIZE, y*Constants.BOX_SIZE);
        g2d.drawLine(x*Constants.BOX_SIZE + Constants.BOX_SIZE, y*Constants.BOX_SIZE, x*Constants.BOX_SIZE + Constants.BOX_SIZE, y*Constants.BOX_SIZE + Constants.BOX_SIZE);
        g2d.drawLine(x*Constants.BOX_SIZE, y*Constants.BOX_SIZE + Constants.BOX_SIZE, x*Constants.BOX_SIZE + Constants.BOX_SIZE, y*Constants.BOX_SIZE + Constants.BOX_SIZE);
        this.repaint();
    }

    public void getLabel(){
        for(JLabel i : labelList){
            if(i.getX()/80 == x && i.getY()/80 == y){
                holdedLabel = i;
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        x = e.getX()/Constants.BOX_SIZE;
        y = e.getY()/Constants.BOX_SIZE;

        getLabel();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }


    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(holdedLabel != null){
            int label_x = holdedLabel.getY() / Constants.BOX_SIZE;
            int label_y = holdedLabel.getX() / Constants.BOX_SIZE;
            if(check[label_x][label_y] == 0){
                int key = e.getKeyCode();
    
                if(key == KeyEvent.VK_1){
                    holdedLabel.setText("1");
                    change[label_x][label_y] = 1;
                }
                else if(key == KeyEvent.VK_2){
                    holdedLabel.setText("2");
                    change[label_x][label_y] = 2;
                }
                else if(key == KeyEvent.VK_3){
                    holdedLabel.setText("3");
                    change[label_x][label_y] = 3;
                }
                else if(key == KeyEvent.VK_4){
                    holdedLabel.setText("4");
                    change[label_x][label_y] = 4;
                }
                else if(key == KeyEvent.VK_5){
                    holdedLabel.setText("5");
                    change[label_x][label_y] = 5;
                }
                else if(key == KeyEvent.VK_6){
                    holdedLabel.setText("6");
                    change[label_x][label_y] = 6;
                }
                else if(key == KeyEvent.VK_7){
                    holdedLabel.setText("7");
                    change[label_x][label_y] = 7;
                }
                else if(key == KeyEvent.VK_8){
                    holdedLabel.setText("8");
                    change[label_x][label_y] = 8;
                }
                else if(key == KeyEvent.VK_9){
                    holdedLabel.setText("9");
                    change[label_x][label_y] = 9;
                }
                else if(key == KeyEvent.VK_BACK_SPACE){
                    holdedLabel.setText("");
                    change[label_x][label_y] = 0;
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
