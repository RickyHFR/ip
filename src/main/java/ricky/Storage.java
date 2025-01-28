package ricky;

import ricky.task.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Handles the loading and saving of tasks to a file.
 */
public class Storage {
    private final Path filePath;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath The path of the file to store tasks.
     */
    public Storage(Path filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file.
     *
     * @return A list of tasks loaded from the file.
     * @throws RickyException If the file contains invalid tasks.
     * @throws IOException If an I/O error occurs.
     */
    public ArrayList<Task> loadTasks() throws RickyException, IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        if (!Files.exists(filePath)) {
            this.createFile();
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
                    tasks.add(new Deadline(data[2], LocalDateTime.parse(data[3], DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"))));
                    if (data[1].equals("1")) {
                        tasks.get(tasks.size() - 1).markDone();
                    }
                    break;
                case "E":
                    if (data.length != 5 || (!data[1].equals("0") && !data[1].equals("1"))) {
                        throw new RickyException("Invalid task in file: " + filePath);
                    }
                    tasks.add(new Event(data[2], LocalDateTime.parse(data[3], DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm")), LocalDateTime.parse(data[4], DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"))));
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

    /**
     * Saves the list of tasks to the file.
     *
     * @param tasks The list of tasks to be saved.
     * @throws RickyException If an error occurs while writing to the file.
     */
    public void storeTasks(TaskList tasks) throws RickyException {
        if (!Files.exists(filePath)) {
            this.createFile();
        }
        try (BufferedWriter writer = Files.newBufferedWriter(filePath)) {
            for (Task task : tasks.getTasks()) {
                writer.write(task.storeInfo());
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RickyException("Error writing to file: " + filePath);
        }
    }

    /**
     * Creates the file and its parent directories if they do not exist.
     */
    public void createFile() {
        try {
            Files.createDirectories(filePath.getParent());
        } catch (IOException e) {
            System.err.println("Error creating directory: " + filePath.getParent());
        }
    }
}