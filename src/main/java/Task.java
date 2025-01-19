public class Task {
    protected String description;
    protected boolean isDone;
    public static int totalTaskNumber = 0;
    private final int taskNumber;


    public Task(String input) {
        this.description = input;
        this.isDone = false;
        taskNumber = totalTaskNumber;
        totalTaskNumber++;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markDone() {
        isDone = true;
    }

    public void markUndone() {
        isDone = false;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + description + "\n";
    }
}
