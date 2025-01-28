package ricky;

import ricky.command.Command;
import ricky.task.TaskList;

import java.nio.file.Paths;
import java.nio.file.Path;

/**
 * The main class of the Ricky application.
 * Initializes and runs the application.
 */
public class Ricky {
    private static final Path filePath = Paths.get("src", "main", "data", "ricky.txt");
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    /**
     * Constructs a Ricky object with the specified file path.
     *
     * @param filePath The path of the file to store tasks.
     */
    public Ricky(Path filePath) {
        storage = new Storage(filePath);
        ui = new Ui();
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (Exception e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the Ricky application.
     * Continuously reads and executes user commands until an error occurs.
     */
    public void run() {
        ui.printWelcome();
        try {
            while (true) {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
            }
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
            try {
                storage.storeTasks(tasks);
            } catch (Exception ex) {
                System.err.println("An error occurred while saving tasks: " + ex.getMessage());
            }
            System.exit(1);
        }
    }

    /**
     * The main method of the Ricky application.
     * Creates a new Ricky object and runs the application.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        new Ricky(filePath).run();
    }
}