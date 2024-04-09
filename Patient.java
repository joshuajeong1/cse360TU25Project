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

public class Patient extends Application {
	

    @Override
    public void start(Stage primaryStage) {
    	// Create Objects to get data from files
    	Data dat = new Data();
    	String[] idHolder = dat.readDataFromIdFile();
    	String[] cacData = dat.readDataFromFile(idHolder[0]);
    	String[] patData = dat.readDataFromFile1(idHolder[0]);
    	
        // Create labels for technician information
        Label hello = new Label("Hello " + patData[1] + " " + patData[3]);
        Label cac = new Label("The total Agatston CAC score:");

        
        // Create label style
        String setLabelStyle = "-fx-border-color: black;\r\n"
        		+ "    -fx-border-width: 2px;\r\n"
        		+ "    -fx-padding: 5px;";
        
        // Create text fields for technician information
        //TextField idField = new TextField();
        Label cacField = new Label(cacData[1]);
        cacField.setStyle(setLabelStyle);


        // Create labels for technician tasks
        //Label task0Label = new Label("Vessel level Agatston CAC score");
        Label task1Label = new Label("LM:");
        Label task2Label = new Label("LAD:");
        Label task3Label = new Label("LCX:");
        Label task4Label = new Label("RCA:");
        Label task5Label = new Label("PDA:");

        // Create text fields for technician tasks
        Label task1Field = new Label(cacData[3]);
        task1Field.setStyle(setLabelStyle);
        Label task2Field = new Label(cacData[5]);
        task2Field.setStyle(setLabelStyle);
        Label task3Field = new Label(cacData[7]);
        task3Field.setStyle(setLabelStyle);
        Label task4Field = new Label(cacData[9]);
        task4Field.setStyle(setLabelStyle);
        Label task5Field = new Label(cacData[11]);
        task5Field.setStyle(setLabelStyle);
        
        // Create button
        /*Button saveButton = new Button("Save");
        String buttonStyle = "-fx-background-color: #4775d1; -fx-text-fill: black; -fx-font-size: 16px; -fx-pref-width: 100px; -fx-pref-height: 25px;";
        saveButton.setStyle(buttonStyle);
        saveButton.setOnAction(e -> {
            // Add save functionality here
            System.out.println("Save button clicked!");
        });*/

        // Create an VBox for technician information
        VBox technicianInfo0 = new VBox();
        technicianInfo0.setSpacing(28);
        technicianInfo0.setAlignment(Pos.TOP_CENTER);
        technicianInfo0.getChildren().addAll(hello);
        
        // Create a VBox
        VBox technicianInfo1 = new VBox();
        technicianInfo1.setSpacing(20);
        technicianInfo1.setAlignment(Pos.TOP_RIGHT);
        technicianInfo1.getChildren().addAll(cac);
        
        VBox technicianInfo2 = new VBox();
        technicianInfo2.setSpacing(20);
        technicianInfo2.setAlignment(Pos.TOP_RIGHT);
        technicianInfo2.getChildren().addAll(cacField);
        
        //create a HBox
        HBox technicianInfo = new HBox();
        technicianInfo.setSpacing(20);
        technicianInfo.getChildren().addAll(technicianInfo1, technicianInfo2);


        // Create a GridPane for technician tasks
        GridPane technicianTasks = new GridPane();
        technicianTasks.setHgap(10);
        technicianTasks.setVgap(10);
        //technicianTasks.addRow(0, task0Label);
        technicianTasks.addRow(0, task1Label, task1Field);
        technicianTasks.addRow(1, task2Label, task2Field);
        technicianTasks.addRow(2, task3Label, task3Field);
        technicianTasks.addRow(3, task4Label, task4Field);
        technicianTasks.addRow(4, task5Label, task5Field);
        
        // Create an HBox for the button
        /*HBox buttonBox = new HBox();
        buttonBox.getChildren().add(saveButton);
        buttonBox.setPadding(new Insets(10));
        buttonBox.setAlignment(Pos.BOTTOM_RIGHT);*/

        // Create a VBox layout to organize technician information and tasks
        VBox root = new VBox();
        root.setSpacing(20);
        root.setPadding(new Insets(20));
        root.getChildren().addAll(technicianInfo0, technicianInfo, technicianTasks);

        // Create a scene with the VBox as the root node
        Scene scene = new Scene(root, 550, 350);

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
