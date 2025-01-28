package ricky.task;

/**
 * Represents a task without any date/time attached to it.
 */
public class ToDo extends Task {

    /**
     * Constructs a ToDo task with the specified description.
     *
     * @param description The description of the task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the ToDo task.
     *
     * @return A string representation of the ToDo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a string representation of the ToDo task for storage.
     *
     * @return A string representation of the ToDo task for storage.
     */
    @Override
    public String store() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }
}