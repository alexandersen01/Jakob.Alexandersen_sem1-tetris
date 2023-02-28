package no.uib.inf101.tetris.view;

import javax.swing.JPanel;

import no.uib.inf101.grid.GridCell;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.geom.Rectangle2D;


public class TetrisView extends JPanel {

    private ViewableTetrisModel model;
    private ColorTheme colorTheme;
    static final double OUTERMARGIN = 2;
    static final double dimX = 0;
    static final double dimY = 0;
    
    // Constructor
    public TetrisView(ViewableTetrisModel model) {

        colorTheme = new DefaultColorTheme();
        Color backgroundColor = colorTheme.getBackgroundColor();
        this.setBackground(backgroundColor);

        this.model = model;
        this.setFocusable(true);
        this.setPreferredSize(new Dimension(600, 1920));
    }

  // The paintComponent method is called by the Java Swing framework every time
  // either the window opens or resizes, or we call .repaint() on this object. 
  // Note: NEVER call paintComponent directly yourself
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        drawGame(g2);
    }


    //create method drawGame with param Graphics2D g to draw the elements of the game
    public void drawGame(Graphics2D g) {
        //get color from colorTheme
        Color frameColor = colorTheme.getFrameColor();
        double margin = TetrisView.OUTERMARGIN;
        double x = TetrisView.dimX;
        double y = TetrisView.dimY;
        double width = this.getWidth() - 2 * margin;
        double height = this.getHeight() - 2 * margin;
        
        Rectangle2D rect = new Rectangle2D.Double(x, y, width, height);
        g.setColor(frameColor);
        g.fill(rect);
        
        
        CellPositionToPixelConverter cps = new CellPositionToPixelConverter(rect, model.getDimension(), margin);
        
        //draw the cells
        drawCells(g, this.model.getTilesOnBoard(), cps, this.colorTheme);
        
    }

    //create method drawCells with param Graphics2D g2, Iterable<GridCell<Character>> cells, CellPositionToPixelConverter converter and ColorTheme colorTheme
    public static void drawCells(Graphics2D g, Iterable<GridCell<Character>> grid, CellPositionToPixelConverter cps, ColorTheme colorTheme) {
        
        for (GridCell<Character> cell : grid) {
            Rectangle2D bounds = cps.getBoundsForCell(cell.pos());
            Color color = colorTheme.getCellColor(cell.value());
            g.setColor(color);
            g.fill(bounds);
        }
    }
}
