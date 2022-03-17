import java.util.ArrayList;
import java.util.Collections;

import javax.swing.*;

public class Sudoku {
    public static int grid[][] = {
        { 0, 2, 1, 6, 0, 0, 7, 5, 9 },
        { 0, 9, 3, 0, 1, 5, 0, 4, 0 },
        { 5, 7, 0, 0, 0, 9, 6, 0, 0 },
        { 1, 0, 9, 5, 0, 2, 0, 0, 4 },
        { 7, 3, 5, 4, 6, 0, 0, 8, 2 },
        { 2, 0, 8, 0, 0, 0, 0, 6, 1 },
        { 3, 5, 2, 0, 7, 4, 0, 0, 6 },
        { 0, 1, 0, 2, 0, 8, 0, 0, 7 },
        { 0, 8, 0, 9, 3, 0, 1, 0, 5 }
        };

    
    public static JLabel getLabel(ArrayList<JLabel> arr,int x, int y){
        for(JLabel i : arr){
            if(i.getX() == x && i.getY() == y){
                return i;
            }
        }
        return null;
    }

    public static void makeZeroGrid(int[][] grid){
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid.length; j++){
                grid[i][j] = 0;
            }
        }
    }

    public static boolean legal_number(int[][] grid, int row, int col, int number){
        //Same row
        for(int i = 0; i < grid.length; i++){
            if(i == col){
                continue;
            }
            if(grid[row][i] == number){
                return false;
            }
        }
        //Same column
        for(int i = 0; i < grid.length; i++){
            if(i == row){
                continue;
            }
            if(grid[i][col] == number){
                return false;
            }
        }
        //Same box
        int box_x = (row / 3)*3;
        int box_y = (col / 3)*3;

        for(int i = box_x; i < box_x + 3; i++){
            for(int j = box_y; j < box_y + 3; j++){
                if(i == row && j == row){
                    continue;
                }
                if(grid[i][j] == number){
                    return false;
                }
            }
        }
        
        return true;
    }

    public static boolean solve(int[][] grid, int remove){
        int row = -1;
        int col = -1;
        boolean found = false;

        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid.length; j++){
                if(grid[i][j] == 0){
                    found = true;
                    row = i;
                    col = j;
                    break;
                }
            }
            if(found){
                break;
            }
        }

        if(!found){
            return true;
        }

        int arr[] = {1,2,3,4,5,6,7,8,9};
        ArrayList<Integer> l = new ArrayList<Integer>();
        for(int i : arr){
            l.add(i);
        }
        Collections.shuffle(l);
        if(remove != -1){
            l.remove(l.indexOf(remove));
        }

        for(int number : l){
            if(legal_number(grid, row, col, number)){
                grid[row][col] = number;
                if(solve(grid, remove)){
                    return true;
                }
                else{
                    grid[row][col] = 0;
                }
            }
        }
        return false;
    }

    public static int getRandom(){
        int a = (int)(Math.random()*10);
        while(a == 0){
            a = (int)(Math.random()*10);
        }
        return a;
    }

    public static void removeRandom(int[][] grid){
        int counter = 0;
        int k = 35;

        while(counter < k){
            int r = getRandom();
            int c = getRandom();
            while(grid[r - 1][c - 1] == 0){
                r = getRandom();
                c = getRandom();
            }

            int number = grid[r - 1][c - 1];
            grid[r - 1][c - 1] = 0;
            if(solve(grid, number)){
                grid[r - 1][c - 1] = number;
            }
            else{
                counter++;
            }
        }
    }

    public static void print(int[][] grid){
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid.length; j++){
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }
}
