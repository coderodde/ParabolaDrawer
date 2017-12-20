package net.coderodde.math.analyticgeometry;

/**
 * This interface defines the API for the real-valued functions of a real 
 * variable.
 * 
 * @author Rodion "rodde" Efremov
 * @version 1.6 (Dec 20, 2017)
 */
public interface RealFunction {
    
    /**
     * Maps {@code x} to a corresponding {@code y}-value.
     * 
     * @param x the {@code x}-coordinate.
     * @return the {@code y}-coordinate.
     */
    public double calculateY(double x);
}
