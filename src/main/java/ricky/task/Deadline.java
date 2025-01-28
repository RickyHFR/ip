package ricky.task;

import java.time.LocalDateTime;

public class Deadline extends Task {

    protected final LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)",
                super.toString(), by.format(DATE_TIME_FORMATTER));
    }

    @Override
    public String storeInfo() {
        return String.format("D | %d | %s | %s | %s",
                isDone ? 1 : 0,
                description,
                by);
    }
}
