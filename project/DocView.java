package project;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DocView extends Application {

	private String username;
	public DocView(String username) {
		this.username = username;
	}
    @Override
    public void start(Stage primaryStage) {
    	Data dat = new Data();
    	String[] idHolder = dat.readDataFromIdFile();
    	String[] patData = dat.readDataFromFile1(idHolder[0]);
    	String[] docData = dat.readDataFromFile2(idHolder[0]);
    	
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10));

        // Center: Medication / Notes
        GridPane medicationNotesPane = new GridPane();
        medicationNotesPane.setVgap(10);

        Label medicationLabel = new Label("Medication:");
        TextArea medicationTextArea = new TextArea();
        medicationTextArea.setPrefRowCount(5);

        Label doctorNotesLabel = new Label("Doctor's Notes:");
        TextArea doctorNotesTextArea = new TextArea();
        doctorNotesTextArea.setPrefRowCount(5);
        
        Label immunizationLabel = new Label("Immunization Record:");
        TextArea immunizationTextArea = new TextArea();
        immunizationTextArea.setPrefRowCount(5);

        // Button to message protal
        String buttonStyle = "-fx-background-color: #4775d1; -fx-text-fill: black; -fx-font-size: 16px; -fx-pref-width: 200px; -fx-pref-height: 50px;";
        Button messageButton = new Button("Message Doctor");
        messageButton.setStyle(buttonStyle);
        
        messageButton.setOnAction(e -> {
        	System.out.println("Button Pressed!");
            Message message = new Message(this.username, 0);
            message.start(primaryStage);
        	
        });
        
        medicationNotesPane.add(medicationLabel, 0, 0);
        medicationNotesPane.add(medicationTextArea, 0, 1);
        medicationNotesPane.add(doctorNotesLabel, 0, 2);
        medicationNotesPane.add(doctorNotesTextArea, 0, 3);
        medicationNotesPane.add(immunizationLabel, 0, 5);
        medicationNotesPane.add(immunizationTextArea, 0, 6);

        root.setLeft(medicationNotesPane);

        
        // Bottom Right: Save Button
        Button saveButton = new Button("Save");
        String buttonStyle1 = "-fx-background-color: #4775d1; -fx-text-fill: black; -fx-font-size: 16px; -fx-pref-width: 200px; -fx-pref-height: 50px;";
        saveButton.setStyle(buttonStyle1);
        saveButton.setOnAction(event -> {
            // Add your saving logic here
            TextArea[] textAreas = {medicationTextArea, doctorNotesTextArea, immunizationTextArea};
            Data.saveDataToFile2(textAreas, idHolder[0]);
            Main main = new Main();
            main.start(primaryStage);
        });

        root.setBottom(saveButton);

        // Create label style
        String setLabelStyle = "-fx-border-color: black;\r\n"
        		+ "    -fx-border-width: 2px;\r\n"
        		+ "    -fx-padding: 5px;";
        
        
        
        // Right: Additional Information
        VBox additionalInfoBox = new VBox(40);
        additionalInfoBox.setPadding(new Insets(10));
        additionalInfoBox.setSpacing(25);
        Label vitalsLabel = new Label("					--Vitals--");
        Label task1Label = new Label("Weight:");
        Label task2Label = new Label("Height:");
        Label task3Label = new Label("Blood Pressure:");
        Label task4Label = new Label("Allergies:");
        Label task5Label = new Label("Concerns:");
        Label task6Label = new Label("Previous health issues:");
        Label task7Label = new Label("Previously prescribed medication:");
        Label task8Label = new Label("Immunization history:");

        VBox additionalInfoBox0 = new VBox(10);
        additionalInfoBox0.setPadding(new Insets(50));
        Label task1Field = new Label(patData[5]);
        task1Field.setStyle(setLabelStyle);
        Label task2Field = new Label(patData[7]);
        task2Field.setStyle(setLabelStyle);
        Label task3Field = new Label(patData[9]);
        task3Field.setStyle(setLabelStyle);
        Label task4Field = new Label(patData[11]);
        task4Field.setStyle(setLabelStyle);
        Label task5Field = new Label(patData[13]);
        task5Field.setStyle(setLabelStyle);
        Label task6Field = new Label(docData[1]);
        task6Field.setStyle(setLabelStyle);
        Label task7Field = new Label(docData[3]);
        task7Field.setStyle(setLabelStyle);
        Label task8Field = new Label(docData[5]);
        task8Field.setStyle(setLabelStyle);
        
        additionalInfoBox.getChildren().addAll(
                vitalsLabel, 
                task1Label, 
                task2Label, 
                task3Label, 
                task4Label, 
                task5Label, 
                task6Label, 
                task7Label, 
                task8Label);
        
        additionalInfoBox0.getChildren().addAll(
        		task1Field,
        		task2Field,
        		task3Field,
        		task4Field,
        		task5Field,
        		task6Field,
        		task7Field,
        		task8Field);

        // Create a VBox
        VBox buttonBox = new VBox();
        buttonBox.setSpacing(20);
        buttonBox.setAlignment(Pos.BOTTOM_LEFT);
        buttonBox.getChildren().addAll(saveButton ,messageButton);
        
        root.setCenter(additionalInfoBox);
        
        root.setRight(additionalInfoBox0);
        
        root.setBottom(buttonBox);

        Scene scene = new Scene(root, 900, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Doctor Portal");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}