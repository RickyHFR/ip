package ricky.command;

import ricky.Storage;
import ricky.task.TaskList;
import ricky.Ui;

public class Command {
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        // To be overridden by subclasses
    }
}
