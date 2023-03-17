package no.uib.inf101.tetris.view;

import java.awt.Color;

public class DefaultColorTheme implements ColorTheme{

    @Override
    public Color getCellColor(char c) {
        Color color = switch(c) {
            case 'I' -> Color.decode("#6BFF00");
            case 'J' -> Color.decode("#FF0000");
            case 'L' -> Color.decode("#00FFF6");
            case 'O' -> Color.decode("#0042FF");
            case 'S' -> Color.decode("#7500FF");
            case 'T' -> Color.decode("#FC00FF");
            case 'Z' -> Color.decode("#FF8700");
            
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
        return new Color(41, 42, 28, 128);
        
    }
    
    @Override
    public Color gameOverColor() {
        return new Color(0, 0, 0, 128);
    }



}
