package net.coderodde.math.analyticgeometry;

import java.util.Objects;

/**
 * This class implements a rotate parabola function.
 * 
 * @author Rodion "rodde" Efremov
 * @version 1.6 (Dec 20, 2017)
 */
public class ParabolaFunction implements RealFunction {

    private final ObservableParabola parabola;
    
    public ParabolaFunction(ObservableParabola parabola) {
        this.parabola = Objects.requireNonNull(parabola, 
                                               "The input parabola is null.");
    }
    
    @Override
    public double calculateY(double x) {
        double alpha = parabola.getAlpha();
        double beta = parabola.getBeta();
        double xV = parabola.getVertexX();
        double yV = parabola.getVertexY();
        
        double tDiscriminant = computeDiscriminant(alpha, beta, x, xV);
        double tLower = quadratic(alpha, beta, -tDiscriminant);
        double tUpper = quadratic(alpha, beta, +tDiscriminant);
        
        double lengthVS = 
                (x - xV) * Math.sqrt(1 + Math.pow(Math.tan(alpha), 2.0));
        
        double yS = (x - xV) * Math.tan(alpha) + yV;
        double a = lengthVS - tUpper;
        double b = beta * tUpper * tUpper;
        
        return yS + Math.sqrt(a * a + b * b);
    }
    
    private double computeDiscriminant(double alpha,
                                       double beta,
                                       double x,
                                       double xV) {
        double result = 0.0;
        result += Math.pow(Math.cos(alpha), 2.0);
        result += 4.0 * beta * Math.sin(alpha) * (xV - x);
        return Math.sqrt(result);
    }
    
    private double quadratic(double alpha, double beta, double discriminant) {
        return (Math.cos(alpha) + discriminant) / 
               (2.0 * beta * Math.sin(alpha));
    }
}
