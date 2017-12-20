package net.coderodde.math.analyticgeometry;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;

/**
 * This class sets up the facilities and provides the entry point of the 
 * program.
 * 
 * @author Rodion "rodde" Efremov
 * @version 1.6 (Dec 20, 2017)
 */
public class ParabolaDrawer extends JFrame {

    private static final double DEFAULT_VERTEX_X = 0.0;
    private static final double DEFAULT_VERTEX_Y = 0.0;
    private static final double DEFAULT_ALPHA = 0.0;
    private static final double DEFAULT_BETA = 1.0;
    
    private final ParabolaPanel panel;
    
    public ParabolaDrawer() {
        Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
        ObservableParabola parabola = new ObservableParabola(DEFAULT_VERTEX_X,
                                                             DEFAULT_VERTEX_Y,
                                                             DEFAULT_ALPHA,
                                                             DEFAULT_BETA);
        this.panel = new ParabolaPanel(parabola);
        this.getContentPane().add(this.panel);
        this.setSize(screenDimension);
        this.setResizable(false);
        this.setVisible(true);
    }
    
    public static void main(String[] args) {
        ParabolaDrawer drawer = new ParabolaDrawer();
    }
}
