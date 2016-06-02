package java2dsimulator;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Selina Enzlmüller
 */
public class Toolbar extends Application {

    VBox optionsList;
    VBox toolline;
    int size = 5;
    //int  0 = Square, 1 = Circle, 2 = Particle Circle , 3 = Particle Square
    int type = 0;
    BorderPane tools = new BorderPane();

    @Override
    public void start(Stage primaryStage) throws Exception {

        //TOOLBOX
        Label header = new Label("Select Tool");

        //Dropdownmenu
        ObservableList<String> options = FXCollections.observableArrayList();
        final ComboBox comboBox = new ComboBox(options);
        comboBox.getItems().addAll(
                "Circle",
                "Square",
                "Particle Circle",
                "Particle Square"
        );

        Label sizeparameter = new Label("SizeParameter");
        TextField tf = new TextField();
        optionsList = new VBox(sizeparameter, tf);
        comboBox.setValue("");
        comboBox.setPrefWidth(150);

        //Admit Button
        Button admitButton = new Button("Admit");
        admitButton.setOnAction(e -> {
            String choice = (String) comboBox.getValue();
            System.out.println("Selected: " + choice);
            String a = (String) comboBox.getSelectionModel().getSelectedItem();

            if (a == "Square") {
                type = 0;
            } else if (a == "Circle") {
                type = 1;
            } else if (a == "Particle Circle") {
                type = 2;
            } else if (a == "Particle Square") {
                type = 3;
            }

            size = Integer.parseInt(tf.getText());
        });

        BorderPane admit = new BorderPane();
        admit.setRight(admitButton);

        toolline = new VBox(header, comboBox, optionsList, admit);

        tools.setRight(toolline);
        tools.setLayoutX(2);
    }
}
