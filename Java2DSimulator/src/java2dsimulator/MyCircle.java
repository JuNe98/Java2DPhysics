package java2dsimulator;

import java.util.ArrayList;

/**
 *
 * @author Stirmayr Matthias
 */
public class MyCircle implements Object2D {

    private int radius;
    private ArrayList<Particle> circles;
    private int MouseX;
    private int MouseY;

    public MyCircle(int radius, int MouseX, int MouseY) {
        this.radius = radius;
        this.MouseX = MouseX;
        this.MouseY = MouseY;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    @Override
    public ArrayList<Particle> getCircles() {
        genCircli();
        return circles;
    }

    private void genCircli() {
        int d = (5 - radius * 4) / 4;
        int x = 0;
        int y = radius;

        do {
            circles.add(new Particle(translateWidth((MouseX + (9*x))), translateWidth(MouseY + (9*y))));
            circles.add(new Particle(translateWidth((MouseX + (9*x))), translateWidth(MouseY - (9*y))));
            circles.add(new Particle(translateWidth((MouseX - (9*x))), translateWidth(MouseY + (9*y))));
            circles.add(new Particle(translateWidth((MouseX - (9*x))), translateWidth(MouseY - (9*y))));
            circles.add(new Particle(translateWidth((MouseX + (9*y))), translateWidth(MouseY + (9*x))));
            circles.add(new Particle(translateWidth((MouseX + (9*y))), translateWidth(MouseY - (9*x))));
            circles.add(new Particle(translateWidth((MouseX - (9*y))), translateWidth(MouseY + (9*x))));
            circles.add(new Particle(translateWidth((MouseX - (9*y))), translateWidth(MouseY - (9*x))));
            /**image.setPixel(centerX + x, centerY + y, circleColor);
            image.setPixel(centerX + x, centerY - y, circleColor);
            image.setPixel(centerX - x, centerY + y, circleColor);
            image.setPixel(centerX - x, centerY - y, circleColor);
            image.setPixel(centerX + y, centerY + x, circleColor);
            image.setPixel(centerX + y, centerY - x, circleColor);
            image.setPixel(centerX - y, centerY + x, circleColor);
            image.setPixel(centerX - y, centerY - x, circleColor);**/
            if (d < 0) {
                d += 2 * x + 1;
            } else {
                d += 2 * (x - y) + 1;
                y--;
            }
            x++;
        } while (x <= y);
    }

    public double translateWidth(int x) {
        double rx = 0;
        if (x > (Settings.SCENE_WIDTH / 2)) {
            rx = (x - Settings.SCENE_WIDTH / 2) / 64 - 0.0625;
        } else if (x < (Settings.SCENE_WIDTH / 2)) {
            rx = -((Settings.SCENE_WIDTH / 2 - x) / 64) - 0.0625;
           
        }
        return rx;
    }
    
    public double translateHeight(int y){
        double ly = (Settings.SCENE_HEIGHT - y) / 64 - 0.0625;
        return ly;
    }

}
