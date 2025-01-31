package ricky;

import ricky.command.*;
import ricky.task.Deadline;
import ricky.task.Event;
import ricky.task.ToDo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Parses user input into commands.
 */
public class Parser {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    /**
     * Parses the user input and returns the corresponding command.
     *
     * @param input The user input.
     * @return The command corresponding to the user input.
     * @throws RickyException If the user input is invalid.
     */
    public static Command parse(String input) throws RickyException {
        String[] inputs = input.split(" ");
        String command = inputs[0];
        switch (command) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "mark":
            if (inputs.length == 1) {
                throw new RickyException("OOPS!!! Please specify the task number.");
            }
            return new MarkCommand(Integer.parseInt(inputs[1]), true);
        case "unmark":
            if (inputs.length == 1) {
                throw new RickyException("OOPS!!! Please specify the task number.");
            }
            return new MarkCommand(Integer.parseInt(inputs[1]), false);
        case "delete":
            if (inputs.length == 1) {
                throw new RickyException("OOPS!!! Please specify the task number to delete.");
            }
            return new DeleteCommand(Integer.parseInt(inputs[1]));
        case "todo":
            if (inputs.length == 1) {
                throw new RickyException("OOPS!!! The description of a todo cannot be empty.");
            }
            return new AddCommand(new ToDo(input.substring(5)));
        case "deadline":
            String[] deadlineInputs = input.substring(9).split(" /by ");
            LocalDateTime by;
            try {
                by = LocalDateTime.parse(deadlineInputs[1], DATE_TIME_FORMATTER);
            } catch (Exception e) {
                throw new RickyException("Please follow the format: yyyy-mm-dd-HHmm");
            }
            return new AddCommand(new Deadline(deadlineInputs[0], by));
        case "event":
            String[] eventInputs = input.substring(6).split(" /from | /to ");
            LocalDateTime from;
            LocalDateTime to;
            try {
                from = LocalDateTime.parse(eventInputs[1], DATE_TIME_FORMATTER);
                to = LocalDateTime.parse(eventInputs[2], DATE_TIME_FORMATTER);
            } catch (Exception e) {
                throw new RickyException("Please follow the format: yyyy-mm-dd-HHmm");
            }
            return new AddCommand(new Event(eventInputs[0], from, to));
        case "find":
            if (inputs.length == 1) {
                throw new RickyException("OOPS!!! Please specify the keyword to find.");
            }
            return new FindCommand(input.substring(5));
        default:
            return new InvalidCommand();
        }
    }
}
