package no.uib.inf101.tetris.model;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.Grid;

/**
     * Create a new TetrisBoard with the given rows and cols
     * @param rows
     * @param cols
     */

public class TetrisBoard extends Grid<Character> {

    public static int rowsRemoved = 0;
    public static int score;


    /**
     * Create a new TetrisBoard with the given rows and cols
     * @param rows
     * @param cols
     */
    public TetrisBoard(int rows, int cols) {
        super(rows, cols);
        //initialize the grid with empty cells
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                set(new CellPosition(i, j), '-');
            }
        }
        this.score = 0;
    }

    public String prettyString(){
        return "g--y\n----\nr--b";
    }


    /**
     * Checks if a row contains a given element
     * @param row
     * @param c
     * @return
     */
    public boolean rowContains(int row, char c) {
        for (int i = 0; i < cols(); i++) {
            if (get(new CellPosition(row, i)) == c) {
                return true;
            }
        }
        return false;
    }


    /**
     * Sets all the elements in a row to a given value
     * @param row
     * @param element
     */
    public void setRow(int row, char element) {
        for (int i = 0; i < cols(); i++) {
            set(new CellPosition(row, i), element);
        }
    }


    /**
     * Copies all the values from a given row to another row
     * @param from
     * @param to
     */
    public void copyRow(int from, int to) {
        for(int i = 0; i < cols(); i++){
            CellPosition fromPos = new CellPosition(from, i);
            CellPosition toPos = new CellPosition(to, i);
            set(toPos, get(fromPos));
        }
    }

    
    /**
     * Removes a row and moves all the rows above it down
     * @return rows removed
     */
    public int removeRow(){

        int oldRow = rows() - 1;
        int newRow = rows() - 1;
        while (newRow >= 0){
            while(oldRow >= 0 && !rowContains(oldRow, '-')){
                rowsRemoved++;
                if(rowsRemoved == 2){
                    score += 300;
                }
                else if(rowsRemoved == 3){
                    score += 500;
                }
                else if(rowsRemoved == 4){
                    score += 800;
                }
                
                oldRow--;
            }
            if(oldRow < 0){
                setRow(newRow, '-');
            }
            else{
                copyRow(oldRow, newRow);
            }
            newRow--;
            oldRow--;
        }
        return rowsRemoved;
        
        
    }
    

}
