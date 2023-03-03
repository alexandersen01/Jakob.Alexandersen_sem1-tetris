package no.uib.inf101.tetris.view;

import java.awt.Color;

public interface ColorTheme {

    //create method getCellColor with param char and return Color

    /**
     * Get the color of a cell with the given character
     * @param c
     * @return a color
     */
    Color getCellColor(char c);

    //create method getFrameColor with no param and return Color
    /**
     * Get the color of the frame
     * @return a see through color
     */
    Color getFrameColor();

    //create method getBackgroundColor with no param and return the color black
    /**
     * Get the color of the background
     * @return a color
     */
    Color getBackgroundColor();

    //create method gameOverColor with no param and return a color
    /**
     * Get the color of the game over screen
     * @return a color
     */
    Color gameOverColor();
    
}
