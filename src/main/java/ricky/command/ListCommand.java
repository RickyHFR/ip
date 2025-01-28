package ricky.command;

import ricky.Storage;
import ricky.task.TaskList;
import ricky.Ui;

/**
 * Represents a command to list all tasks in the task list.
 */
public class ListCommand extends Command {

    /**
     * Executes the list command, printing all tasks in the task list.
     *
     * @param tasks   The task list to be printed.
     * @param ui      The UI to print the task list.
     * @param storage The storage (not used in this command).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printList(tasks);
    }
}