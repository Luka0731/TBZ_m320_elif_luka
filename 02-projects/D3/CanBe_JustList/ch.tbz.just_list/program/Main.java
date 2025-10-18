package program;

import ai.ToDoAI;
import service.TaskService;

import java.util.Optional;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        TaskService service = new TaskService();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the ToDo App!");
        boolean running = true;

        while (running) {
            System.out.println("\n==== MENU ====");
            System.out.println("1) Create Task");
            System.out.println("2) Show All Tasks");
            System.out.println("3) Delete Task");
            System.out.println("4) Ask AI");
            System.out.println("5) Exit");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Enter task title: ");
                    String title = scanner.nextLine();

                    System.out.print("Enter task description: ");
                    String description = scanner.nextLine();

                    System.out.print("Enter helpful resources (optional): ");
                    String resources = scanner.nextLine();

                    System.out.print("Enter category: ");
                    String category = scanner.nextLine();

                    System.out.print("Enter level (Easy/Mittel/Hard): ");
                    String level = scanner.nextLine();

                    break;

                case "2":
                    System.out.println("\n Current Tasks:");
                    service.ShowAllTasks();
                    break;

                case "3":
                    System.out.print("Enter the title of the task to delete: ");
                    String deleteTitle = scanner.nextLine();
                    service.Delete(deleteTitle);
                    System.out.println("Task deleted (if it existed).");
                    break;

                case "4":
                    System.out.println("Ask AI");
                    String user_prompt = scanner.nextLine();
                    ToDoAI toDoAI = new ToDoAI();
                    toDoAI.AskAI(user_prompt);
                    break;

                case "5":
                    running = false;
                    break;


                default:
                    System.out.println("Invalid option!");
            }
        }

        scanner.close();
    }
}
