package ricky.command;

import ricky.Storage;
import ricky.Ui;
import ricky.task.TaskList;

public class InvalidCommand extends Command{
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printInvalidCommand();
    }
}
