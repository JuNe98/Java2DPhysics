package java2dsimulator;

import java.util.ArrayList;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

/**
 * The Main Class which handels all the generation and Spawning and also the
 * Userinputs
 *
 * @author Matthias Stirmayr, Julian Nening, Selina Enzlmüller
 */
public class Java2DSimulator extends Application {

    Toolbar toolbar;
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
        overlay.setStyle("-fx-background-color: #E6E6FA;");
        Object2D oj;

        Scale s = new Scale(1, -1);
        Translate t = new Translate(Settings.SCENE_WIDTH / 2, -Settings.SCENE_HEIGHT);
        mainPane.getTransforms().addAll(s, t);
        //TOOLBOX
        Label header = new Label("Tools");

        VBox toolline = new VBox(header);

        toolbar = new Toolbar();
        try {
            toolbar.start(primaryStage);
        } catch (Exception ex) {
            Logger.getLogger(Java2DSimulator.class.getName()).log(Level.SEVERE, null, ex);
        }
        VBox tools = new VBox();

        tools.getChildren().add(toolbar.tools);
//        tools.setLayoutX(2);
        overlay.setCenter(mainPane);
        overlay.setRight(tools);

        scene = new Scene(overlay, Settings.SCENE_WIDTH, Settings.SCENE_HEIGHT);
        PhysObj.setMainPane(mainPane);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Java2DSimulator");
        primaryStage.getIcons().add(new Image("file:img/Kiste.png"));
        primaryStage.show();

        //ImageView floorView = new ImageView(floorImg);
        world = new World();

        // create the floor
        for (int i = -30; i < 30; i++) {
            Rectangle floorrect = new Rectangle(1.0, 1.0);
            PhysObj floorbox = new PhysObj(new Image("file:img/darkground.png"));
            floorbox.addFixture(new BodyFixture(floorrect));
            floorbox.setMass(MassType.INFINITE);
            floorbox.translate(i, 0.0);
            this.world.addBody(floorbox);
        }

        Rectangle rectShape = new Rectangle(1.0, 1.0);
        Circle circleShape = new Circle(0.5);
        //SPAWN TEST
        scene.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (toolbar.type == 2) {
                    MyCircle test = new MyCircle(toolbar.size, event.getSceneX(), event.getSceneY());
                    ArrayList<Particle> x = test.getCircles();
                    for (int i = 0; i < x.size(); i++) {
                        PhysObj par = new PhysObj(new Image("file:img/particle.png"));
                        BodyFixture f = new BodyFixture(new Circle(0.125));
                        f.setDensity(1.2);
                        f.setFriction(0.8);
                        f.setRestitution(0.4);
                        par.addFixture(f);
                        par.setMass(MassType.NORMAL);
                        par.translate(x.get(i).getX(), x.get(i).getY());
                        world.addBody(par);
                    }
                } else if (toolbar.type == 0) {

                    Image boxImg = null;
                    if (random.nextBoolean()) {
                        boxImg = new Image("file:img/smile.png");

                    } else {
                        boxImg = new Image("file:img/smallkiste.png");
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
                    rectangle.rotate(Math.PI);
                    if (x > (Settings.SCENE_WIDTH / 2)) {
                        double rx
                                = (x - Settings.SCENE_WIDTH / 2) / 64 - 0.5;
                        double ry
                                = (Settings.SCENE_HEIGHT - y) / 64 - 0.5;
                        rectangle.translate(rx, ry);
                    } else if (x < (Settings.SCENE_WIDTH / 2)) {
                        double lx = -((Settings.SCENE_WIDTH / 2 - x) / 64) - 0.5;
                        double ly = (Settings.SCENE_HEIGHT - y) / 64 - 0.5;
                        rectangle.translate(lx, ly);
                    }
                    rectangle.getTransform().setRotation(0);
                    world.addBody(rectangle);

                } else if (toolbar.type == 1) {

                    Image kugelImg;

                    kugelImg = new Image("file:img/smallkugel.png");

                    int x = (int) event.getSceneX();
                    int y = (int) event.getSceneY();

                    System.out.println();
                    PhysObj circle = new PhysObj(kugelImg);
                    BodyFixture f = new BodyFixture(circleShape);
                    f.setDensity(1.2);
                    f.setFriction(0.8);
                    f.setRestitution(0.4);
                    circle.addFixture(f);
                    circle.setMass();
                    if (x > (Settings.SCENE_WIDTH / 2)) {
                        double rx
                                = (x - Settings.SCENE_WIDTH / 2) / 64 - 0.5;
                        double ry
                                = (Settings.SCENE_HEIGHT - y) / 64 - 0.5;
                        circle.translate(rx, ry);
                        System.out.printf("Circle spawned! Mouse at X: %d Y: %d - Position at X: % f Y:% f", x, y, rx, ry);
                    } else if (x < (Settings.SCENE_WIDTH / 2)) {
                        double lx = -((Settings.SCENE_WIDTH / 2 - x) / 64) - 0.5;
                        double ly = (Settings.SCENE_HEIGHT - y) / 64 - 0.5;
                        circle.translate(lx, ly);
                        System.out.printf(
                                "Circle spawned! Mouse at X: %d Y: %d ->Position at X: % f Y: % f", x, y, lx, ly);
                    }
                    circle.getTransform().setRotation(0);
                    world.addBody(circle);

                } else if (toolbar.type == 3) {
                    int x = (int) event.getSceneX();
                    int y = (int) event.getSceneY();
                    MySquare square = new MySquare(toolbar.size, x, y);
                    ArrayList<Particle> particles = square.getCircles();
                    for (int i = 0; i < particles.size(); i++) {
                        PhysObj par = new PhysObj(new Image("file:img/particle.png"));
                        BodyFixture f = new BodyFixture(new Circle(0.125));
                        f.setDensity(1.2);
                        f.setFriction(0.8);
                        f.setRestitution(0.4);
                        par.addFixture(f);
                        par.setMass(MassType.NORMAL);
                        par.translate(particles.get(i).getX(), particles.get(i).getY());
                        world.addBody(par);
                    }
                } else if (toolbar.type == 4) {
                    int x = (int) event.getSceneX();
                    int y = (int) event.getSceneY();
                    Rectangle floorrect = new Rectangle(1.0, 1.0);
                    PhysObj floorbox = new PhysObj(new Image("file:img/darkground.png"));
                    floorbox.addFixture(new BodyFixture(floorrect));
                    floorbox.setMass(MassType.INFINITE);
                    if (x > (Settings.SCENE_WIDTH / 2)) {
                        double rx
                                = (x - Settings.SCENE_WIDTH / 2) / 64 - 0.5;
                        double ry
                                = (Settings.SCENE_HEIGHT - y) / 64 - 0.5;
                        floorbox.translate(rx, ry);
                    } else if (x < (Settings.SCENE_WIDTH / 2)) {
                        double lx = -((Settings.SCENE_WIDTH / 2 - x) / 64) - 0.5;
                        double ly = (Settings.SCENE_HEIGHT - y) / 64 - 0.5;
                        floorbox.translate(lx, ly);
                        
                    }
                    world.addBody(floorbox);
                }
            }
        });

        AnimationTimer gameLoop = new AnimationTimer() {

            long last;

            @Override
            public void handle(long now) {
                float delta = 1f / (1000.0f / ((now - last) / 1000000));
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
