package project;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Technician extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create labels for technician information
        Label id = new Label("Patient ID:");
        Label cac = new Label("The total Agatston CAC score:");

        // Create text fields for technician information
        TextField idField = new TextField();
        TextField cacField = new TextField();

        // Create labels for technician tasks
        Label task0Label = new Label("Vessel level Agatston CAC score");
        Label task1Label = new Label("LM:");
        Label task2Label = new Label("LAD:");
        Label task3Label = new Label("LCX:");
        Label task4Label = new Label("RCA:");
        Label task5Label = new Label("PDA:");

        // Create text fields for technician tasks
        TextField task1Field = new TextField();
        TextField task2Field = new TextField();
        TextField task3Field = new TextField();
        TextField task4Field = new TextField();
        TextField task5Field = new TextField();
        
        // Create button
        Button saveButton = new Button("Save");
        String buttonStyle = "-fx-background-color: #4775d1; -fx-text-fill: black; -fx-font-size: 16px; -fx-pref-width: 100px; -fx-pref-height: 25px;";
        saveButton.setStyle(buttonStyle);
        saveButton.setOnAction(e -> {
            // Add save functionality here
        	TextField[] textFields = {cacField, task1Field, task2Field, task3Field, task4Field, task5Field};
            // Save data to file
            Data.saveDataToFile1(textFields,idField.getText());
            Main back = new Main();
            back.start(primaryStage);
        });

        // Create an VBox for technician information
        VBox technicianInfo0 = new VBox();
        technicianInfo0.setSpacing(28);
        technicianInfo0.setAlignment(Pos.TOP_LEFT);

        technicianInfo0.getChildren().addAll(id, cac);
        
        // Create a VBox
        VBox technicianInfo1 = new VBox();
        technicianInfo1.setSpacing(20);
        technicianInfo1.setAlignment(Pos.TOP_RIGHT);
        technicianInfo1.getChildren().addAll(idField, cacField);
        
        //create a HBox
        HBox technicianInfo = new HBox();
        technicianInfo.setSpacing(20);
        technicianInfo.getChildren().addAll(technicianInfo0, technicianInfo1);


        // Create a GridPane for technician tasks
        GridPane technicianTasks = new GridPane();
        technicianTasks.setHgap(10);
        technicianTasks.setVgap(10);
        technicianTasks.addRow(0, task0Label);
        technicianTasks.addRow(1, task1Label, task1Field);
        technicianTasks.addRow(2, task2Label, task2Field);
        technicianTasks.addRow(3, task3Label, task3Field);
        technicianTasks.addRow(4, task4Label, task4Field);
        technicianTasks.addRow(5, task5Label, task5Field);
        
        // Create an HBox for the button
        HBox buttonBox = new HBox();
        buttonBox.getChildren().add(saveButton);
        buttonBox.setPadding(new Insets(10));
        buttonBox.setAlignment(Pos.BOTTOM_RIGHT);

        // Create a VBox layout to organize technician information and tasks
        VBox root = new VBox();
        root.setSpacing(20);
        root.setPadding(new Insets(20));
        root.getChildren().addAll(technicianInfo, technicianTasks, buttonBox);

        // Create a scene with the VBox as the root node
        Scene scene = new Scene(root, 600, 400);

        // Set the title of the window
        primaryStage.setTitle("Technician Scene");

        // Set the scene for the primary stage
        primaryStage.setScene(scene);

        // Display the primary stage
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
