package no.uib.inf101.tetris.view;

import java.awt.Color;

public class DefaultColorTheme implements ColorTheme{

    @Override
    public Color getCellColor(char c) {
        Color color = switch(c) {
            case 'I' -> Color.decode("#63145b");
            case 'J' -> Color.decode("#80014d");
            case 'L' -> Color.decode("#f23553");
            case 'O' -> Color.decode("#005169");
            case 'S' -> Color.decode("#569fb1");
            case 'T' -> Color.decode("#C90E9D");
            case 'Z' -> Color.decode("#C90E0E");
            
            case 'r' -> Color.RED;
            case 'g' -> Color.GREEN;
            case 'b' -> Color.BLUE;
            case 'y' -> Color.YELLOW;
            case '-' -> Color.BLACK;
            default -> throw new IllegalArgumentException("Unexpected value: " + c);
        };
        return color;
    }

    @Override
    public Color getFrameColor() {
        return new Color(0, 0, 0, 0);
    }

    @Override
    public Color getBackgroundColor() {
        return null;
    }
    
    @Override
    public Color gameOverColor() {
        return new Color(0, 0, 0, 128);
    }

}
