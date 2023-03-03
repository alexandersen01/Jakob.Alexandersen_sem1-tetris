package no.uib.in101.tetris.model.tetromino;

import no.uib.inf101.tetris.model.tetromino.Tetromino;
import no.uib.inf101.tetris.model.tetromino.TetrominoFactory;


public class PatternedTetrominoFactory implements TetrominoFactory {


    public String pattern;
    private int index = 0;

    public PatternedTetrominoFactory(String pattern) {
        //every time getNext is called, it will return the next tetromino in the pattern
        this.pattern = pattern;
    }

    @Override
    public Tetromino getNext() {

        Tetromino t = Tetromino.newTetromino(pattern.charAt(index));
        index ++;
        if (index == pattern.length()) {
            index = 0;
        }
        return t;
    
}

}
