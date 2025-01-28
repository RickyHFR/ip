package ricky;

import ricky.task.Task;
import ricky.task.TaskList;

import java.util.Scanner;

/**
 * Handles interactions with the user.
 */
public class Ui {

    /**
     * Prints a horizontal line.
     */
    public static void printLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints the welcome message.
     */
    public void printWelcome() {
        printLine();
        System.out.println("Hello! I'm Ricky.");
        System.out.println("What can I do for you?");
        printLine();
    }

    /**
     * Prints the goodbye message.
     */
    public void printGoodbye() {
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }

    /**
     * Prints the list of tasks.
     *
     * @param tasks The list of tasks.
     */
    public void printList(TaskList tasks) {
        printLine();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
        printLine();
    }

    /**
     * Prints a message indicating that a task has been added.
     *
     * @param task  The task that was added.
     * @param tasks The list of tasks.
     */
    public void printAdd(Task task, TaskList tasks) {
        printLine();
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        printLine();
    }

    /**
     * Prints a message indicating that a task has been marked as done.
     *
     * @param task The task that was marked as done.
     */
    public void printDone(Task task) {
        printLine();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
        printLine();
    }

    /**
     * Prints a message indicating that a task has been marked as not done.
     *
     * @param task The task that was marked as not done.
     */
    public void printUndone(Task task) {
        printLine();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task);
        printLine();
    }

    /**
     * Prints a message indicating that a task has been deleted.
     *
     * @param task  The task that was deleted.
     * @param tasks The list of tasks.
     */
    public void printDelete(Task task, TaskList tasks) {
        printLine();
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println("Now you have " + (tasks.size() - 1) + " tasks in the list.");
        printLine();
    }

    /**
     * Prints an error message indicating that there was an error loading the file.
     */
    public void showLoadingError() {
        printLine();
        System.out.println("Error loading file. Starting with an empty task list.");
        printLine();
    }

    /**
     * Prints an error message indicating that there was an error storing the file.
     */
    public void showStorageError() {
        printLine();
        System.out.println("Error storing file.");
        printLine();
    }

    /**
     * Prints a message indicating that the command is invalid.
     */
    public void printInvalidCommand() {
        printLine();
        System.out.println("I don't know what that means. Please enter a valid command.");
        printLine();
    }

    /**
     * Reads a command from the user.
     *
     * @return The command entered by the user.
     */
    public String readCommand() {
        return new Scanner(System.in).nextLine();
    }

    /**
     * Prints a message indicating that a task has been found.
     *
     * @param tasks The list of tasks.
     */
    public void printFind(TaskList tasks) {
        printLine();
        if (tasks.size() == 0) {
            System.out.println("There are no matching tasks in your list.");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + "." + tasks.get(i));
            }
        }
        printLine();
    }
}
