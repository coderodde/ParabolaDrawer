package net.coderodde.math.analyticgeometry;

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
    
    private final double unitsPerPixel;
    private final double centerX;
    private final double centerY;
    private final ObservableParabola parabola;
    
    public ParabolaPanel(ObservableParabola parabola) {
        this.parabola = 
                Objects.requireNonNull(parabola, "The input parabola is null.");
        this.unitsPerPixel = DEFAULT_UNITS_PER_PIXEL;
        this.centerX = DEFAULT_CENTER_X;
        this.centerY = DEFAULT_CENTER_Y;
    }
    
    @Override
    public void paintComponent(Graphics g) {
        
    }

    @Override
    public void onParabolaParameterChange() {
        repaint();
    }
}
