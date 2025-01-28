package ricky.task;

import java.time.format.DateTimeFormatter;

public class Task {

    protected String description;
    protected boolean isDone;
    protected static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("MMM d yyyy h:mma");

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
        return String.format("[%s] %s", this.getStatusIcon(), description);
    }

    public String storeInfo() {
        return String.format(" | %d | %s", isDone ? 1 : 0, description);
    }
}
