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

//create helper method to check if tetromino within the board and not occupying any colored cells
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

    //create helper method to get a new tetromino
    public void newTetromino() {
        currentTetromino = factory.getNext().shiftedToTopCenterOf(board);
        //check if the new tetromino is legal
        if(!isLegal(currentTetromino)) {
            state = GameState.GAME_OVER;
        }
    }

    //create helper method to glue the tetromino to the board
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
    public int getTickTime() {
        return 450;
    }

    @Override
    public void clockTick() {
        //move the tetromino down one row
        MoveTetromino(1, 0);
        //if it cannot move down, glue the tetromino to the board
        if(!MoveTetromino(1, 0)) {
            glueTetromino();
            board.removeRow();
            newTetromino();
        }
    }
    

}
