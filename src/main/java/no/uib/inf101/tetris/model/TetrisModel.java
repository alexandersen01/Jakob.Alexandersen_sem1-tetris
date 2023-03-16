package no.uib.inf101.tetris.model;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.GridCell;
import no.uib.inf101.grid.GridDimension;
import no.uib.inf101.tetris.controller.ControllableTetrisModel;
import no.uib.inf101.tetris.model.tetromino.Tetromino;
import no.uib.inf101.tetris.model.tetromino.TetrominoFactory;
import no.uib.inf101.tetris.view.ViewableTetrisModel;


public class TetrisModel implements ViewableTetrisModel, ControllableTetrisModel {

    private TetrisBoard board;
    private TetrominoFactory factory;
    private Tetromino currentTetromino; //represents a falling tetromino
    public GameState state = GameState.ACTIVE_STATE;
    //get rowsRemoved from TetrisBoard
    public int rowsRemoved = 0;

    // Constructor with the parameter TetrisBoard
    public TetrisModel (TetrisBoard board, TetrominoFactory factory) {
        this.board = board;
        this.factory = factory;
        this.currentTetromino = factory.getNext().shiftedToTopCenterOf(board);
    }

    @Override
    public GridDimension getDimension() {
        return board; 
    }

    @Override
    public Iterable<GridCell<Character>> getTilesOnBoard() {
        return board;
    }

    @Override
    public Iterable<GridCell<Character>> getTilesOnTetromino() {
        return currentTetromino;
    }

    @Override
    public boolean MoveTetromino(int deltaRow, int deltaCol) {
        //use shiftedBy to make a copy of the tetromino
        Tetromino newTetromino = currentTetromino.shiftedBy(deltaRow, deltaCol);
        //check if the new tetromino is legal
        if(isLegal(newTetromino)) {
            //if it is legal, set the current tetromino to the new tetromino
            currentTetromino = newTetromino;
            return true;
        }
        return false;
    }


    /**
     * Checks if the tetromino is legal (as in within the board and not occupying any colored cells)
     * @param tetromino
     * @return true or false
     */
    public boolean isLegal(Tetromino tetromino) {
        //loop through the cells in tetromino
        for (GridCell<Character> cell : tetromino) {
            CellPosition pos = cell.pos();
            //check if the cell is within the board
            if(!board.positionIsOnGrid(pos) || board.get(pos) != '-'){
                return false;
            }
        
        }
        return true;
    }

    @Override
    public boolean RotateTetromino() {
        //use rotated to make a copy of the tetromino
        Tetromino newTetromino = currentTetromino.rotated();
        //check if the new tetromino is legal
        if(isLegal(newTetromino)) {
            //if it is legal, set the current tetromino to the new tetromino
            currentTetromino = newTetromino;
            return true;
        }
        return false;
    }

    @Override
    public boolean DropTetromino() {
        //move the tetromino all the way down
        while(MoveTetromino(1, 0)) {
            //do nothing
        }
        glueTetromino();
        board.removeRow();
        newTetromino();
        return true;
        
    }

    /**
     * Gets a new tetromino and checks if it is legal. If it is illegal, change {@link #state} to {@link GameState#GAME_OVER
     */
    public void newTetromino() {
        currentTetromino = factory.getNext().shiftedToTopCenterOf(board);
        //check if the new tetromino is legal
        if(!isLegal(currentTetromino)) {
            state = GameState.GAME_OVER;
        }
    }

    /**
     * Glues the tetromino to the board
     */
    public void glueTetromino() {
        for (GridCell<Character> cell : currentTetromino) {
            board.set(cell.pos(), cell.value());
        }
    }

    @Override
    public GameState getGameState() {
        return state;
    }

    @Override
    public void pause() {
        state = GameState.PAUSED;
    }


    @Override
    public void resume() {
        state = GameState.ACTIVE_STATE;
    }

    @Override
    public int getTickTime() {
        return 450 - 9 * board.rowsRemoved;
    }

    @Override
    public void clockTick() {
        //move the tetromino down one row
        //if it cannot move down, glue the tetromino to the board
        if(!MoveTetromino(1, 0)) {
            glueTetromino();
            rowsRemoved += board.removeRow();
            rowsRemoved = Math.min(80, rowsRemoved);
            newTetromino();

        }
    }

    @Override
    public int getrowsRemoved() {
        return rowsRemoved;
    }
    
}
