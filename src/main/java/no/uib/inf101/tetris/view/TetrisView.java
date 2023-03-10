package no.uib.inf101.tetris.view;

import javax.swing.JPanel;

import no.uib.inf101.grid.GridCell;
import no.uib.inf101.tetris.model.GameState;

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
    //get rowsRemoved from TetrisModel

    
    
    // Constructor
    /**
     * Create a the window for the game
     * @param model
     */
    public TetrisView(ViewableTetrisModel model) {

        Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) screenSize.getWidth();
        int height = (int) screenSize.getHeight();

        colorTheme = new DefaultColorTheme();
        Color backgroundColor = colorTheme.getBackgroundColor();
        this.setBackground(backgroundColor);

        this.model = model;
        this.setFocusable(true);
        this.setPreferredSize(new Dimension(width/3, height));
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



    /**
     * Draw the game
     * @param g
     */
    public void drawGame(Graphics2D g) {
        //get color from colorTheme
        Color frameColor = colorTheme.getBackgroundColor();
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
        drawCells(g, this.model.getTilesOnTetromino(), cps, this.colorTheme);
        if(this.model.getGameState() == GameState.GAME_OVER) {
            drawGameOver(g);
        }
        if(this.model.getGameState() == GameState.ACTIVE_STATE){
            frameCounter(g);

        }
        if(this.model.getGameState() == GameState.PAUSED){
            drawPause(g);

        }


        
    }

    /**
     * Draw the cells
     * @param g
     * @param grid
     * @param cps
     * @param colorTheme
     */
    public static void drawCells(Graphics2D g, Iterable<GridCell<Character>> grid, CellPositionToPixelConverter cps, ColorTheme colorTheme) {
        
        for (GridCell<Character> cell : grid) {
            Rectangle2D bounds = cps.getBoundsForCell(cell.pos());
            Color color = colorTheme.getCellColor(cell.value());
            g.setColor(color);
            g.fill(bounds);
        }
    }


    /**
     * Draw game over message if {@code model.getGameState() == GameState.GAME_OVER}
     * @param g
     */
    public void drawGameOver(Graphics2D g) {
        //get color from colorTheme
        Color frameColor = colorTheme.gameOverColor();
        double margin = TetrisView.OUTERMARGIN;
        double x = TetrisView.dimX;
        double y = TetrisView.dimY;

        double width = this.getWidth() - 2 * margin;
        double height = this.getHeight() - 2 * margin;
        
        Rectangle2D rect = new Rectangle2D.Double(x, y, width, height);


        
            g.setColor(frameColor);
            g.fill(rect);
            g.setColor(Color.WHITE);
            g.setFont(g.getFont().deriveFont(50.0f));
            g.drawString("Game Over!", 170, 400);

}
    /**
     * Draw pause message if {@code model.getGameState() == GameState.PAUSED}
     * @param g
     */
    public void drawPause(Graphics2D g) {
        //get color from colorTheme
        Color frameColor = colorTheme.gameOverColor();
        double margin = TetrisView.OUTERMARGIN;
        double x = TetrisView.dimX;
        double y = TetrisView.dimY;

        double width = this.getWidth() - 2 * margin;
        double height = this.getHeight() - 2 * margin;
        
        Rectangle2D rect = new Rectangle2D.Double(x, y, width, height);

        g.setColor(frameColor);
        g.fill(rect);
        g.setColor(Color.WHITE);
        g.setFont(g.getFont().deriveFont(50.0f));
        g.drawString("Paused", 215, 400);
        g.drawString("Press 'P' to continue", 55, 500);
    }

    /**
     * Draws the frame counter. 
     * NOTE: The counter is static at 144 fps, and is only used for "testing purposes"
     * @param g
     */
    public void frameCounter(Graphics2D g){
        g.setColor(Color.GREEN);
        g.setFont(g.getFont().deriveFont(14.0f));
        g.drawString("FPS: 144", 10, 20);
    }



}
