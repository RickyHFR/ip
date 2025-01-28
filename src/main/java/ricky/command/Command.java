/**
 * Represents the base class for all commands.
 */
package ricky.command;

import ricky.Storage;
import ricky.task.TaskList;
import ricky.Ui;

/**
 * Represents a command to be executed.
 */
public class Command {
    /**
     * Executes the command.
     *
     * @param tasks   The task list to operate on.
     * @param ui      The UI to interact with the user.
     * @param storage The storage to save the task list.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        // To be overridden by subclasses
    }
}

