package no.uib.inf101.tetris.view;

import no.uib.inf101.grid.GridCell;
import no.uib.inf101.grid.GridDimension;
import no.uib.inf101.tetris.model.GameState;

public interface ViewableTetrisModel {

    /**
     * Get the dimension of the grid
     * @return The dimension of the grid
     */
    GridDimension getDimension();

    /**
     * Iterates over all tiles on the board
     * @return All positions of tiles on the board
     */
    Iterable<GridCell<Character>> getTilesOnBoard();

    /**
     * Makes the cells of the current tetromino iterable
     * @return something, idk i'll get back to you
     */
    Iterable<GridCell<Character>> getTilesOnTetromino();


    /**
     * Get the current game state
     * @return The current game state
     */
    GameState getGameState();


    /**
     * Get the amount of rows removed
     * @return a number
     */
    int getrowsRemoved();
}

