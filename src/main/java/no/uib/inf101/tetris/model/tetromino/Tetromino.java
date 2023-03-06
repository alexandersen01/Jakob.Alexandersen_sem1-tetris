package no.uib.inf101.tetris.model.tetromino;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.GridCell;
import no.uib.inf101.grid.GridDimension;

public final class Tetromino implements Iterable<GridCell<Character>>{
    
    public char c;
    public boolean[][] shape;
    public CellPosition pos;


    public Tetromino(char c, boolean[][] shape, CellPosition pos) {
        this.c = c;
        this.shape = shape;
        this.pos = pos;

    }
    /**
     * Returns a new tetromino based on the char c
     * @param c
     * @return a new tetromino
     */
    static public Tetromino newTetromino(char c) {

        switch(c) {
            case 'I':
                return new Tetromino('I', new boolean[][]{{false, false, false, false},
                                                            {true, true, true, true}, 
                                                            {false, false, false, false},
                                                            {false, false, false, false}},
                                                    new CellPosition(0, 0));
            case 'J':
                return new Tetromino('J', new boolean[][]{{false, false, false}, 
                                                            {true, true, true},
                                                            {false, false, true}}, 
                                                    new CellPosition(0, 0));
            case 'L':
                return new Tetromino('L', new boolean[][]{{false, false, false}, 
                                                            {true, true, true},
                                                            {true, false, false}}, 
                                                        new CellPosition(0, 0));
            case 'O':
                return new Tetromino('O', new boolean[][]{{false, false, false, false}, 
                                                            {false, true, true, false},
                                                            {false, true, true, false},
                                                            {false, false, false, false}}, 
                                                        new CellPosition(0, 0));
            case 'S':
                return new Tetromino('S', new boolean[][]{{false, false, false}, 
                                                            {false, true, true},
                                                            {true, true, false}}, 
                                                        new CellPosition(0, 0));
            case 'T':
                return new Tetromino('T', new boolean[][]{{false, false, false}, 
                                                            {true, true, true},
                                                            {false, true, false}}, 
                                                        new CellPosition(0, 0));
            case 'Z':
                return new Tetromino('Z', new boolean[][]{{false, false, false}, 
                                                            {true, true, false},
                                                            {false, true, true}}, 
                                                        new CellPosition(0, 0));
            default:
                throw new IllegalArgumentException("Invalid char");
        }

    }

    /**
     * Returns a new tetromino with the same shape and color, but shifted by deltaRow and deltaCol
     * @param deltaRow
     * @param deltaCol
     * @return new tetromino that is shifted
     */
    public Tetromino shiftedBy(int deltaRow, int deltaCol) {
        return new Tetromino(c, shape, pos.shiftedBy(deltaRow, deltaCol));
    }


    /**
     * Puts the tetromino in the top center of the grid
     * @param dim
     * @return a correctly positioned tetromino
     */
    public Tetromino shiftedToTopCenterOf(GridDimension dim) {

        int centerCol = dim.cols() / 2;
        int leftCol = centerCol - shape.length / 2;
        int topRow = -1;
    
        return new Tetromino(c, shape, new CellPosition(topRow, leftCol));

    }

    @Override
    public Iterator<GridCell<Character>> iterator() {
        //create a list of GridCell<Character>-objects
        //use a double for-loop to iterate over the shape-array
        //if the current shape is true, calculate the row and column of the cell and add it to the list
        List<GridCell<Character>> list = new ArrayList<>();
        for(int i = 0; i < shape.length; i++) {
            for(int j = 0; j < shape[i].length; j++) {
                if(shape[i][j]) {
                    list.add(new GridCell<>(pos.shiftedBy(i, j), c));
                }
            }
        }
        return list.iterator();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + c;
        result = prime * result + Arrays.deepHashCode(shape);
        result = prime * result + ((pos == null) ? 0 : pos.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Tetromino other = (Tetromino) obj;
        if (c != other.c)
            return false;
        if (!Arrays.deepEquals(shape, other.shape))
            return false;
        if (pos == null) {
            if (other.pos != null)
                return false;
        } else if (!pos.equals(other.pos))
            return false;
        return true;
    }

    /**
     * Returns a new tetromino that is rotated 90 degrees clockwise
     */
    public Tetromino rotated() {
        boolean[][] newShape = new boolean[shape[0].length][shape.length];
        for(int i = 0; i < shape.length; i++) {
            for(int j = 0; j < shape[i].length; j++) {
                newShape[j][shape.length - i - 1] = shape[i][j];
            }
        }
        return new Tetromino(c, newShape, pos);
    }
    
}
