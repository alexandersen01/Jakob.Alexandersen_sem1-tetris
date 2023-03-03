package no.uib.inf101.tetris.model;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.Grid;

/**
     * Create a new TetrisBoard with the given rows and cols
     * @param rows
     * @param cols
     */

public class TetrisBoard extends Grid<Character> {



    public TetrisBoard(int rows, int cols) {
        super(rows, cols);
        //initialize the grid with empty cells
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                set(new CellPosition(i, j), '-');
            }
        }
    }

    public String prettyString(){
        return "g--y\n----\nr--b";
    }

    //implement method to check if an element exists in a row
    public boolean rowContains(int row, char c) {
        for (int i = 0; i < cols(); i++) {
            if (get(new CellPosition(row, i)) == c) {
                return true;
            }
        }
        return false;
    }

    //implement a method that sets all the elements in a row to a given value
    public void setRow(int row, char element) {
        for (int i = 0; i < cols(); i++) {
            set(new CellPosition(row, i), element);
        }
    }

    //implement a method that copies all the values from a given row to another row
    public void copyRow(int from, int to) {
        for(int i = 0; i < cols(); i++){
            CellPosition fromPos = new CellPosition(from, i);
            CellPosition toPos = new CellPosition(to, i);
            set(toPos, get(fromPos));
        }
    }

    //implement a method that removes a row and moves all the rows above it down
    public int removeRow(){
        int rowsremoved = 0;
        int oldRow = rows() - 1;
        int newRow = rows() - 1;
        while (newRow >= 0){
            while(oldRow >= 0 && !rowContains(oldRow, '-')){
                rowsremoved++;
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
        return rowsremoved;
    }


}
