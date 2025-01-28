package ricky.command;

import ricky.Storage;
import ricky.task.TaskList;
import ricky.Ui;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);
}
