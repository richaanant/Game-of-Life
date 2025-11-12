import itsc2214.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Random;

/**
 * Implements Conway's Game of Life using a 2D array. 
 */
public class Project1 implements GameOfLife {
    private boolean[][] currentGrid;
    private boolean[][] previous;
    private int row;
    private int col;

    // TODO Implement 2 constructors
    public Project1() {
        this.row = 5;
        this.col = 5;
        this.previous = new boolean[5][5];
        for (int r = 0; r < 5; r++) {
            for (int c = 0; c < 5; c++) {
                this.previous[r][c] = false;
            }
        }
    }
    public Project1(int row, int col) {
        this.row = row;
        this.col = col;
        this.previous = new boolean[row][col];
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < row; c++) {
                this.previous[r][c] = false;
            }
        }
    }




    // TODO Implement all the methods fron the GameOfLife

    /**
     * it random assigns a grid with a live based on the alive probability 
     * return true if it is alive
     * otherwise, it is false
     * Share each grid by the previous and the current
     * @param aliveProbability
     */
    public void randomInitialize(double aliveProbability) {
        Random rand = new Random();
        Double randomNumber = rand.nextDouble();
        for (int r = 0; r < this.row; r++) {
            for (int c = 0; c < this.col; c++) {
               if (randomNumber < aliveProbability) {
                    this.previous[r][c] = false; // dead
               } else {
                    this.previous[r][c] = true; // alive
               }
            }
        }
    }

    /**
     * it loads the initial grid information given the string data
     * reads the first line from the string data
     * Creates a string array to split the first line from the string data
     * Converts the two elements in the string array to integer
     * Each line, we have go through each character in the line to see if it is "O" or a "."
     * Based on that, we have to adjust the grid accordingly
     * @param data - grid configration based on the row and column as a string
     */
    public void loadFromString(String data) {
        Scanner scanner = new Scanner(data);
        String line = scanner.nextLine();
        String[] numStrings = line.split(" ");
        this.row = Integer.parseInt(numStrings[0]);
        this.col = Integer.parseInt(numStrings[1]); 
        for (int r = 0; r < this.row; r++) {
            String newLine = scanner.nextLine();
            for (int c = 0; c < this.col; c++) {
                if (newLine.charAt(r) == 'O') {
                    this.previous[r][c] = true;
                } else {
                    this.previous[r][c] = false;
            }
         }
        }
    }
    
    /**
     * it loads the initial grid information given the string data from a file
     * Uses the scanner to read the file
     * Iterates the row of the grid by converting the file into the string
     * @param filename - grid based on row and column as a string in a file
     * @throws FileNotFoundException - if the file is not found
     */
    
    public void loadFromFile(String filename) throws FileNotFoundException {
        File file;
        Scanner scanner = new Scanner(filename); // this might be a source of errors
        try {
            file = new File(filename);
        } catch(Exception FileFoundException) {
            System.err.println("FileName is not valid");
        }
        String result = "";
        // Converting the file to a string
        for (int r = 0; r < this.row; r++) {
                result += scanner.next() + "\n";
            }
            loadFromString(result);
        }

          
    
    /**
     * It counts the number  of live neighbors of the cell based on the index of the row and the column
     * Skips the center of the cell
     * Skips the index of the row and column if it is -1
     * The count of live neighbors has to between the value of 0 and 8 
     * @param r - given index of row
     * @param c - given index of column
     * @return a int value based on count the number of live neighbors of the cell
     */
    
    public int countLiveNeighbors(int r, int c) {
        int countLiveNeighbors = 0;
        for (int i = r - 1; i <= r + 1; i++) {
            for (int j = c - 1; j <= c + 1; j++) {
                if (i >= 0 && i < this.row  && j >= 0 && j < this.col) {
                    if (i != r || j != c) {
                        boolean currentElement = previous[i][j];
                        if (currentElement == true) {
                            countLiveNeighbors++;
                        }

                    }

                }
            }
        }
        return countLiveNeighbors;


    }
            
    /**
     * Calculates the next generation of the grid
     * Iterates each row and column of the grid that counts the number of live neighbors of the cell
     * Set the next generation of the grid
     */
    public void nextGeneration() {
        for (int r = 0; r < this.row; r++) {
            for (int c = 0; c < this.col; c++) {
                countLiveNeighbors(r, c);
                this.previous[r][c] = this.currentGrid[r][c];
            }
        }

    }

    public boolean isAlive(int r, int c) {
        return false;
    }
    @Override
    public boolean isStillLife() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isStillLife'");
    }
    @Override
    public int numCols() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'numCols'");
    }
    @Override
    public int numRows() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'numRows'");
    }






    

}