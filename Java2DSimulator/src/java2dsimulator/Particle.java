package java2dsimulator;

import java.awt.Point;
import java.util.ArrayList;
import javafx.scene.image.Image;
import org.dyn4j.dynamics.BodyFixture;
import org.dyn4j.geometry.Circle;
import org.dyn4j.geometry.Convex;

/**
 *
 * @author Julian
 */
public class Particle{
    private Point point = new Point();

    public Particle(int x, int y) {
        point.setLocation(x, y);
    }
    
    public void setX(int x){
        this.point.x = x;
    }
    
    public void setY(int y){
        this.point.y = y;
    }
}
