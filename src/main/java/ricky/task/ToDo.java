package ricky.task;

public class ToDo extends Task {

    public ToDo(final String description) {
        super(description);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

    @Override
    public String storeInfo() {
        return String.format("T | %d | %s", isDone ? 1 : 0, description);
    }
}
