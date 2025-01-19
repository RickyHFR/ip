import java.util.Scanner;
import java.util.regex.*;

public class Ricky {
    public static void main(String[] args) throws RickyException {
        String greetingLine = "____________________________________________________________\n";
        String greetingMsg1 = "Hello! I'm Ricky.\n";
        String greetingMsg2 = "What can I do for you?\n";
        String greetingMsg3 = "Bye. Hope to see you again soon!\n";
        System.out.println(greetingLine + greetingMsg1 + greetingMsg2 + greetingLine);

        Task[] taskList = new Task[100];

        Scanner scanner = new Scanner(System.in);
        boolean endService = false;

        while (!endService) {
            String input = scanner.nextLine();
            String[] usrRequest = input.split(" ");
            switch (input) {
                case "bye":
                    endService = true;
                    System.out.println(greetingLine + greetingMsg3 + greetingLine);
                    break;
                case "list":
                    System.out.println(greetingLine);
                    System.out.println("Here are the tasks in your list:\n");
                    for (int i = 0; i < Task.totalTaskNumber; i++) {
                        System.out.printf(String.valueOf(i + 1) + "." + taskList[i].toString() + "\n");
                    }
                    System.out.println(greetingLine);
                    break;
                default:
                    switch (usrRequest[0]) {
                        case "mark":
                            taskList[Integer.parseInt(usrRequest[1]) - 1].markDone();
                            System.out.println(greetingLine + "Nice! I've marked this task as done:\n" + "  " + taskList[Integer.parseInt(usrRequest[1]) - 1].toString() + "\n" + greetingLine);
                            break;
                        case "unmark":
                            taskList[Integer.parseInt(usrRequest[1]) - 1].markUndone();
                            System.out.println(greetingLine + "OK, I've marked this task as not done yet:\n" + "  " + taskList[Integer.parseInt(usrRequest[1]) - 1].toString() + "\n" + greetingLine);
                            break;
                        case "todo":
                            input = input.substring(input.indexOf(" ") + 1).trim();
                            if (input.isEmpty()) {
                                throw new RickyException("OOPS!!! The description of a todo cannot be empty.");
                            }
                            ToDo newTask = new ToDo(input);
                            System.out.println(greetingLine + "Got it. I've added this task:\n" + "  " + newTask.toString() + "\n");
                            System.out.printf("Now you have %d tasks in the list.\n", Task.totalTaskNumber);
                            System.out.println(greetingLine);
                            taskList[Task.totalTaskNumber - 1] = newTask;
                            break;
                        case "deadline":
                            input = input.substring(input.indexOf(" ") + 1);
                            int index = input.indexOf("/by");
                            String by;
                            if (index == -1) {
                                throw new RickyException("Please follow the format: deadline <description> /by <date>");
                            } else {
                                if (input.substring(index + 3).trim().isEmpty()) {
                                    throw new RickyException("OOPS!!! The date of a deadline cannot be empty.");
                                }
                                by = input.substring(index + 3).trim();
                            }
                            String description = input.substring(0, index).trim();
                            if (description.isEmpty()) {
                                throw new RickyException("OOPS!!! The description of a deadline cannot be empty.");
                            }
                            Deadline newDeadline = new Deadline(description, by);
                            System.out.println(greetingLine + "Got it. I've added this task:\n" +  "  " + newDeadline.toString() + "\n");
                            System.out.printf("Now you have %d tasks in the list.\n", Task.totalTaskNumber);
                            System.out.println(greetingLine);
                            taskList[Task.totalTaskNumber - 1] = newDeadline;
                            break;
                        case "event":
                            input = input.substring(input.indexOf(" ") + 1);
                            int fromIndex = input.indexOf("/from");
                            int toIndex = input.indexOf("/to");
                            if (fromIndex == -1 || toIndex == -1) {
                                throw new RickyException("Please follow the format: event <description> /from <start time> /to <end time>");
                            }
                            description = input.substring(0, fromIndex).trim();
                            if (description.isEmpty()) {
                                throw new RickyException("OOPS!!! The description of an event cannot be empty.");
                            }
                            String from = input.substring(fromIndex + 5, toIndex).trim();
                            if (from.isEmpty()) {
                                throw new RickyException("OOPS!!! The start time of an event cannot be empty.");
                            }
                            String to = input.substring(toIndex + 3).trim();
                            if (to.isEmpty()) {
                                throw new RickyException("OOPS!!! The end time of an event cannot be empty.");
                            }
                            Event newEvent = new Event(description, from, to);
                            System.out.println(greetingLine + "Got it. I've added this task:\n" +  "  " + newEvent.toString() + "\n");
                            System.out.printf("Now you have %d tasks in the list.\n", Task.totalTaskNumber);
                            System.out.println(greetingLine);
                            taskList[Task.totalTaskNumber - 1] = newEvent;
                            break;
                        default:
                            throw new RickyException();
                    }
            }
        }
    }
}