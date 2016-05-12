package java2dsimulator;

import java.util.ArrayList;
import org.dyn4j.geometry.Circle;

/**
 *
 * @author Julian Nenning
 */
public class MyCircle implements Object2D{
    private int radius;
    private ArrayList<Particle> circles;                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    @Override
    public ArrayList<Particle> getCircles() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    
}
