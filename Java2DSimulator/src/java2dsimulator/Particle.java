package java2dsimulator;

import javafx.scene.image.Image;
import org.dyn4j.dynamics.BodyFixture;
import org.dyn4j.geometry.Circle;
import org.dyn4j.geometry.Convex;

/**
 *
 * @author Julian
 */
public class Particle extends PhysObj{
    private double radius = 0.075;
    private Circle circle = new Circle(radius);

    public Particle(Image i) {
        super(i);
    }
    
    
}
