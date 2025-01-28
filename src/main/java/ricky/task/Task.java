package ricky.task;

public class Task {
    protected String description;
    protected boolean isDone;


    public Task(String input) {
        this.description = input;
        this.isDone = false;
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
        return "[" + this.getStatusIcon() + "] " + description;
    }

    public String store() {
        return " | " + (isDone ? "1" : "0") + " | " + description;
    }

    public String getDescription() {
        return description;
    }
}
