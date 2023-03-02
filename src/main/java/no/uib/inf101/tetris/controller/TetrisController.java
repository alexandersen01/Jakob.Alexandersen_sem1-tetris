package no.uib.inf101.tetris.controller;

import java.awt.event.KeyEvent;

import no.uib.inf101.tetris.view.TetrisView;

public class TetrisController implements java.awt.event.KeyListener {
    
    public ControllableTetrisModel model;
    public TetrisView view;
    

    public TetrisController(ControllableTetrisModel model, TetrisView view) {
        this.model = model;
        this.view = view;
        view.addKeyListener(this);
    }
    

    @Override
    public void keyPressed(KeyEvent e) {
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
            while (model.MoveTetromino(1, 0)) {
                //do nothing
            }
        }
        //if the key pressed is the up arrow key, rotate the tetromino
        else if (e.getKeyCode() == KeyEvent.VK_UP) {
            model.RotateTetromino();
        }

        //call on repaint to update the view
        view.repaint();
    }


    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'keyTyped'");
    }


    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'keyReleased'");
    }
}
