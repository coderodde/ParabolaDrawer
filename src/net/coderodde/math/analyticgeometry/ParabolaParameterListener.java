package net.coderodde.math.analyticgeometry;

/**
 * This interface defines the API for the listener objects that are notified 
 * whenever the parabola parameters change.
 * 
 * @author Rodion "rodde" Efremov
 * @version 1.6 (Dec 20, 2017)
 */
public interface ParabolaParameterListener {
    
    /**
     * Called whenever the parabola being listened to change its state.
     */
    public void onParabolaParameterChange();
}
