package no.uib.inf101.tetris.controller;

import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;

import javax.swing.Timer;

import no.uib.inf101.tetris.midi.TetrisSong;
import no.uib.inf101.tetris.model.GameState;
import no.uib.inf101.tetris.view.TetrisView;

public class TetrisController implements java.awt.event.KeyListener {
    
    public ControllableTetrisModel model;
    public TetrisView view;
    private Timer timer;
    private TetrisSong song = new TetrisSong();
    

    public TetrisController(ControllableTetrisModel model, TetrisView view) {
        this.model = model;
        this.view = view;
        view.addKeyListener(this);
        this.timer = new Timer(model.getTickTime(), this::clockTick);
        timer.start();
        song.run();
    }
    

    @Override
    public void keyPressed(KeyEvent e) {
        if(model.getGameState() == GameState.GAME_OVER) {
            return;
        }
        //if the key pressed is the left arrow key move the tetromino left
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            model.MoveTetromino(0, -1);
        }
        //if the key pressed is the right arrow key move the tetromino right
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            model.MoveTetromino(0, 1);
        }
        //if the key pressed is space, move the tetromino all the way down
        else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            model.DropTetromino();

        }
        //if the key pressed is the up arrow key, rotate the tetromino
        else if (e.getKeyCode() == KeyEvent.VK_UP) {
            model.RotateTetromino();
        }
        //move piece down one if down arrow key is pressed
        else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            model.MoveTetromino(1, 0);
        }

        //call on repaint to update the view
        view.repaint();
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }


    @Override
    public void keyReleased(KeyEvent e) {

    }

    public void clockTick(ActionEvent event){

        if(model.getGameState() != GameState.GAME_OVER) {
            model.clockTick();
            setDelay();
            view.repaint();
        }
    }

    //create helper method to get the right delay from the model and call on setDelay and setInitialDelay
    public void setDelay() {
        timer.setDelay(model.getTickTime());
        timer.setInitialDelay(model.getTickTime());
    }
}
