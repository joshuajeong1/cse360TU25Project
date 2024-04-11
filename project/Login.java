package project;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Login extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create label for the title
        Label titleLabel = new Label("Patient Login");
        titleLabel.setStyle("-fx-font-size: 18px;");

        // Create label for ID
        Label idLabel = new Label("ID:");

        // Create text field for entering ID
        TextField idField = new TextField();

        // Create login button
        Button loginButton = new Button("Login");
        String buttonStyle = "-fx-background-color: #4775d1; -fx-text-fill: black; -fx-font-size: 16px; -fx-pref-width: 100px; -fx-pref-height: 25px;";
        loginButton.setStyle(buttonStyle);
        loginButton.setOnAction(event -> {
            // Here you can implement login functionality
        	Data dat = new Data();
        	dat.saveIdToFile(idField.getText());
            Patient patient = new Patient();
            patient.start(primaryStage);
        });

        // Create a VBox layout to arrange elements vertically
        VBox root = new VBox(10);
        root.setPadding(new Insets(20));
        root.setSpacing(20);
        root.getChildren().addAll(titleLabel, idLabel, idField, loginButton);

        // Create a scene
        Scene scene = new Scene(root, 500, 250);

        // Set the scene for the primary stage
        primaryStage.setScene(scene);

        // Set the title of the window
        primaryStage.setTitle("Login Page");

        // Display the primary stage
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
