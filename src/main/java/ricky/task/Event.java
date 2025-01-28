package ricky.task;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

/**
 * Represents a task that occurs within a specific time frame.
 */
public class Event extends Task {

    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Constructs an Event task with the specified description, start time, and end time.
     *
     * @param description The description of the task.
     * @param from        The start time of the event.
     * @param to          The end time of the event.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a string representation of the event task.
     *
     * @return A string representation of the event task.
     */
    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)",
                super.toString(), from.format(DATE_TIME_FORMATTER),
                to.format(DATE_TIME_FORMATTER));
    }

    /**
     * Returns a string representation of the event task for storage.
     *
     * @return A string representation of the event task for storage.
     */
    @Override
    public String storeInfo() {
        return String.format("E | %d | %s | %s | %s",
                isDone ? 1 : 0,
                description,
                from,
                to);
    }
}