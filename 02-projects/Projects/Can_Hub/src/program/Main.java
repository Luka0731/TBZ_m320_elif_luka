package program;

import controller.Methods;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Methods userMethods = new Methods();

        while (true) {
            System.out.println("\n===============================");
            System.out.println("🧩 Fake GitHub Control Center");
            System.out.println("===============================");
            System.out.println("1) Create new user");
            System.out.println("2) Create new branch");
            System.out.println("3) Commit branch");
            System.out.println("4) Show commit log");
            System.out.println("5) Delete branch");
            System.out.println("6) Exit");
            System.out.print("Select option: ");

            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    userMethods.create_user();
                    break;

                case "2":
                    System.out.print("Enter branch name: ");
                    String branchName = sc.nextLine();
                    userMethods.createBranch(branchName);
                    break;

                case "3":
                    System.out.print("Enter branch name to commit: ");
                    String branchCommit = sc.nextLine();
                    userMethods.commitBranch(branchCommit);
                    break;

                case "4":
                    System.out.print("Enter branch name to show log: ");
                    String branchLog = sc.nextLine();
                    userMethods.showLog(branchLog);
                    break;

                case "5":
                    System.out.print("Enter branch name to delete: ");
                    String branchDelete = sc.nextLine();
                    userMethods.deleteBranch(branchDelete);
                    break;

                case "6":
                    System.out.println("👋 Exiting Fake GitHub. Goodbye!");
                    sc.close();
                    return;

                default:
                    System.out.println("❌ Invalid option. Try again.");
            }
        }
    }
}
