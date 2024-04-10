//package com.example;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {
        // Create buttons
        Button button1 = new Button("Intake Portal");
        Button button4 = new Button("Doctor Portal");
        Button button3 = new Button("Patient Portal");
        
        // Create title
        Label label1 = new Label("Welcome to Heart Health Imaging and Recording System");

        // Style buttons
        String buttonStyle = "-fx-background-color: #4775d1; -fx-text-fill: black; -fx-font-size: 16px; -fx-pref-width: 200px; -fx-pref-height: 50px;";
        button1.setStyle(buttonStyle);
        button3.setStyle(buttonStyle);
        button4.setStyle(buttonStyle);
        
        // Handle button click events
        button1.setOnAction(e -> {
            Nurse intake = new Nurse();
            intake.start(primaryStage);
        });
        button3.setOnAction(e -> {
            Login login = new Login();
            login.start(primaryStage);
        });
        button4.setOnAction(e -> {
            DoctorLogin doctor = new DoctorLogin();
            doctor.start(primaryStage);
        });
        
        
        // Style label
        label1.setStyle("-fx-font-size: 16px;");
        
        //VBox for the title at the top
        VBox vbox0 = new VBox();
        vbox0.setAlignment(Pos.TOP_CENTER);
        vbox0.setPadding(new Insets(20));
        vbox0.getChildren().addAll(label1);
        
        // Create a VBox layout to stack buttons vertically
        VBox vbox1 = new VBox();
        vbox1.setAlignment(Pos.BOTTOM_CENTER);
        vbox1.setSpacing(20);
        vbox1.setPadding(new Insets(20));
        vbox1.getChildren().addAll(button1, button4, button3);
        
        // Creating root box
        VBox root = new VBox();
        root.getChildren().addAll(vbox0, vbox1);

        // Create a scene with the VBox as the root node
        Scene scene = new Scene(root, 500, 300);

        // Set the title of the window
        primaryStage.setTitle("JavaFX Application");

        // Set the scene for the primary stage
        primaryStage.setScene(scene);

        // Display the primary stage
        primaryStage.show();
    }
	
	public static void main(String[] args) {
		launch(args);
	}
}
