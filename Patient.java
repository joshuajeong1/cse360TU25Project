import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
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
    	String[] cacData = dat.readDataFromFile1(idHolder[0]);
    	String[] docData = dat.readDataFromFile2(idHolder[0]);
    	
        // Create labels for technician information
        Label hello = new Label("Patient: " + cacData[1] + " " + cacData[3]);
        Label infoLabel = new Label("Patient Information");

        
        // Create label style
        String setLabelStyle = "-fx-border-color: black;\r\n"
        		+ "    -fx-border-width: 2px;\r\n"
        		+ "    -fx-padding: 5px;";

        // Create labels for technician tasks
        //Label task0Label = new Label("Vessel level Agatston CAC score");\
        Label vitalsLabel = new Label("		--Vitals--");
        Label historyLabel = new Label("				--History--");
        Label task1Label = new Label("Weight:");
        Label task2Label = new Label("Height:");
        Label task3Label = new Label("Blood Pressure:");
        Label task4Label = new Label("Allergies:");
        Label task5Label = new Label("Concerns:");
        Label task6Label = new Label("Previous health issues:");
        Label task7Label = new Label("Previously prescribed medication:");
        Label task8Label = new Label("Immunization history:");

        // Create text fields for technician tasks
        Label task1Field = new Label(cacData[5]);
        task1Field.setStyle(setLabelStyle);
        Label task2Field = new Label(cacData[7]);
        task2Field.setStyle(setLabelStyle);
        Label task3Field = new Label(cacData[9]);
        task3Field.setStyle(setLabelStyle);
        Label task4Field = new Label(cacData[11]);
        task4Field.setStyle(setLabelStyle);
        Label task5Field = new Label(cacData[13]);
        task5Field.setStyle(setLabelStyle);
        Label task6Field = new Label(docData[1]);
        task6Field.setStyle(setLabelStyle);
        Label task7Field = new Label(docData[3]);
        task7Field.setStyle(setLabelStyle);
        Label task8Field = new Label(docData[5]);
        task8Field.setStyle(setLabelStyle);
        
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
        technicianInfo0.setAlignment(Pos.TOP_LEFT);
        technicianInfo0.getChildren().addAll(hello);
        
        // Create a VBox
        VBox technicianInfo1 = new VBox();
        technicianInfo1.setSpacing(20);
        technicianInfo1.setAlignment(Pos.TOP_CENTER);
        technicianInfo1.getChildren().addAll(infoLabel);
        
        //create a HBox
        HBox technicianInfo = new HBox();
        technicianInfo.setSpacing(20);
        technicianInfo.getChildren().addAll(technicianInfo1);


        // Create a GridPane for Vitals
        GridPane vitals = new GridPane();
        vitals.setHgap(10);
        vitals.setVgap(10);
        //technicianTasks.addRow(0, task0Label);
        vitals.addRow(0, vitalsLabel);
        vitals.addRow(1, task1Label, task1Field);
        vitals.addRow(2, task2Label, task2Field);
        vitals.addRow(3, task3Label, task3Field);
        vitals.addRow(4, task4Label, task4Field);
        vitals.addRow(5, task5Label, task5Field);
        
        // Create a GridPane for History
        GridPane history = new GridPane();
        history.setHgap(10);
        history.setVgap(10);
        //technicianTasks.addRow(0, task0Label);
        history.addRow(0, historyLabel);
        history.addRow(1, task6Label, task6Field);
        history.addRow(2, task7Label, task7Field);
        history.addRow(3, task8Label, task8Field);
        
        //Create a Gridpane for Patient Information
        GridPane patientInfo = new GridPane();
        patientInfo.setHgap(10);
        patientInfo.setVgap(10);
        patientInfo.addColumn(0, vitals);
        patientInfo.addColumn(1, history);
        
        //Sets the Columns to take up 50% of the pane
        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPercentWidth(50); 
        ColumnConstraints column2 = new ColumnConstraints();
        column2.setPercentWidth(50);
        
        patientInfo.getColumnConstraints().addAll(column1, column2);
        
        
        
        
        // Create an HBox for the button
        /*HBox buttonBox = new HBox();
        buttonBox.getChildren().add(saveButton);
        buttonBox.setPadding(new Insets(10));
        buttonBox.setAlignment(Pos.BOTTOM_RIGHT);*/

        // Create a VBox layout to organize technician information and tasks
        VBox root = new VBox();
        root.setSpacing(20);
        root.setPadding(new Insets(20));
        root.getChildren().addAll(technicianInfo0, technicianInfo, patientInfo);

        // Create a scene with the VBox as the root node
        Scene scene = new Scene(root, 800, 400);

        // Set the title of the window
        primaryStage.setTitle("Patient Portal");

        // Set the scene for the primary stage
        primaryStage.setScene(scene);

        // Display the primary stage
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
