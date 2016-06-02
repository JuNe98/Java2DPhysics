package java2dsimulator;

import java.awt.Point;
import java.util.ArrayList;
import org.dyn4j.geometry.Circle;

/**
 *
 * @author Julian Nenning
 */
public class MySquare implements Object2D{
    private int length;
    private ArrayList<Particle> particles;
    private Point mouseposition = new Point();

    public MySquare(int length) {
        this.length = length;
    }
    
    public MySquare(int x, int y){
        mouseposition.setLocation(x, y);
    }
    
    public MySquare(int length, int x, int y){
        this.length = length;
        mouseposition.setLocation(x, y);
    }
    
    public int getLength() {
        return this.length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    @Override
    public ArrayList<Particle> getCircles() {
        particles = new ArrayList<Particle>();
        // How many circles do we need = count
        int count = this.length * this.length;
        
        // generate circle coordinates and add them to circles...
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                int x = 0;
                int y = 0;
                if(length%2==0){
                    if(i+1<length/2){
                        //y = (int) (Settings.SCENE_HEIGHT-(((int)mouseposition.getY())-(length/2)*8-(length/2-1)*1-4));
                        y = (int) mouseposition.getY()+(length/2)*8+(length/2-1)+4;
                    }else if(i+1>length/2){
                        //y = (int) (Settings.SCENE_HEIGHT-(((int)mouseposition.getY())+(length/2)*8+(length/2-1)*1-4));
                        y = (int) mouseposition.getY()-(length/2)*8-(length/2-1)+4;
                    }
                    if(j+1<length/2){
                        //x = ((int)mouseposition.getX())-(length/2)*8-(length/2-1)*1-4;
                        x = (int) mouseposition.getX() - (length/2)*8 - (length/2-1)*1 - 4;
                    }else if(j+1>length/2){
                        //x = ((int)mouseposition.getX())+(length/2)*8+(length/2-1)*1-4;
                        x = (int) mouseposition.getX() + (length/2)*8 + (length/2-1)*1 - 4;
                    }
                    
                } else{
                    if(i+1<length/2){
                        //y = (int) (Settings.SCENE_HEIGHT-(((int)mouseposition.getY())-(length/2)*8-(length/2-1)*1-4));
                        y = (int) mouseposition.getY()+(length/2-i)*8+(length/2-i)+4;
                    }else if(i+1>length/2){
                        //y = (int) (Settings.SCENE_HEIGHT-(((int)mouseposition.getY())+(length/2)*8+(length/2-1)*1-4));
                        y = (int) mouseposition.getY()-((length/2-(i-length/2))-1)*8-(length/2-(i-length/2))+4;
                        if(i+1 == length){
                            y -= 1;
                        }
                    }else{
                        //y = (int)(Settings.SCENE_HEIGHT-((int)mouseposition.getY()-4));
                        y = (int) mouseposition.getY()+4;
                    }
                    if(j+1<length/2){
                        //x = ((int)mouseposition.getX())-(length/2)*8-(length/2-1)*1-4;
                        x = (int) mouseposition.getX() - (length/2-j)*8 - (length/2-j) - 4;
                        if(j == 0){
                            x += 1;
                        }
                    }else if(j+1>length/2){
                        //x = ((int)mouseposition.getX())+(length/2)*8+(length/2-1)*1-4;
                        x = (int) mouseposition.getX() + ((length/2-(j-length/2))-1)*8 + (length/2-(j-length/2)) - 4;
                    }else{
                        x = (int) mouseposition.getX()-4;
                    }
                }
                double dy = (Settings.SCENE_HEIGHT-y)/Settings.SCALE;
                double dx = 0;
                if(x<Settings.SCENE_WIDTH/2){
                    dx = -(((Settings.SCENE_WIDTH/2)-x)/Settings.SCALE);
                }else if(x>Settings.SCENE_WIDTH/2){
                    dx = (x-(Settings.SCENE_WIDTH/2))/Settings.SCALE;
                }
                Particle particle = new Particle(dx,dy);
                particles.add(particle);
            }
        }
        
        return particles;
    }
}
