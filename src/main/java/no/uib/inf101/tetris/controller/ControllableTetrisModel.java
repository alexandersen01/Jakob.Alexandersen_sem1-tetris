package no.uib.inf101.tetris.controller;

import no.uib.inf101.tetris.model.GameState;

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

    /**
     * Drops the current tetromino to the bottom of the board
     * @return True if the tetromino was dropped, false if it was not
     */
    public boolean DropTetromino();

        /**
     * Get the current game state
     * @return The current game state
     */
    GameState getGameState();

    /**
     * Get the current tick time
     * @return The current tick time
     */
    int getTickTime();

    /**
     * This method gets called every tick in order to move the faling piece every tick
     */
    public void clockTick();

}
