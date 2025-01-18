import java.util.Scanner;

public class Ricky {
    public static void main(String[] args) {
        String greetingLine = "_______________________________________________________\n";
        String greetingMsg1 = "Hello! I'm Ricky.\n";
        String greetingMsg2 = "What can I do for you?\n";
        String greetingMsg3 = "Bye. Hope to see you again soon!\n";
        String listHead = "added: ";
        System.out.println(greetingLine + greetingMsg1 + greetingMsg2 + greetingLine);
        String[] taskList = new String[100];
        int currPosition = 0;

        Scanner scanner = new Scanner(System.in);
        String usrRequest = scanner.nextLine();
        while (!usrRequest.equals("bye")) {
            if (usrRequest.equals("list")) {
                System.out.println(greetingLine);
                for (int i = 0; i < currPosition; i++) {
                    System.out.printf("%d. %s\n", i, taskList[i]);
                }
                System.out.println(greetingLine);
                usrRequest = scanner.nextLine();
                continue;
            }
            System.out.println(greetingLine + listHead + usrRequest + "\n" + greetingLine);
            taskList[currPosition] = usrRequest;
            currPosition++;
            usrRequest = scanner.nextLine();
        }
        System.out.println(greetingLine + greetingMsg3 + greetingLine);
    }
}