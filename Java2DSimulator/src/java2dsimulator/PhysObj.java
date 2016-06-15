package java2dsimulator;

import org.dyn4j.dynamics.Body;
import org.dyn4j.geometry.Transform;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.Iterator;
import javafx.geometry.Rectangle2D;

/**
 * Extendet Body Class which we used for showing the Images and apply the
 * Movement to the Images
 *
 * @author Stirmayr Matthias
 */
public class PhysObj extends Body {

    /**
     * List of Bodies
     */
    static ArrayList<PhysObj> bodies = new ArrayList<PhysObj>();
    /**
     * Mainpane which is used to show the elements
     */
    static Pane mainPane;
    /**
     * Imageview where the Image gets shown.
     */
    ImageView iv = null;

    /**
     * Constructor One for nonvisible Objects
     */
    public PhysObj() {
        super();
        transform = getTransform();
        bodies.add(this);
    }

    /**
     * Construtor Two which also uses Constructor One and sets the Image and
     * adds it to the mainPane
     *
     * @param i Image
     */
    public PhysObj(Image i) {
        this();
        iv = new ImageView();
        Rectangle2D viewPort = new Rectangle2D(0, 0, i.getWidth(), i.getHeight());
        iv.setImage(i);
        mainPane.getChildren().add(iv);
    }

    /**
     * Setter for Mainpane
     *
     * @param mp
     */
    public static void setMainPane(Pane mp) {
        mainPane = mp;
    }

    /**
     * updates all visual items
     */
    public static void update() {
        Iterator<PhysObj> pi = bodies.iterator();
        while (pi.hasNext()) {
            PhysObj po = pi.next();
            if (po.iv != null) {
                Transform t = po.getTransform();
                po.iv.setTranslateX(t.getTranslationX() * Settings.SCALE);
                po.iv.setTranslateY(t.getTranslationY() * Settings.SCALE);
                po.iv.setRotate(po.transform.getRotation() / 0.017453292519943295); //PI / 180
            }
        }
    }
}
