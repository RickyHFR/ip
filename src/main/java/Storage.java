import java.io.*;
import java.util.ArrayList;

public class Storage {
    public static ArrayList<Task> loadTasks(String filePath) throws RickyException {
        ArrayList<Task> tasks = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Assuming each line in the file represents a task
                // You need to parse the line and create Task objects accordingly
                // This is a placeholder implementation
                tasks.add(new Task(line));
            }
        } catch (FileNotFoundException e) {
            throw new RickyException("File not found: " + filePath);
        } catch (IOException e) {
            throw new RickyException("Error reading file: " + filePath);
        }
        return tasks;
    }
}