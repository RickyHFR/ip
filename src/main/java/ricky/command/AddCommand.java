package ricky.command;
import ricky.Storage;
import ricky.task.TaskList;
import ricky.Ui;
import ricky.task.Task;

public class AddCommand extends Command {
    private final Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(task);
        ui.printAdd(task, tasks);
    }
}
