package no.uib.inf101.tetris;

import javax.swing.JFrame;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.tetris.model.TetrisBoard;
import no.uib.inf101.tetris.model.TetrisModel;
import no.uib.inf101.tetris.view.TetrisView;


public class TetrisMain {
  public static final String WINDOW_TITLE = "INF101 Tetris";
  

  public static void main(String[] args) {

    TetrisBoard board = new TetrisBoard(20, 10);

    //create green tile at position (0,0)
    board.set(new CellPosition(0, 0), 'g');
    board.set(new CellPosition(0, 9), 'y');
    board.set(new CellPosition(19, 0), 'b');
    board.set(new CellPosition(19, 9), 'r');


    TetrisModel model = new TetrisModel(board);
    TetrisView view = new TetrisView(model);

    //TetrisBoard board = new TetrisBoard(15, 10);

    //TetrisModel model = new TetrisModel(board, new RandomTetrinoFactory());

    //new TetrisController(model, view);

    // The JFrame is the "root" application window.
    // We here set som properties of the main window, 
    // and tell it to display our tetrisView
    JFrame frame = new JFrame(WINDOW_TITLE);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    // Here we set which component to view in our window
    frame.setContentPane(view);
    
    // Call these methods to actually display the window
    frame.pack();
    frame.setVisible(true);
  }
  
}
