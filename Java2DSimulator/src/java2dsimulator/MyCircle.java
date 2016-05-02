package java2dsimulator;

import java.util.ArrayList;
import org.dyn4j.geometry.Circle;

/**
 *
 * @author Julian Nenning
 */
public class MyCircle implements Object2D{
    private int radius;
    private ArrayList<Circle> circles;

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    @Override
    public ArrayList<Circle> getCircles() {
        circles = new ArrayList<Circle>();
        // How many circles do we need = x
        // generate circle coordinates and add them to circles...
        return circles;
    }
    
}
