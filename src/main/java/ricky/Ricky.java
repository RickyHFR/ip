package ricky;

import ricky.command.Command;
import ricky.task.TaskList;

import java.nio.file.Paths;
import java.nio.file.Path;


public class Ricky {

    private static final Path filePath = Paths.get("src", "main", "data", "ricky.txt");
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

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

    public static void main(String[] args) {
        new Ricky(filePath).run();
    }
}