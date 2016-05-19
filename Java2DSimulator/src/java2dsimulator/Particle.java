package java2dsimulator;

import java.awt.Point;

/**
 *
 * @author Julian Nenning
 */
public class Particle{
    private Point point = new Point();

    public Particle(double x, double y) {
        point.setLocation(x, y);
    }
    public double getX(){
        return point.getLocation().getX();
    }
    public double getY(){
        return point.getLocation().getY();
    }
}
