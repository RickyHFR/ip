package ricky.task;

import java.time.format.DateTimeFormatter;

import java.time.LocalDateTime;

public class Event extends Task {

    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)",
                super.toString(), from.format(DATE_TIME_FORMATTER),
                to.format(DATE_TIME_FORMATTER));
    }

    @Override
    public String storeInfo() {
        return String.format("E | %d | %s | %s | %s",
                isDone ? 1 : 0,
                description,
                from,
                to);
    }
}
