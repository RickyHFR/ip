package ricky.command;
import ricky.Storage;
import ricky.task.TaskList;
import ricky.Ui;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printList(tasks);
    }
}
