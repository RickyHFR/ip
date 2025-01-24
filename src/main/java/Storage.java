import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Storage {
    private final Path filePath;

    public Storage(Path filePath) {
        this.filePath = filePath;
    }

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

                        if (data[1].equals("1")) {
                            tasks.get(tasks.size() - 1).markDone();
                        }
                        break;
                    case "E":
                        if (data.length != 5 || (!data[1].equals("0") && !data[1].equals("1"))) {tasks.add(new Deadline(data[2], LocalDateTime.parse(data[3], DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"))));
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

    public void storeTasks(TaskList tasks) throws RickyException {
        if (!Files.exists(filePath)) {
            this.createFile();
        }
        try (BufferedWriter writer = Files.newBufferedWriter(filePath)) {
            for (Task task : tasks.getTasks()) {
                writer.write(task.store());
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RickyException("Error writing to file: " + filePath);
        }
    }

    public void createFile() {
        try {
            Files.createDirectories(filePath.getParent());
        } catch (IOException e) {
            System.err.println("Error creating directory: " + filePath.getParent());
        }
    }
}