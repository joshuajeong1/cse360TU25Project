package project;

import java.util.Random;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Nurse extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create text fields
        TextField textField1 = new TextField();
        TextField textField2 = new TextField();
        TextField textField3 = new TextField();
        TextField textField4 = new TextField();
        TextField textField5 = new TextField();
        TextField textField6 = new TextField();
        TextField textField7 = new TextField();
        TextField textField8 = new TextField();
        
        
        // Create Labels
        Label label1 = new Label("First Name:");
        Label label2 = new Label("Last Name:");
        Label label3 = new Label("Weight:");
        Label label4 = new Label("Height:");
        Label label5 = new Label("Blood Pressure:");
        Label label6 = new Label("Allergies:");
        Label label7 = new Label("Health Concerns:");
        Label label8 = new Label("DOB:");
        
        Label label9 = new Label("Patient Intake Form");
        Label label10 = new Label("");
        
        // Create Button and set style
        Button button1 = new Button("Save");
        String buttonStyle = "-fx-background-color: #4775d1; -fx-text-fill: black; -fx-font-size: 16px; -fx-pref-width: 100px; -fx-pref-height: 25px;";
        button1.setStyle(buttonStyle);
        
        // Handle button click event
        button1.setOnAction(e -> {
        	Random rand = new Random();
            // Generate a random 6-digit number
            int randomNumber = rand.nextInt(900000) + 100000;
            // Create an array of text fields
            TextField[] textFields = {textField1, textField2, textField3, textField4, textField5, textField6, textField7, textField8};
            // Save data to file
            Data.saveDataToFile(textFields,randomNumber);
            Main back = new Main();
            back.start(primaryStage);
        });

        // Create a VBox layout to stack labels next to text fields
        VBox vbox0 = new VBox();
        vbox0.setAlignment(Pos.BASELINE_LEFT);
        vbox0.setSpacing(26);
        vbox0.setPadding(new Insets(20));
        vbox0.getChildren().addAll(label10, label1, label2, label3, label4, label5, label6, label7, label8);
        
        // Create a VBox layout to stack text fields vertically
        VBox vbox1 = new VBox();
        vbox0.setAlignment(Pos.BASELINE_RIGHT);
        vbox1.setSpacing(20);
        vbox1.setPadding(new Insets(20));
        vbox1.getChildren().addAll(label9, textField1, textField2, textField3, textField4, textField5, textField6, textField7, textField8);
        
        // Create HBox for button on right
        HBox hbox0 = new HBox();
        hbox0.setAlignment(Pos.BOTTOM_RIGHT);
        hbox0.setSpacing(20);
        hbox0.setPadding(new Insets(20));
        hbox0.getChildren().addAll(button1);
        
        // Create root VBox
        HBox root = new HBox();
        root.getChildren().addAll(vbox0, vbox1, hbox0);
        
        // Create a scene with the VBox as the root node
        Scene scene = new Scene(root, 475, 475);

        // Set the title of the window
        primaryStage.setTitle("Patient Intake");

        // Set the scene for the primary stage
        primaryStage.setScene(scene);

        // Display the primary stage
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
