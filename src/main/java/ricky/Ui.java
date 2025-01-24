package ricky;

import ricky.task.Task;
import ricky.task.TaskList;

import java.util.Scanner;

public class Ui {
    public static void printLine() {
        System.out.println("____________________________________________________________");
    }

    public void printWelcome() {
        printLine();
        System.out.println("Hello! I'm ricky.Ricky.");
        System.out.println("What can I do for you?");
        printLine();
    }

    public void printGoodbye() {
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }

    public void printList(TaskList tasks) {
        printLine();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
        printLine();
    }

    public void printAdd(Task task, TaskList tasks) {
        printLine();
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        printLine();
    }

    public void printDone(Task task) {
        printLine();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
        printLine();
    }

    public void printUndone(Task task) {
        printLine();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task);
        printLine();
    }

    public void printDelete(Task task, TaskList tasks) {
        printLine();
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        printLine();
    }

    public void showLoadingError() {
        printLine();
        System.out.println("Error loading file. Starting with an empty task list.");
        printLine();
    }

    public void showStorageError() {
        printLine();
        System.out.println("Error storing file.");
        printLine();
    }

    public String readCommand() {
        return new Scanner(System.in).nextLine();
    }
}
