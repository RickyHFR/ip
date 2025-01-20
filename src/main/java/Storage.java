import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class Storage {
    public static ArrayList<Task> loadTasks(Path filePath) throws RickyException, IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        if (!Files.exists(filePath)) {
            Files.createFile(filePath);
        }
        try (BufferedReader reader = Files.newBufferedReader(filePath)) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(" \\| ");
                switch (data[0]) {
                    case "T":
                        if (data.length != 3 || (!data[1].equals("0") && !data[1].equals("1"))) {
                            throw new RickyException("Invalid task in file: " + filePath);
                        }
                        tasks.add(new ToDo(data[2]));
                        if (data[1].equals("1")) {
                            tasks.get(tasks.size() - 1).markDone();
                        }
                        break;
                    case "D":
                        if (data.length != 4 || (!data[1].equals("0") && !data[1].equals("1"))) {
                            throw new RickyException("Invalid task in file: " + filePath);
                        }
                        tasks.add(new Deadline(data[2], data[3]));
                        if (data[1].equals("1")) {
                            tasks.get(tasks.size() - 1).markDone();
                        }
                        break;
                    case "E":
                        if (data.length != 5 || (!data[1].equals("0") && !data[1].equals("1"))) {
                            throw new RickyException("Invalid task in file: " + filePath);
                        }
                        tasks.add(new Event(data[2], data[3], data[4]));
                        if (data[1].equals("1")) {
                            tasks.get(tasks.size() - 1).markDone();
                        }
                        break;
                    default:
                        throw new RickyException("Invalid task type in file: " + filePath);
                }
            }
        } catch (FileNotFoundException e) {
            throw new RickyException("File not found: " + filePath);
        } catch (IOException e) {
            throw new RickyException("Error reading file: " + filePath);
        }
        return tasks;
    }

    public static void storeTasks(Path filePath, ArrayList<Task> tasks) throws RickyException, IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(filePath)) {
            for (Task task : tasks) {
                writer.write(task.store());
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RickyException("Error writing file: " + filePath);
        }
    }
}