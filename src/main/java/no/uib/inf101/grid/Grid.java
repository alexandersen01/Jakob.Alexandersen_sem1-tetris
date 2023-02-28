package no.uib.inf101.grid;

import java.util.ArrayList;
import java.util.Iterator;


public class Grid <E> implements IGrid <E> {

    //Field variables
    private int rows;
    private int cols;
    ArrayList<GridCell<E>> grid = new ArrayList<>();

    //Konstruktør #1
    public Grid (int rows, int cols) {
        this(rows, cols, null);
    }

    //Konstruktør #2
    public Grid(int rows, int cols, E defaultValue) {
        //Create a new grid with the given rows and cols
        this.rows = rows;
        this.cols = cols;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid.add(new GridCell<E>(new CellPosition(i, j), defaultValue));
            }
        }
        
	}


	@Override
    public int rows() {
        return rows;
    }

    @Override
    public int cols() {
        return cols;
    }

    @Override
    public Iterator<GridCell<E>> iterator() {
        return grid.iterator();
    }

    @Override
    public void set(CellPosition pos, E value) {
        grid.set(pos.row() * this.cols() + pos.col(), new GridCell<E>(pos, value));
    }

    @Override
    public E get(CellPosition pos) {
        return grid.get(pos.row() * this.cols() + pos.col()).value();
    }

    @Override
    public boolean positionIsOnGrid(CellPosition pos) {
        if (pos.row() < 0 || pos.row() >= rows || pos.col() < 0 || pos.col() >= cols) {
            return false;
        }
        return true;
    }

}
