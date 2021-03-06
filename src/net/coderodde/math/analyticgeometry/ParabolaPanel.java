package net.coderodde.math.analyticgeometry;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Objects;
import javax.swing.JPanel;

/**
 * This class implements a panel that draws the parabola.
 * 
 * @author Rodion "rodde" Efremov
 * @version 1.6 (Dec 20, 2017)
 */
public final class ParabolaPanel 
        extends JPanel 
        implements ParabolaParameterListener {
    
    private static final double DEFAULT_UNITS_PER_PIXEL = 0.002;
    private static final double DEFAULT_CENTER_X = 0.0;
    private static final double DEFAULT_CENTER_Y = 0.0;
    private static final Color DEFAULT_X_AXIS_COLOR = Color.RED;
    private static final Color DEFAULT_Y_AXIS_COLOR = Color.BLUE;
    private static final Color DEFAULT_PARABOLA_COLOR = Color.BLACK;
    private static final int CONTROL_BALL_RADIUS = 7; // 7 pixels.
    
    private double unitsPerPixel;
    private double centerX;
    private double centerY;
    private final ObservableParabola parabola;
    private Color xAxisColor;
    private Color yAxisColor;
    private Color parabolaColor;
    
    public ParabolaPanel(ObservableParabola parabola) {
        this.parabola = 
                Objects.requireNonNull(parabola, 
                                       "The input parabola is null.");
        this.unitsPerPixel = DEFAULT_UNITS_PER_PIXEL;
        this.centerX = DEFAULT_CENTER_X;
        this.centerY = DEFAULT_CENTER_Y;
        this.xAxisColor = DEFAULT_X_AXIS_COLOR;
        this.yAxisColor = DEFAULT_Y_AXIS_COLOR;
        this.parabolaColor = DEFAULT_PARABOLA_COLOR;
        this.setBackground(Color.WHITE);
    }
    
    @Override
    public void paintComponent(Graphics g) {
        paintBackground(g);
        paintAxes(g);
        paintParabola(g);
    }
    
    public double getUnitsPerPixel() {
        return unitsPerPixel;
    }
    
    public double getCenterX() {
        return centerX;
    }
    
    public double getCenterY() {
        return centerY;
    }
    
    public Color getXAxisColor() {
        return xAxisColor;
    }
    
    public Color getYAxisColor() {
        return yAxisColor;
    }
    
    public Color getParabolaColor() {
        return parabolaColor;
    }
    
    public void setUnitsPerPixel(double unitsPerPixel) {
        this.unitsPerPixel = checkUnitsPerPixel(unitsPerPixel);
    }
    
    public void setCenterX(double centerX) {
        this.centerX = checkNotNaN(centerX, "The x-coordinate is NaN.");
    }
    
    public void setCenterY(double centerY) {
        this.centerY = checkNotNaN(centerY, "The y-coordinate is NaN.");
    }
    
    public void setXAxisColor(Color color) {
        this.xAxisColor = Objects.requireNonNull(color, "The color is null.");
    }
    
    public void setYAxisColor(Color color) {
        this.yAxisColor = Objects.requireNonNull(color, "The color is null.");
    }
    
    public void setParabolaColor(Color color) {
        this.parabolaColor = 
                Objects.requireNonNull(color, "The color of the parabola.");
    }
    
    public ObservableParabola getParabola() {
        return parabola;
    }

    public boolean pointContainsParabolaVertex(int x, int y) {
        int vertexX = (getWidth() >>> 1) + 
                      (int)((parabola.getVertexX() - centerX) / unitsPerPixel);
        
        int vertexY = (getHeight() >>> 1) +
                      (int)((parabola.getVertexY() + centerY) / unitsPerPixel);
        
        int dx = vertexX - x;
        int dy = vertexY - y;
        
        return Math.sqrt(dx * dx + dy * dy) < CONTROL_BALL_RADIUS;
    }
    
    @Override
    public void onParabolaParameterChange() {
        repaint();
    }
    
    private double checkUnitsPerPixel(double unitsPerPixel) {
        if (Double.isNaN(unitsPerPixel)) {
            throw new IllegalArgumentException("unitsPerPixel is NaN.");
        }
        
        if (unitsPerPixel <= 0.0) {
            throw new IllegalArgumentException(
                    "unitsPerPixel is too small: " + unitsPerPixel + ". " +
                    "Must be positive.");
        }
        
        return unitsPerPixel;
    }
    
    private double checkNotNaN(double value, String errorMessage) {
        if (Double.isNaN(value)) {
            throw new IllegalArgumentException(errorMessage);
        }
        
        return value;
    }
    
    private void paintBackground(Graphics g) {
        g.clearRect(0, 0, getWidth(), getHeight());
    }
    
    private void paintAxes(Graphics g) {
        paintXAxis(g);
        paintYAxis(g);
    }
    
    private void paintParabola(Graphics g) {
       g.setColor(parabolaColor);
       RealFunction part1 = parabola.getPartialFunction1();
       RealFunction part2 = parabola.getPartialFunction2();
       int width = getWidth();
       int height = getHeight();
       int halfWidth = width >>> 1;
       int halfHeight = height >>> 1;
       int previousY = 0;
       double worldX = centerX - halfWidth * unitsPerPixel;
       double previousWorldY = part1.calculateY(worldX);
       double currentWorldY;
       
       // Draw the first part function:
       for (int x = 0; x < width; ++x) {
           // We could have written simply 'worldX += unitsPerPixel', but that
           // way (in principle) we would accumulate error in representation of
           // that variable.
           worldX = centerX + (x - halfWidth) * unitsPerPixel;
           currentWorldY = part1.calculateY(worldX);
           
           if (!Double.isNaN(currentWorldY) && !Double.isNaN(previousWorldY)) {
               int currentY = halfHeight + 
                             (int)((2 * parabola.getVertexY()
                                      - currentWorldY 
                                      - centerY) / unitsPerPixel);
               
               g.drawLine(x - 1, previousY, x, currentY);
               previousY = currentY;
           }
           
           previousWorldY = currentWorldY;
       }
       
       worldX = centerX - halfWidth * unitsPerPixel;
       previousWorldY = part2.calculateY(worldX);
       
       // Draw the second part function:
       for (int x = 0; x < width; ++x) {
           worldX = centerX + (x - halfWidth) * unitsPerPixel;
           currentWorldY = part2.calculateY(worldX);
           
           if (!Double.isNaN(currentWorldY) && !Double.isNaN(previousWorldY)) {
               int currentY = halfHeight +
                             (int)((2 * parabola.getVertexY()
                                      - currentWorldY
                                      - centerY) / unitsPerPixel);
               
               g.drawLine(x - 1, previousY, x, currentY);
               previousY = currentY;
           }
               
           previousWorldY = currentWorldY;
       }
      
       // Paint a small ball at the vertex of the parabola.
       int x = halfWidth + 
              (int)((parabola.getVertexX() - centerX) / unitsPerPixel);
       
       int y = halfHeight + 
              (int)((parabola.getVertexY() - centerY) / unitsPerPixel);
       
       g.fillOval(x - CONTROL_BALL_RADIUS, 
                  y - CONTROL_BALL_RADIUS,
                  2 * CONTROL_BALL_RADIUS, 
                  2 * CONTROL_BALL_RADIUS);
    }
    
    private void paintXAxis(Graphics g) {
        g.setColor(getXAxisColor());
        int width = getWidth();
        int height = getHeight();
        System.out.println("centerY: " + centerY);
        int targetHeight = (height >>> 1) - (int)(centerY / unitsPerPixel);
        g.drawLine(0, targetHeight, width, targetHeight);
    }
    
    private void paintYAxis(Graphics g) {
        g.setColor(getYAxisColor());
        int width = getWidth();
        int height = getHeight();
        int targetWidth = (width >>> 1) - (int)(centerX / unitsPerPixel);
        g.drawLine(targetWidth, 0, targetWidth, height);
    }
}
