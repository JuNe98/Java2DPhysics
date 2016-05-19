package java2dsimulator;


/**
 *
 * @author Julian Nenning, Matthias Stirmayr
 */
public class Particle{
    private double x;
    private double y;
    
    public Particle(double x, double y) {
        this.x = x;
        this.y = y;
        
    }
    public double getX(){
        return this.x;
    }
    public double getY(){
        return this.y;
    }
}
