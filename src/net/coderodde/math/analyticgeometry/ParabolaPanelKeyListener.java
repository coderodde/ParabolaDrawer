package net.coderodde.math.analyticgeometry;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * This class implements a key listener for the {@link ParabolaPanel}.
 * 
 * @author Rodion "rodde" Efremov
 * @version 1.6 (Dec 20, 2017)
 */
public class ParabolaPanelKeyListener implements KeyListener {

    private ParabolaPanel parabolaPanel;
    private volatile boolean shiftKeyPressed;
    
    public void setParabolaPanel(ParabolaPanel parabolaPanel) {
        this.parabolaPanel = parabolaPanel;
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                if (shiftKeyPressed) {
                    double x = parabolaPanel.getParabola().getVertexX();
                    double y = parabolaPanel.getParabola().getVertexY();
                    parabolaPanel.getParabola()
                                 .setVertex(
                                         x, 
                                         y - parabolaPanel.getUnitsPerPixel());
                } else {
                    parabolaPanel.setCenterY(parabolaPanel.getCenterY() - 0.01);
                    parabolaPanel.repaint();
                }
                
                break;
                
            case KeyEvent.VK_DOWN:
                if (shiftKeyPressed) {
                    double x = parabolaPanel.getParabola().getVertexX();
                    double y = parabolaPanel.getParabola().getVertexY();
                    parabolaPanel.getParabola()
                                 .setVertex(
                                         x,
                                         y + parabolaPanel.getUnitsPerPixel());
                } else {
                    parabolaPanel.setCenterY(parabolaPanel.getCenterY() + 0.01);
                    parabolaPanel.repaint();
                }
                    
                break;
                
            case KeyEvent.VK_LEFT:
                if (shiftKeyPressed) {
                    double x = parabolaPanel.getParabola().getVertexX();
                    double y = parabolaPanel.getParabola().getVertexY();
                    parabolaPanel.getParabola()
                                 .setVertex(
                                         x - parabolaPanel.getUnitsPerPixel(),
                                         y);
                } else {
                    parabolaPanel.setCenterX(parabolaPanel.getCenterX() - 0.01);
                    parabolaPanel.repaint();
                }
                    
                break;
                
            case KeyEvent.VK_RIGHT:
                if (shiftKeyPressed) {
                    double x = parabolaPanel.getParabola().getVertexX();
                    double y = parabolaPanel.getParabola().getVertexY();
                    parabolaPanel.getParabola()
                                 .setVertex(
                                         x + parabolaPanel.getUnitsPerPixel(),
                                         y);
                } else {
                    parabolaPanel.setCenterX(parabolaPanel.getCenterX() + 0.01);
                    parabolaPanel.repaint();
                }
                    
                break;
                
            case KeyEvent.VK_W:
                double zoom = parabolaPanel.getUnitsPerPixel();
                parabolaPanel.setUnitsPerPixel(zoom * 1.1);
                parabolaPanel.repaint();
                break;

            case KeyEvent.VK_S:
                zoom = parabolaPanel.getUnitsPerPixel();
                parabolaPanel.setUnitsPerPixel(zoom / 1.1);
                parabolaPanel.repaint();
                break;
                
            case KeyEvent.VK_SHIFT:
                shiftKeyPressed = true;
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_SHIFT:
                shiftKeyPressed = false;
                break;
        }
    }
}
