package no.uib.inf101.tetris;

import javax.swing.JFrame;

import no.uib.inf101.tetris.view.SampleView;


public class TetrisMain {
  public static final String WINDOW_TITLE = "INF101 Tetris";
  
  public static void main(String[] args) {
    SampleView view = new SampleView();
    //TetrisBoard board = new TetrisBoard(15, 10);

    //TetrisModel model = new TetrisModel(board, new RandomTetrinoFactory());

    //TetrisView view = new TetrisView(model);
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
