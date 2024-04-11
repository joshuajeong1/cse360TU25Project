package project;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Message extends Application {
	
	private String id = "12345";
	private int type = 1;
	private String doctorName = "";
	private String sender = "12345";
	private String patientID = "12345";
	public Message(String id, int type) {
		this.id = id;
		this.type = type;
		// 0 is doctor, 1 is patient
		sender = id;
		if(type == 0) {
			doctorName = id;
		}
		else {
			patientID = id;
		}
		
	}
	
    @Override
    public void start(Stage primaryStage) {
        VBox mainWindow = new VBox(30);
        mainWindow.setAlignment(Pos.TOP_CENTER);
        mainWindow.setPadding(new Insets(20,0,0,0));
        HBox header = new HBox(20);
        header.setAlignment(Pos.CENTER);
        Label title = new Label("Messaging Portal");
        title.setFont(new Font(30));
        HBox newConversationContainer = new HBox();
        newConversationContainer.setAlignment(Pos.CENTER);
        TextField newConversation = new TextField();
        Button startConversation = new Button("New Conversation");
        newConversationContainer.getChildren().addAll(newConversation, startConversation);
        
        
        
        ComboBox<String> dropdown = new ComboBox<String>();
        dropdown.setMinWidth(250);
        if(type == 0) {
        	dropdown.getItems().addAll(getPatients(id));
        	
        }
        else {
        	dropdown.getItems().addAll(getDoctors(id));
        }
        
        startConversation.setOnAction(event -> {
        	if(newConversation.getText().equals(null)) {
        		return;
        	}
        	if(type == 0) {
        		patientID = newConversation.getText();
        	}
        	else {
        		doctorName = newConversation.getText();
        	}
        	dropdown.getItems().add(newConversation.getText());
        	try {
            	String f = "src/messages/" + doctorName + "_" + patientID + ".txt";
            	System.out.println(f);
				FileWriter fr = new FileWriter(f);
				fr.write("");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	dropdown.setValue(newConversation.getText());
        	
        	
        });
        TextArea conversation = new TextArea();
        conversation.setEditable(false);
        conversation.setMinHeight(150);
        conversation.setMaxWidth(400);
        HBox messageRow = new HBox(20);
        TextField message = new TextField();
        message.setEditable(false);
        message.setMinWidth(300);
        
        message.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                System.out.println("Enter key pressed!");
                handleMessageSend(message, dropdown, conversation);
            }
        });
        Button send = new Button("Send");
        
        send.setOnAction(event -> {
        	handleMessageSend(message, dropdown, conversation);
        });
        
        dropdown.setOnAction(event -> {
        	// TODO: Set message field to editable
        	if(dropdown.getValue().equals("")) {
        		return;
        	}
        	loadConversation(dropdown.getValue(), conversation);
        	message.setEditable(true);
        });
        
        messageRow.getChildren().addAll(message, send);
        messageRow.setAlignment(Pos.CENTER);
        header.getChildren().add(dropdown);
        mainWindow.getChildren().addAll(title, newConversationContainer, header, conversation, messageRow);
        
        
        
        Scene scene = new Scene(mainWindow, 475, 475);

        primaryStage.setScene(scene);

        // Set the title of the window
        primaryStage.setTitle("Login Page");

        // Display the primary stage
        primaryStage.show();
    }

    public void handleMessageSend(TextField message, ComboBox<String> dropdown, TextArea conversation) {
    	if(type == 0) {
    		patientID = dropdown.getValue();
    	}
    	else {
    		doctorName = dropdown.getValue();
    	}
    	String currentMessage = message.getText();
        message.setText("");
        writeToConversation(currentMessage, doctorName, patientID, sender);
    	loadConversation(dropdown.getValue(), conversation);
    }
    public void writeToConversation(String message, String doctorName, String patientID, String sender) {
    	String fileName = "src/messages/" + doctorName + "_" + patientID + ".txt";

    	
        try {
        	BufferedReader reader = new BufferedReader(new FileReader(fileName));
        	String previous = "";
        	String line;
        	while((line = reader.readLine()) != null) {
        		previous += line + '\n';
        	}
        	FileWriter writer = new FileWriter(fileName);
        	
            writer.write(previous + sender + ": " + message);
            writer.write("\n");
            
            
            writer.close();
        } catch (IOException e) {
            System.err.println("Error appending to file: " + e.getMessage());
            e.printStackTrace();
        }
    }
    public void loadConversation(String recipientID, TextArea conversation) {
    	String filename;
    	if (type == 0) {
    		filename = "src/messages/" + id + "_" + recipientID + ".txt";
    	}
    	else {
    		filename = "src/messages/" + recipientID + "_" + id + ".txt";
    	}
    	try {
			BufferedReader reader = new BufferedReader(new FileReader(filename));
			StringBuilder conv = new StringBuilder();
			String line;
			while((line = reader.readLine()) != null) {
				conv.append(line);
				conv.append("\n");
			}
			conversation.setText(conv.toString());
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    }
    
    public ArrayList<String> getDoctors(String patientID) {
    	ArrayList<String> names = new ArrayList<String>();
    	String filepath = "src/messages/";
    	
    	File directory = new File(filepath);
    	if(directory.exists() && directory.isDirectory()) {
    		File[] files = directory.listFiles();
    		if(files != null) {
    			for(File f : files) {
    				if(f.isFile()) {
    					if(f.getName().replaceFirst("[.][^.]+$", "").split("_")[1].equals(patientID)) {
    						names.add(f.getName().replaceFirst("[.][^.]+$", "").split("_")[0]);
    					}
    				}
    			}
    			
    		}
    	}
    	return names;
    }
    
    public ArrayList<String> getPatients(String doctorName) {
    	ArrayList<String> names = new ArrayList<String>();
    	String filepath = "src/messages/";
    	
    	File directory = new File(filepath);
    	if(directory.exists() && directory.isDirectory()) {
    		File[] files = directory.listFiles();
    		if(files != null) {
    			for(File f : files) {
    				if(f.isFile()) {
    					if(f.getName().replaceFirst("[.][^.]+$", "").split("_")[0].equals(doctorName)) {
    						names.add(f.getName().replaceFirst("[.][^.]+$", "").split("_")[1]);
    					}
    				}
    			}
    			
    		}
    	}
    	return names;
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
