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
public final class ParabolaDrawerApp extends JFrame {

    private static final double DEFAULT_VERTEX_X = 0.0;
    private static final double DEFAULT_VERTEX_Y = 0.0;
    private static final double DEFAULT_ALPHA = 0.0;
    private static final double DEFAULT_BETA = 1.0;
    
    public ParabolaDrawerApp() {
        Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
        ObservableParabola parabola = new ObservableParabola(DEFAULT_VERTEX_X,
                                                             DEFAULT_VERTEX_Y,
                                                             DEFAULT_ALPHA,
                                                             DEFAULT_BETA);
        ParabolaPanel panel = new ParabolaPanel(parabola);
        ParabolaPanelMouseListener mouseListener = 
                new ParabolaPanelMouseListener(panel);
        ParabolaPanelKeyListener keyListener = new ParabolaPanelKeyListener();
        keyListener.setParabolaPanel(panel);
        getContentPane().add(panel);
        
        parabola.addParameterListener(panel);
        panel.addKeyListener(keyListener);
        panel.addMouseListener(mouseListener);
        panel.addMouseMotionListener(mouseListener);
        panel.setFocusable(true);
        panel.requestFocus();
        
        
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(screenDimension);
        setResizable(false);
        setVisible(true);
    }
    
    public static void main(String[] args) {
        ParabolaDrawerApp drawer = new ParabolaDrawerApp();
    }
}
