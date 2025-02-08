package ricky;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import ricky.command.AddCommand;
import ricky.command.Command;
import ricky.command.DeleteCommand;
import ricky.command.ExitCommand;
import ricky.command.FindCommand;
import ricky.command.InvalidCommand;
import ricky.command.ListCommand;
import ricky.command.MarkCommand;
import ricky.task.Deadline;
import ricky.task.Event;
import ricky.task.ToDo;

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
        String[] inputs = input.trim().split(" ");
        String command = inputs[0].trim().toLowerCase();
        switch (command) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "mark":
            if (inputs.length != 2 || !inputs[1].matches("\\d+")) {
                throw new RickyException("Please follow the format: mark [task number]");
            }
            return new MarkCommand(Integer.parseInt(inputs[1]), true);
        case "unmark":
            if (inputs.length != 2 || !inputs[1].matches("\\d+")) {
                throw new RickyException("Please follow the format: unmark [task number]");
            }
        case "delete":
            if (inputs.length != 2 || !inputs[1].matches("\\d+")) {
                throw new RickyException("Please follow the format: delete [task number]");
            }
            return new DeleteCommand(Integer.parseInt(inputs[1]));
        case "todo":
            if (inputs.length == 1) {
                throw new RickyException("OOPS!!! The description of a todo cannot be empty.");
            }
            return new AddCommand(new ToDo(input.substring(5)));
        case "deadline":
            if (inputs.length <= 3) {
                throw new RickyException("Please follow the format: deadline [task] /by [yyyy-mm-dd-HHmm]");
            }
            String[] deadlineInputs = input.substring(9).split(" /by ");
            LocalDateTime by;
            try {
                by = LocalDateTime.parse(deadlineInputs[1], DATE_TIME_FORMATTER);
            } catch (Exception e) {
                throw new RickyException("Please follow the format: yyyy-mm-dd-HHmm");
            }
            return new AddCommand(new Deadline(deadlineInputs[0], by));
        case "event":
            if (inputs.length <= 5) {
                throw new RickyException("Please follow the format: event [task] /from [yyyy-mm-dd-HHmm]" +
                        " /to [yyyy-mm-dd-HHmm]");
            }
            String[] eventInputs = input.substring(6).split(" /from | /to ");
            LocalDateTime from;
            LocalDateTime to;
            try {
                from = LocalDateTime.parse(eventInputs[1], DATE_TIME_FORMATTER);
                to = LocalDateTime.parse(eventInputs[2], DATE_TIME_FORMATTER);
            } catch (Exception e) {
                throw new RickyException("Please follow the format: deadline [task] /by [yyyy-mm-dd-HHmm]");
            }
            return new AddCommand(new Event(eventInputs[0], from, to));
        case "find":
            if (inputs.length <= 1) {
                throw new RickyException("Please follow the format: find [keyword]");
            }
            return new FindCommand(input.substring(5));
        default:
            return new InvalidCommand();
        }
    }
}
