public class MarkCommand extends Command {
    private int index;
    private boolean isDone;

    public MarkCommand(int index, boolean isDone) {
        this.index = index;
        this.isDone = isDone;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (isDone) {
            tasks.markDone(index - 1);
            ui.printDone(tasks.get(index - 1));
        } else {
            tasks.markUndone(index - 1);
            ui.printUndone(tasks.get(index - 1));
        }
    }
}
