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

    /**
     * After this number of milliseconds, the speed of shifting/moving
     * increases.
     */
    private static final long INCREASE_SPEED_MILLISECONDS = 3000L;
    private static final long INCREASE_SPEED_KEYS_TYPED = 6;
    private static final double SPEEDUP_FACTOR = 5.0;
    
    
    private ParabolaPanel parabolaPanel;
    private volatile boolean shiftKeyPressed;
    private volatile long shiftKeyPressTime;
    private volatile long keysTypedAtShiftPress;
    private volatile long keysTyped;
    
    public void setParabolaPanel(ParabolaPanel parabolaPanel) {
        this.parabolaPanel = parabolaPanel;
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        keysTyped++;
        
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                if (shiftKeyPressed) {
                    double x = parabolaPanel.getParabola().getVertexX();
                    double y = parabolaPanel.getParabola().getVertexY();
                    double speed = getSpeed();
                    parabolaPanel.getParabola().setVertex(x, y - speed);
                } else {
                    parabolaPanel.setCenterY(parabolaPanel.getCenterY() - 0.01);
                    parabolaPanel.repaint();
                }
                
                break;
                
            case KeyEvent.VK_DOWN:
                if (shiftKeyPressed) {
                    double x = parabolaPanel.getParabola().getVertexX();
                    double y = parabolaPanel.getParabola().getVertexY();
                    double speed = getSpeed();
                    parabolaPanel.getParabola().setVertex(x, y + speed);
                } else {
                    parabolaPanel.setCenterY(parabolaPanel.getCenterY() + 0.01);
                    parabolaPanel.repaint();
                }
                    
                break;
                
            case KeyEvent.VK_LEFT:
                if (shiftKeyPressed) {
                    double x = parabolaPanel.getParabola().getVertexX();
                    double y = parabolaPanel.getParabola().getVertexY();
                    double speed = getSpeed();
                    parabolaPanel.getParabola().setVertex(x - speed, y);
                } else {
                    parabolaPanel.setCenterX(parabolaPanel.getCenterX() - 0.01);
                    parabolaPanel.repaint();
                }
                    
                break;
                
            case KeyEvent.VK_RIGHT:
                if (shiftKeyPressed) {
                    double x = parabolaPanel.getParabola().getVertexX();
                    double y = parabolaPanel.getParabola().getVertexY();
                    double speed = getSpeed();
                    parabolaPanel.getParabola().setVertex(x + speed, y);
                } else {
                    parabolaPanel.setCenterX(parabolaPanel.getCenterX() + 0.01);
                    parabolaPanel.repaint();
                }
                    
                break;
                
            case KeyEvent.VK_W:
                if (shiftKeyPressed) {
                    double currentRotationAngle = 
                            parabolaPanel.getParabola().getAlpha();
                    
                    double nextRotationAngle = currentRotationAngle +
                                               Math.PI / 180.0;
                    
                    parabolaPanel.getParabola().setAlpha(nextRotationAngle);
                } else {
                    double zoom = parabolaPanel.getUnitsPerPixel();
                    parabolaPanel.setUnitsPerPixel(zoom * 1.1);
                    parabolaPanel.repaint();
                }
                
                break;

            case KeyEvent.VK_S:
                if (shiftKeyPressed) {
                    double currentRotationAngle = 
                            parabolaPanel.getParabola().getAlpha();
                    
                    double nextRotationAngle = currentRotationAngle -
                                               Math.PI / 180.0;
                    
                    parabolaPanel.getParabola().setAlpha(nextRotationAngle);
                } else {
                    double zoom = parabolaPanel.getUnitsPerPixel();
                    parabolaPanel.setUnitsPerPixel(zoom / 1.1);
                    parabolaPanel.repaint();
                }
                break;
                
            case KeyEvent.VK_SHIFT:
                shiftKeyPressed = true;
                shiftKeyPressTime = System.currentTimeMillis();
                keysTypedAtShiftPress = keysTyped;
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
    
    private double getSpeed() {
        long timeElapsedSinceShiftKeyPressed = System.currentTimeMillis() - 
                                               shiftKeyPressTime;
        
        long currentKeysTyped = keysTyped - keysTypedAtShiftPress;
        
        return timeElapsedSinceShiftKeyPressed < INCREASE_SPEED_MILLISECONDS &&
               currentKeysTyped >= INCREASE_SPEED_KEYS_TYPED ?
               parabolaPanel.getUnitsPerPixel() * SPEEDUP_FACTOR :
               parabolaPanel.getUnitsPerPixel();
    }
}
