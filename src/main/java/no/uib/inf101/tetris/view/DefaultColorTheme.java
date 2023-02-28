package no.uib.inf101.tetris.view;

import java.awt.Color;

public class DefaultColorTheme implements ColorTheme{

    @Override
    public Color getCellColor(char c) {
        Color color = switch(c) {
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
    
}
