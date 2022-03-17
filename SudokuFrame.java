import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class SudokuFrame extends JFrame implements ActionListener{

    SudokuPanel sPanel = new SudokuPanel();
    JButton solveBtn = new JButton("Solve");
    JButton submitBtn = new JButton("Submit");
    JButton newgameBtn = new JButton("New Game");
    JLabel resultLbl = new JLabel("",SwingConstants.CENTER);

    public SudokuFrame(){
        this.setTitle("Sudoku");
        this.setSize(Constants.FRAME_WIDHT,Constants.FRAME_HEIGHT);
        this.setBackground(new Color(255,255,255));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);
        this.setLayout(null);
        this.setFocusable(false);

        submitBtn.setBounds(sPanel.getX() + Constants.PANEL_WIDHT + 95, sPanel.getY() + Constants.BOX_SIZE*3, 200, Constants.BOX_SIZE);
        submitBtn.setBackground(Color.WHITE);
        submitBtn.setFocusable(false);
        solveBtn.setBounds(sPanel.getX() + Constants.PANEL_WIDHT + 95, sPanel.getY() + Constants.BOX_SIZE*4, 200, Constants.BOX_SIZE);
        solveBtn.setBackground(Color.WHITE);
        solveBtn.setFocusable(false);
        newgameBtn.setBounds(sPanel.getX() + Constants.PANEL_WIDHT + 95, sPanel.getY() + Constants.BOX_SIZE*5, 200, Constants.BOX_SIZE);
        newgameBtn.setBackground(Color.WHITE);
        newgameBtn.setFocusable(false);
        resultLbl.setBounds(sPanel.getX() + Constants.PANEL_WIDHT + 95, sPanel.getY() + Constants.BOX_SIZE, 200, Constants.BOX_SIZE);
        resultLbl.setBackground(Color.WHITE);
        resultLbl.setOpaque(true);
        resultLbl.setFocusable(false);
        submitBtn.addActionListener(this);
        solveBtn.addActionListener(this);
        newgameBtn.addActionListener(this);

        this.add(sPanel);
        this.add(solveBtn);
        this.add(submitBtn);
        this.add(newgameBtn);
        this.add(resultLbl);
    }

    public static void printGrid(){
        int r = 0;
        int c = 0;
        for(JLabel label : SudokuPanel.labelList){
            String s = Integer.toString(Sudoku.grid[c][r]);
            if(Sudoku.grid[c][r] == 0){
                label.setText("");
            }
            else{
                label.setText(s);
                label.setForeground(Color.GRAY);
            }
            c += 1;

            if(c >= Sudoku.grid.length){
                c = 0;
                r += 1;
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e)  {
        if(e.getSource() == submitBtn){
            Sudoku.solve(Sudoku.grid, -1);
            boolean correct = true;
            for(int i = 0; i < Sudoku.grid.length; i++){
                for(int j = 0; j < Sudoku.grid.length; j++){
                    if(Sudoku.grid[i][j] != SudokuPanel.change[i][j]){
                        correct = false;
                    }
                }
            }

            if(correct){
                resultLbl.setText("Correct Solution");
            }
            else{
                resultLbl.setText("Uncorrect Sudoku");
            }

        }
        else if(e.getSource() == solveBtn){
            Sudoku.solve(Sudoku.grid, -1);
            Sudoku.solve(SudokuPanel.check, -1);
            resultLbl.setText("");
            printGrid();
        }
        else if(e.getSource() == newgameBtn){
            Sudoku.makeZeroGrid(Sudoku.grid);
            Sudoku.solve(Sudoku.grid, -1);
            Sudoku.removeRandom(Sudoku.grid);
            for(int i = 0; i < Sudoku.grid.length; i++){
                for(int j = 0; j < Sudoku.grid.length; j++){
                    SudokuPanel.check[i][j] = Sudoku.grid[i][j];
                    SudokuPanel.change[i][j] = Sudoku.grid[i][j];
                }
            }
            for(JLabel label : SudokuPanel.labelList){
                label.setForeground(Color.BLACK);
            }
            resultLbl.setText("");
            printGrid();
        }  
    }  
}
