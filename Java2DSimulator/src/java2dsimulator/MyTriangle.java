package java2dsimulator;

import java.util.ArrayList;
import org.dyn4j.geometry.Circle;

/**
 *
 * @author Julian Nenning
 */
public class MyTriangle implements Object2D{
    private int length;
    private ArrayList<Circle> circles;
    
    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    @Override
    public ArrayList<Circle> getCircles() {
        circles = new ArrayList<Circle>();
        // How many circles do we need = x
        int x = 0;
        for (int i = length; i > 0; i--) {
            x += i;
        }
        // generate circle coordinates and add them to circles...
        return circles;
    }
}
