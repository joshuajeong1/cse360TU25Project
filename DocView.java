package com.example;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;


public class DocView extends Application {
    private final Map<String, Control> hashMap = new HashMap<>();
    private TextField patientNumberField;
    private String filename = "patient_%s_info.txt";
    private String medFilename = "patient_%s_meds.txt";

    @Override
    public void start(Stage primaryStage) {
        VBox mainLayout = new VBox(10);
        mainLayout.setPadding(new Insets(10));

        //patient number input box
        HBox patientNumberBox = new HBox(10);
        patientNumberBox.getChildren().add(new Label("Patient Number:"));
        patientNumberField = new TextField();
        patientNumberField.setPromptText("Enter patient number");
        Button searchButton = new Button("Search");
        searchButton.setOnAction(event -> loadPatientData());
        patientNumberBox.getChildren().addAll(patientNumberField, searchButton);
        mainLayout.getChildren().add(patientNumberBox);

        //make the 3 columns for data
        HBox dataLayout = new HBox(10);
        VBox leftColumn = new VBox(10);
        VBox centerColumn = new VBox(10);
        VBox rightColumn = new VBox(10);
        addSectionsToColumns(leftColumn, centerColumn, rightColumn);
        dataLayout.getChildren().addAll(leftColumn, centerColumn, rightColumn);

        //save button
        Button saveButton = new Button("Save");
        saveButton.setOnAction(event -> saveDataToFile(String.format(medFilename, patientNumberField.getText())));
        rightColumn.getChildren().add(saveButton);

        mainLayout.getChildren().add(dataLayout);

        //set stage
        primaryStage.setTitle("Doctor View");
        primaryStage.setScene(new Scene(mainLayout));
        primaryStage.show();
    }

    //read in patient data
    private void loadPatientData() {
        String patientNumber = patientNumberField.getText();
        if (!patientNumber.isEmpty()) {
            readDataFromFile(String.format(filename, patientNumber));
        }
    }

    private void addSectionsToColumns(VBox leftColumn, VBox centerColumn, VBox rightColumn) {
        //medications and notes
        leftColumn.getChildren().add(new Label("Medication / Notes"));
        leftColumn.getChildren().add(makeBox("Medications"));
        leftColumn.getChildren().add(makeBox("Doctor's Notes"));

        //all the vitals
        centerColumn.getChildren().add(new Label("Vitals"));
        centerColumn.getChildren().add(makeBox("Weight"));
        centerColumn.getChildren().add(makeBox("Height"));
        centerColumn.getChildren().add(makeBox("Body temperature"));
        centerColumn.getChildren().add(makeBox("Blood pressure"));
        centerColumn.getChildren().add(makeBox("Known allergies"));
        centerColumn.getChildren().add(makeBox("Concerns"));

        //health history
        rightColumn.getChildren().add(new Label("History"));
        rightColumn.getChildren().add(makeBox("Previous health issues"));
        rightColumn.getChildren().add(makeBox("Previously prescribed medication"));
        rightColumn.getChildren().add(makeBox("Immunization history"));
    }

    //method for making the boxes
    private HBox makeBox(String labelText) {
        HBox hbox = new HBox(10);
        Label label = new Label(labelText + ": ");
        label.setMinWidth(Region.USE_PREF_SIZE);

        Control inputControl;
        if (labelText.equals("Medications") || labelText.equals("Doctor's Notes")) {
            TextArea textArea = new TextArea();
            textArea.setWrapText(true);
            inputControl = textArea;
        } else {
            TextField textField = new TextField();
            textField.setPrefHeight(35);
            inputControl = textField;
        }

        hbox.getChildren().addAll(label, inputControl);
        hashMap.put(labelText, inputControl);
        return hbox;
    }

    //read data from file
    //name should be patient_%s_info.txt
    private void readDataFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                //look for the ":" in the text file
                String[] parts = line.split(": ", 2);
                if (parts.length == 2) {
                    Control control = hashMap.get(parts[0]);
                    if (control != null) {
                        if (control instanceof TextArea) {
                            ((TextArea) control).setText(parts[1]);
                        } else if (control instanceof TextField) {
                            ((TextField) control).setText(parts[1]);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //save data to file
    //named patient_%s_meds.txt
    private void saveDataToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Map.Entry<String, Control> entry : hashMap.entrySet()) {
                if (entry.getKey().equals("Medications") || entry.getKey().equals("Doctor's Notes")) {
                    String text = entry.getValue() instanceof TextInputControl ? ((TextInputControl) entry.getValue()).getText() : "";
                    writer.write(entry.getKey() + ": " + text);
                    writer.newLine();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
