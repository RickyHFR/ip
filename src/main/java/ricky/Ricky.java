package ricky;

import ricky.task.TaskList;
import java.nio.file.Path;

/**
 * Represents the Ricky chatbot.
 */
public class Ricky {
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
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        try {
            return Parser.parse(input).execute(tasks, ui, storage);
        } catch (RickyException e) {
            return e.getMessage();
        }
    }

}
