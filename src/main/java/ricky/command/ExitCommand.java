package ricky.command;
import ricky.Storage;
import ricky.task.TaskList;
import ricky.Ui;
public class ExitCommand extends Command{
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            storage.storeTasks(tasks);
        } catch (Exception e) {
            ui.showStorageError();
        }
        ui.printGoodbye();
        System.exit(0);
    }
}
