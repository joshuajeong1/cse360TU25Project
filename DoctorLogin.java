import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DoctorLogin extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create label for the title
        Label titleLabel = new Label("Doctor Login");
        titleLabel.setStyle("-fx-font-size: 18px;");

        // Create label for username
        Label usernameLabel = new Label("Username:");

        // Create text field for entering username
        TextField usernameField = new TextField();

        // Create label for password
        Label passwordLabel = new Label("Password:");

        // Create password field for entering password
        PasswordField passwordField = new PasswordField();
        
        // Create label for password
        Label idLabel = new Label("Patient ID:");

        // Create password field for entering password
        TextField idField = new TextField();

        // Create login button
        Button loginButton = new Button("Login");
        String buttonStyle = "-fx-background-color: #4775d1; -fx-text-fill: black; -fx-font-size: 16px; -fx-pref-width: 100px; -fx-pref-height: 25px;";
        loginButton.setStyle(buttonStyle);
        loginButton.setOnAction(event -> {
            // Here you can implement login functionality
        	if (Data.readDataFromLoginFile(usernameField.getText(), passwordField.getText())) {
        		Data dat = new Data();
            	dat.saveIdToFile(idField.getText());
            	DocView doctorView = new DocView();
            	doctorView.start(primaryStage);
        	} else {
        		System.out.println("Incorrect username or password!");
        	}
        	
        });

        // Create a VBox layout to arrange elements vertically
        VBox root = new VBox(10);
        root.setPadding(new Insets(20));
        root.setSpacing(20);
        root.getChildren().addAll(titleLabel, usernameLabel, usernameField, passwordLabel, passwordField, idLabel, idField, loginButton);

        // Create a scene
        Scene scene = new Scene(root, 300, 450);

        // Set the scene for the primary stage
        primaryStage.setScene(scene);

        // Set the title of the window
        primaryStage.setTitle("Doctor Login Page");

        // Display the primary stage
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
