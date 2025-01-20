import java.util.Scanner;
import java.util.ArrayList;

public class Ricky {
    String filePath = "../data/ricky.txt";
    ArrayList<Task> taskList = new ArrayList<>();

    public void loadTasks() {
        try {
            taskList = Storage.loadTasks(filePath);
        } catch (RickyException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void main(String[] args) throws RickyException {
        String greetingLine = "____________________________________________________________\n";
        String greetingMsg1 = "Hello! I'm Ricky.\n";
        String greetingMsg2 = "What can I do for you?\n";
        String greetingMsg3 = "Bye. Hope to see you again soon!\n";
        System.out.println(greetingLine + greetingMsg1 + greetingMsg2 + greetingLine);

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
                    for (int i = 0; i < taskList.size(); i++) {
                        System.out.printf("%d. %s\n", i, taskList.get(i).toString());
                    }
                    System.out.println(greetingLine);
                    break;
                default:
                    switch (usrRequest[0]) {
                        case "mark":
                            taskList.get(Integer.parseInt(usrRequest[1]) - 1).markDone();
                            System.out.println(greetingLine + "Nice! I've marked this task as done:\n" + "  " + taskList.get(Integer.parseInt(usrRequest[1]) - 1).toString() + "\n" + greetingLine);
                            break;
                        case "unmark":
                            taskList.get(Integer.parseInt(usrRequest[1]) - 1).markUndone();
                            System.out.println(greetingLine + "OK, I've marked this task as not done yet:\n" + "  " + taskList.get(Integer.parseInt(usrRequest[1]) - 1).toString() + "\n" + greetingLine);
                            break;
                        case "todo":
                            input = input.substring(input.indexOf(" ") + 1).trim();
                            if (input.isEmpty()) {
                                throw new RickyException("OOPS!!! The description of a todo cannot be empty.");
                            }
                            ToDo newTask = new ToDo(input);
                            taskList.add(newTask);
                            System.out.println(greetingLine + "Got it. I've added this task:\n" + "  " + newTask + "\n");
                            System.out.printf("Now you have %d tasks in the list.\n", taskList.size());
                            System.out.println(greetingLine);
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
                            taskList.add(newDeadline);
                            System.out.println(greetingLine + "Got it. I've added this task:\n" +  "  " + newDeadline + "\n");
                            System.out.printf("Now you have %d tasks in the list.\n", taskList.size());
                            System.out.println(greetingLine);
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
                            System.out.println(greetingLine + "Got it. I've added this task:\n" +  "  " + newEvent + "\n");
                            taskList.add(newEvent);
                            System.out.printf("Now you have %d tasks in the list.\n", taskList.size());
                            System.out.println(greetingLine);
                            break;
                        case "delete":
                            if (usrRequest.length == 1) {
                                throw new RickyException("OOPS!!! Please specify the task number to delete.");
                            }
                            int taskNum = Integer.parseInt(usrRequest[1]);
                            if (taskNum > taskList.size() || taskNum <= 0) {
                                throw new RickyException("OOPS!!! Please enter a valid task number.");
                            }
                            Task deletedTask = taskList.get(taskNum - 1);
                            taskList.remove(taskNum - 1);
                            System.out.println(greetingLine + "Noted. I've removed this task:\n" + "  " + deletedTask.toString() + "\n");
                            System.out.printf("Now you have %d tasks in the list.\n", taskList.size());
                            System.out.println(greetingLine);
                            break;
                        default:
                            throw new RickyException();
                    }
            }
        }
    }
}