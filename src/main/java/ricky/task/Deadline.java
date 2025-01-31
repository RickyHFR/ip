package ricky.task;

import java.time.LocalDateTime;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {

    protected final LocalDateTime by;

    /**
     * Constructs a Deadline task with the specified description and deadline.
     *
     * @param description The description of the task.
     * @param by          The deadline of the task.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns a string representation of the deadline task.
     *
     * @return A string representation of the deadline task.
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)",
                super.toString(), by.format(DATE_TIME_FORMATTER));
    }

    /**
     * Returns a string representation of the deadline task for storage.
     *
     * @return A string representation of the deadline task for storage.
     */
    @Override
    public String storeInfo() {
        return String.format("D | %d | %s | %s | %s",
                isDone ? 1 : 0,
                description,
                by);
    }
}
