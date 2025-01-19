import java.util.Scanner;

public class Ricky {
    public static void main(String[] args) {
        String greetingLine = "_______________________________________________________\n";
        String greetingMsg1 = "Hello! I'm Ricky.\n";
        String greetingMsg2 = "What can I do for you?\n";
        String greetingMsg3 = "Bye. Hope to see you again soon!\n";
        String listHead = "added: ";
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
                        System.out.printf(String.valueOf(i) + "." + taskList[i].toString());
                    }
                    System.out.println(greetingLine);
                    break;
                default:
                    if (usrRequest[0].equals("mark")) {
                        taskList[Integer.parseInt(usrRequest[1])].markDone();
                        System.out.println(greetingLine + "Nice! I've marked this task as done:\n" + taskList[Integer.parseInt(usrRequest[1])].toString() + greetingLine);
                    } else if (usrRequest[0].equals("unmark")) {
                        taskList[Integer.parseInt(usrRequest[1])].markUndone();
                        System.out.println(greetingLine + "OK, I've marked this task as not done yet:\n" + taskList[Integer.parseInt(usrRequest[1])].toString() + greetingLine);
                    } else {
                        System.out.println(greetingLine + listHead + input + "\n" + greetingLine);
                        taskList[Task.totalTaskNumber] = new Task(input);
                    }
            }
        }
    }
}