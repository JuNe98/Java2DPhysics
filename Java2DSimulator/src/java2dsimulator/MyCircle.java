package java2dsimulator;

import java.util.ArrayList;

/**
 * A Generator Class for a Big cricle
 * @author Stirmayr Matthias
 */
public class MyCircle implements Object2D {
    /**
     * The radius of the Cicle
    */
    private int radius;
    /**
     * List of the Generated Circles.
     */
    private ArrayList<Particle> circles = new ArrayList<Particle>();
    /**
     * HoizontalMouse Position
     */
    private double MouseX;
    /**
     * Vertical  Mouse Position
     */
    private double MouseY;
    /**
     * Simple Constructor
     * @param radius Radius of the Circle
     * @param MouseX Horizontal Mouse position
     * @param MouseY Vertical Mouse Position
     */
    public MyCircle(int radius, double MouseX, double MouseY) {
        this.radius = radius;
        this.MouseX = MouseX;
        this.MouseY = MouseY;
    }
    /**
     * Getter for Radius
     * @return Radius
     */
    public int getRadius() {
        return radius;
    }
    /**
     * Setter for Radius
     * @param radius 
     */
    public void setRadius(int radius) {
        this.radius = radius;
    }

    /**
     * Used to Trigger Generation and then return the Circles
     * @return 
     */
    @Override
    public ArrayList<Particle> getCircles() {
        genCircli();
        return circles;
    }
    /**
     * Generation of the Circl, usses Membervaraibls.
     */
    private void genCircli() {
        int d = (5 - radius * 4) / 4;
        int x = 0;
        int y = radius;

        do {
            circles.add(new Particle(translateWidth((MouseX + (17*x))), translateHeight(MouseY + (17*y))));
            circles.add(new Particle(translateWidth((MouseX + (17*x))), translateHeight(MouseY - (17*y))));
            circles.add(new Particle(translateWidth((MouseX - (17*x))), translateHeight(MouseY + (17*y))));
            circles.add(new Particle(translateWidth((MouseX - (17*x))), translateHeight(MouseY - (17*y))));
            circles.add(new Particle(translateWidth((MouseX + (17*y))), translateHeight(MouseY + (17*x))));
            circles.add(new Particle(translateWidth((MouseX + (17*y))), translateHeight(MouseY - (17*x))));
            circles.add(new Particle(translateWidth((MouseX - (17*y))), translateHeight(MouseY + (17*x))));
            circles.add(new Particle(translateWidth((MouseX - (17*y))), translateHeight(MouseY - (17*x))));
            
            if (d < 0) {
                d += 2 * x + 1;
            } else {
                d += 2 * (x - y) + 1;
                y--;
            }
            x++;
        } while (x <= y);
    }
    /**
     * Translate Pixel to Internal Cordinate System.
     * @param x
     * @return 
     */
    public double translateWidth(double x) {
        double rx = 0d;
        if (x > (Settings.SCENE_WIDTH / 2.0)) {
            rx = (x - Settings.SCENE_WIDTH / 2.0) / 64.0 - 0.0625;
        } else if (x < (Settings.SCENE_WIDTH / 2.0)) {
            rx = -((Settings.SCENE_WIDTH / 2.0 - x) / 64.0) - 0.0625;
           
        }
        return rx;
    }
    /**
     * Translate Pixel to Internal Cordinate System.
     * @param y
     * @return 
     */
    public double translateHeight(double y){
        double ly = (Settings.SCENE_HEIGHT - y) / 64.0 - 0.0625;
        return ly;
    }

}
