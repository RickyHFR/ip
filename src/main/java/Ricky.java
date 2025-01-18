import java.util.Scanner;

public class Ricky {
    public static void main(String[] args) {
        String greetingLine = "_______________________________________________________\n";
        String greetingMsg1 = "Hello! I'm Ricky.\n";
        String greetingMsg2 = "What can I do for you?\n";
        String greetingMsg3 = "Bye. Hope to see you again soon!\n";
        System.out.println(greetingLine + greetingMsg1 + greetingMsg2 + greetingLine);

        Scanner scanner = new Scanner(System.in);
        String usrRequest = scanner.nextLine();
        while (!usrRequest.equals("bye")) {
            System.out.println(greetingLine + usrRequest + "\n" + greetingLine);
            usrRequest = scanner.nextLine();
        }
        System.out.println(greetingLine + greetingMsg3 + greetingLine);
    }
}