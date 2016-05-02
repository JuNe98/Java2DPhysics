package java2dsimulator;

import javafx.application.Application;
import javafx.scene.*;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import javafx.scene.image.Image;
import javafx.scene.transform.*;
import javafx.animation.AnimationTimer;

import org.dyn4j.dynamics.*;
import org.dyn4j.geometry.*;

import java.util.Random;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
/**
 * 
 * @author Matthias Stirmayr, Julian Nening, Selina Enzlmüller
 */
public class Java2DSimulator extends Application {
    
    BorderPane overlay = null;
    Pane mainPane = null;
    
    World world = null;
    Scene scene = null;

    Random random = new Random();

    double rnd(double from, double to) {
        return to + (from - to) * random.nextDouble();
    }

    @Override
    public void start(Stage primaryStage) {

        // the scale and translate mean 0,0 is in the centre of the screen
        // at the bottom with height increasing up the screen
        
        overlay = new BorderPane();
        mainPane = new Pane();
        
        Scale s = new Scale(1, -1);
        Translate t = new Translate(Settings.SCENE_WIDTH / 2, -Settings.SCENE_HEIGHT);
        mainPane.getTransforms().addAll(s, t);
        //TOOLBOX
        Label header = new Label("Tools");
        
        VBox toolline = new VBox(header);
        
        BorderPane tools = new BorderPane();
        tools.setCenter(toolline);
        tools.setLayoutX(2);
        overlay.setCenter(mainPane);
        overlay.setRight(tools);
        scene = new Scene(overlay, Settings.SCENE_WIDTH, Settings.SCENE_HEIGHT);
        PhysObj.setMainPane(mainPane);

        primaryStage.setScene(scene);
        primaryStage.show();
        
        //ImageView floorView = new ImageView(floorImg);
        world = new World();

        // create the floor
        Rectangle floorRect = new Rectangle(120.0, 1.0);
        
        PhysObj floor = new PhysObj(new Image("file:img/ground.png"));
        floor.rotate(Math.PI);
        
        floor.addFixture(new BodyFixture(floorRect));
        
        floor.setMass(MassType.INFINITE);

        // move the floor down a bit
        floor.translate(0.0, -1.0);
        
        this.world.addBody(floor);

        Rectangle rectShape = new Rectangle(1.0, 1.0);

//        for (int i = 0; i < 10; i++) {
//            PhysObj rectangle = new PhysObj(boxImg);
//            BodyFixture f = new BodyFixture(rectShape);
//            f.setDensity(1.2);
//            f.setFriction(0.8);
//            f.setRestitution(0.4);
//            rectangle.addFixture(f);
//            rectangle.setMass();
//            rectangle.translate(rnd(-3, 3), 9.0 + rnd(-4, 2));
//            rectangle.getTransform().setRotation(rnd(-3.141, 3.141));
//            this.world.addBody(rectangle);
//        }

        scene.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Image boxImg;
                if (rnd(0, 10) > 5) {

                    boxImg = new Image("file:img/smallbox.png");
                } else {

                    boxImg = new Image("file:img/smile.png");
                }
                int x = (int) event.getSceneX();
                int y = (int) event.getSceneY();
                
                System.out.println();
                PhysObj rectangle = new PhysObj(boxImg);
                BodyFixture f = new BodyFixture(rectShape);
                f.setDensity(1.2);
                f.setFriction(0.8);
                f.setRestitution(0.4);
                rectangle.addFixture(f);
                rectangle.setMass();
                if(x>(Settings.SCENE_WIDTH/2)){
                    double rx = (x-Settings.SCENE_WIDTH/2)/64 - 0.5;
                    double ry = (Settings.SCENE_HEIGHT-y)/64 - 0.5;
                    rectangle.translate(rx,ry);
                    System.out.printf("Rectangle spawned! Mouse at X: %d Y: %d -> Position at X: %f Y: %f", x,y, rx, ry);
                }else if(x<(Settings.SCENE_WIDTH/2)){
                    double lx = -((Settings.SCENE_WIDTH/2 - x)/64) - 0.5;
                    double ly = (Settings.SCENE_HEIGHT-y)/64 - 0.5;
                    rectangle.translate(lx,ly);
                    System.out.printf("Rectangle spawned! Mouse at X: %d Y: %d -> Position at X: %f Y: %f", x,y, lx, ly);
                }

                rectangle.getTransform().setRotation(0);
                world.addBody(rectangle);
            }
        });

        AnimationTimer gameLoop = new AnimationTimer() {

            long last;

            @Override
            public void handle(long now) { // now is in nanoseconds 
                float delta = 1f / (1000.0f / ((now - last) / 1000000));  // seems long winded but avoids precision issues
                world.updatev(delta);
                PhysObj.update();

                last = now;
            }

        };
        gameLoop.start();

    }

    public static void main(String[] args) {
        launch(args);
    }

}
