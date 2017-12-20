package net.coderodde.math.analyticgeometry;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

/**
 * This object holds all the parameters describing a (possibly rotated) 
 * parabola.
 * 
 * @author Rodion "rodde" Efremov
 * @version 1.6 (Dec 20, 2017)
 */
public final class ObservableParabola {
    
    private final Point2D.Double vertex;
    private double alpha;
    private double beta;
    private final List<ParabolaParameterListener> parameterListeners = 
            new ArrayList<>();
    
    public ObservableParabola() {
        this.vertex = new Point2D.Double();
    }
    
    public ObservableParabola(double x, double y, double alpha, double beta) {
        this();
        setVertex(x, y);
        setAlpha(alpha);
        setBeta(beta);
    }
    
    public void setVertex(double x, double y) {
        this.vertex.x = checkX(x);
        this.vertex.y = checkY(y);
        notifyAllListeners();
    }
    
    public void setAlpha(double alpha) {
        this.alpha = checkAlpha(alpha);
        notifyAllListeners();
    }
    
    public void setBeta(double beta) {
        this.beta = checkBeta(beta);
        notifyAllListeners();
    }
    
    public double getVertexX() {
        return vertex.x;
    }
    
    public double getVertexY() {
        return vertex.y;
    }
    
    public double getAlpha() {
        return alpha;
    }
    
    public double getBeta() {
        return beta;
    }
    
    public void addParameterListener(ParabolaParameterListener listener) {
        if (listener != null) {
            this.parameterListeners.add(listener);
        }
    }
    
    public void removeParameterListener(ParabolaParameterListener listener) {
        this.parameterListeners.remove(listener);
    }
    
    private double checkX(double x) {
        return checkNotNaN(x, "The x-coordinate is NaN.");
    }
    
    private double checkY(double y) {
        return checkNotNaN(y, "The y-coordinate is NaN.");
    }
    
    private double checkAlpha(double alpha) {
        return checkNotNaN(alpha, "The alpha value is NaN.");
    }
    
    private double checkBeta(double beta) {
        return checkNotNaN(beta, "The beta value is NaN.");
    }
    
    private double checkNotNaN(double coordinate, String errorMessage) {
        if (Double.isNaN(coordinate)) {
            throw new IllegalArgumentException(errorMessage);
        }
        
        return coordinate;
    }
    
    private void notifyAllListeners() {
        parameterListeners.forEach((listener) -> {
            listener.onParabolaParameterChange();
        });
    }
}
