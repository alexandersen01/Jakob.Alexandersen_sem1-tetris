package no.uib.inf101.tetris.model.tetromino;

public interface TetrominoFactory {
    /**
     * Get the next tetromino
     * @return a tetromino
     */
    Tetromino getNext();

    
}
