import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.TextField;

public class Data {
	// Saves data to patient info file
    public static void saveDataToFile(TextField[] textFields, int id) {
    	String filePath = "src/" + id +"_PatientInfo.txt"; // Path relative to src folder
        try {
            FileWriter writer = new FileWriter(filePath);
            for (TextField textField : textFields) {
            	writer.write(id + "\n");
                writer.write(textField.getText() + "\n");
            }
            writer.close();
            System.out.println("Data saved to file: " + filePath + " Press refresh on src to see it pop up!");
        } catch (IOException e) {
            System.out.println("Error for file: " + e.getMessage());
        }
    }
    
    // Saves data to CT Results file
    public static void saveDataToFile1(TextField[] textFields, String id) {
    	String filePath = "src/" + id +"CTResults.txt"; // Path relative to src folder
        try {
            FileWriter writer = new FileWriter(filePath);
            for (TextField textField : textFields) {
            	writer.write(id + "\n");
                writer.write(textField.getText() + "\n");
            }
            writer.close();
            System.out.println("Data saved to file: " + filePath + " Press refresh on src to see it pop up!");
        } catch (IOException e) {
            System.out.println("Error for file: " + e.getMessage());
        }
    }
    
    // reads data from the CT Results file
    public String[] readDataFromFile(String id) {
        String fileName = "src/" + id + "CTResults.txt"; // Path relative to src folder
        String[] dataLines = new String[20];
        int i = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
            	dataLines[i] = line;
                //dataLines.add(line);
                i++;
            }
        } catch (IOException e) {
            System.out.println("Error for file: " + e.getMessage());
        }

        return (dataLines);
    }
    
    // Saves login data to id_holder file
    public void saveIdToFile(String id) {
    	String filePath = "src/id_holder.txt";
    	try {
    		FileWriter writer = new FileWriter(filePath);
            writer.write(id + "\n");
            writer.close();
    	} catch (IOException e) {
            System.out.println("Error for file: " + e.getMessage());
    	}
    }
    
    // Reads data from the Id file
    public String[] readDataFromIdFile() {
        String fileName = "src/id_holder.txt"; // Path relative to src folder
        String[] dataLines = new String[20];
        int i = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
            	dataLines[i] = line;
                //dataLines.add(line);
                i++;
            }
        } catch (IOException e) {
            System.out.println("Error no scan results yet");
        }

        return (dataLines);
    }
    
    public String[] readDataFromFile1(String id) {
        String fileName = "src/" + id + "_PatientInfo.txt"; // Path relative to src folder
        String[] dataLines = new String[20];
        int i = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
            	dataLines[i] = line;
                //dataLines.add(line);
                i++;
            }
        } catch (IOException e) {
            System.out.println("Error for file: " + e.getMessage());
        }

        return (dataLines);
    }

}
