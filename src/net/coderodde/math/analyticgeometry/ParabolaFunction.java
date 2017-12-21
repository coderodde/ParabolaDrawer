package net.coderodde.math.analyticgeometry;

import java.util.Objects;

/**
 * This class implements a rotate parabola function.
 * 
 * @author Rodion "rodde" Efremov
 * @version 1.6 (Dec 20, 2017)
 */
public class ParabolaFunction implements RealFunction {

    public static enum Part {
        PART_A,
        PART_B
    }
    
    private final double vertexX;
    private final double vertexY;
    private final double alpha;
    private final double beta;
    private final Part part;
    
    /**
     * Constructs one of the partial function.
     * 
     * @param parabola the target parabola.
     * @param part     the part selector (upper/lower).
     */
    public ParabolaFunction(ObservableParabola parabola, Part part) {
        this.part = Objects.requireNonNull(part, "The part selector is null.");
        this.vertexX = 
                Objects.requireNonNull(
                        parabola, 
                        "The input parabola is null.").getVertexX();
        this.vertexY = parabola.getVertexY();
        this.alpha = parabola.getAlpha();
        this.beta = parabola.getBeta();
    }
    
    /**
     * {@inheritDoc }
     */
    @Override
    public double calculateY(double x) {
        double tDiscriminant = computeDiscriminant(alpha, beta, x, vertexX);
        double t = 
                part == Part.PART_A ?
                quadratic(alpha, beta, -tDiscriminant) :
                quadratic(alpha, beta, +tDiscriminant);
        
        double lengthVS = 
                (x - vertexX) * Math.sqrt(1 + Math.pow(Math.tan(alpha), 2.0));
        
        double yS = (x - vertexX) * Math.tan(alpha) + vertexY;
        double a = lengthVS - t;
        double b = beta * t * t;
        
        return yS + Math.sqrt(a * a + b * b);
    }
    
    /**
     * Computes the discriminant of the quadratic equation over {@code t} 
     * value.
     * 
     * @param alpha the angle.
     * @param beta  the geometry factor.
     * @param x     the x-coordinate.
     * @param xV    the x-coordinate of the vertex of the parabola.
     * @return the discriminant.
     */
    private double computeDiscriminant(double alpha,
                                       double beta,
                                       double x,
                                       double xV) {
        double result = 0.0;
        result += Math.pow(Math.cos(alpha), 2.0);
        result += 4.0 * beta * Math.sin(alpha) * (xV - x);
        return Math.sqrt(result);
    }
    
    /**
     * Solves the quadratic equation over {@code t}.
     * 
     * @param alpha         the angle.
     * @param beta          the geometry factor.
     * @param discriminant  the value of the discriminant.
     * @return the value of {@code t}.
     */
    private double quadratic(double alpha, double beta, double discriminant) {
        return (Math.cos(alpha) + discriminant) / 
               (2.0 * beta * Math.sin(alpha));
    }
}
