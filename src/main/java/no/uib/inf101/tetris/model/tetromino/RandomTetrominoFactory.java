package no.uib.inf101.tetris.model.tetromino;

public class RandomTetrominoFactory implements TetrominoFactory{

    @Override
    public Tetromino getNext() {
        //choose a random char from the list of chars 'IJLOSTZ'
        //return a tetromino based on the char
        String chars = "LJSZTIO";
        int rand = (int)(Math.random() * chars.length());
        char c = chars.charAt(rand);
        return Tetromino.newTetromino(c);
    }

    
}
