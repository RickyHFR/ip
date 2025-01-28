package ricky.command;

import ricky.Storage;
import ricky.task.TaskList;
import ricky.Ui;

public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printDelete(tasks.get(index - 1), tasks);
        tasks.delete(index - 1);
    }
}
