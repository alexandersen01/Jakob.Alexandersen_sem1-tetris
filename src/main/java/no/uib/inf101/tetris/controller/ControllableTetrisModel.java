package no.uib.inf101.tetris.controller;

public interface ControllableTetrisModel {
    

    /**
     * Moves the current tetromino down one row
     * @param deltaRow
     * @param deltaCol
     * @return True if the tetromino was moved, false if it was not
     */
    public boolean MoveTetromino (int deltaRow, int deltaCol);

    /**
     * Rotates the current tetromino clockwise
     * @return True if the tetromino was rotated, false if it was not
     */
    public boolean RotateTetromino();

}
