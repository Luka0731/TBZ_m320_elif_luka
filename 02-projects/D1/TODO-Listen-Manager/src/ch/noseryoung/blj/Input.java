package ch.noseryoung.blj;

import java.util.Scanner;
import static ch.noseryoung.blj.Utility.toColor;



public class Input {

    static final Scanner scanner = new Scanner(System.in);



    // |---------- Book ----------|

    static void toDoBook () {
        System.out.println("/// TODO-BOOK ////////////////////////////////////////////");
        System.out.println("Enter one of the numbers to perform an action");
        System.out.println("| 1 = add | 2 = edit | 3 = remove | 4 = enter | 5 = exit |");
        System.out.println("//////////////////////////////////////////////////////////");
        int action;
        do  {
            action = scanner.nextInt();
            scanner.nextLine(); // Debugging purposes
            switch (action) {
                case 1: addToDoList(); break;
                case 2: editToDoList(); break;
                case 3: removeToDoList(); break;
                case 4: enterToDoList(); break;
                case 5: Starter.run--; break;
                default: Utility.printColored("ERROR: Invalid input!", "31"); break;
            }
        } while (action < 1 || action > 5);
    }



    static void addToDoList() {
        System.out.print("\nTitle: ");
        String title = scanner.nextLine();
        Starter.toDoBook.add(title);
        System.out.println();
    }



    static void editToDoList() {
        System.out.print("\nWhat TODO-List do you want to edit? ");
        String title = scanner.nextLine();
        System.out.print("Possible options: [title]");
        System.out.print("\nWhat information would you like to edit: ");
        String information = scanner.nextLine();
        System.out.print("To what would you like to edit it: ");
        String newInformation = scanner.nextLine();
        Starter.toDoBook.edit(title, information, newInformation);
        System.out.println();
    }



    static void enterToDoList() {
        System.out.print("\nWhat TODO-List do you want to enter? ");
        String title = scanner.nextLine();
        if (Starter.toDoBook.doesExist(title)) {
            Starter.toDoList = Starter.toDoBook.getToDoListByTitle(title);
            Starter.run++;
        }
        System.out.println();
    }



    static void removeToDoList() {
        System.out.print("\nWhat TODO-List do you want to remove? ");
        String title = scanner.nextLine();
        Starter.toDoBook.remove(title);
        System.out.println();
    }



    // |---------- List ----------|

    static void toDoList() {
        System.out.println("/// TODO-List: " + toColor(Utility.getTitleOfToDoList().toUpperCase(), "34") + " ///////////////////////////////////////////////////////////");
        System.out.println("Enter one of the numbers to perform an action");
        System.out.println("| 1 = add | 2 = edit | 3 = remove | 4 = check off | 5 = sort | 6 = export | 7 = exit |");
        System.out.println("///////////////////////////////////////////////////////////////////////////" + Utility.convertStringToSymbols(Utility.getTitleOfToDoList(), '/'));
        int action;
        do  {
            action = scanner.nextInt();
            scanner.nextLine(); // Debugging purposes
            switch (action) {
                case 1: addTask(); break;
                case 2: editTask(); break;
                case 3: removeTask();  break;
                case 4: checkOffTask(); break;
                case 5: sortTask(); break;
                case 6: Starter.toDoList.exportToFile(); break;
                case 7: Starter.run--; break;
                default: Utility.printColored("ERROR: Invalid input!", "31"); break;
            }
        } while (action < 1 || action > 6);
        System.out.println();
    }



    static void addTask() {
        System.out.print("\nTitle: ");
        String title = scanner.nextLine();
        System.out.print("Description: ");
        String description = scanner.nextLine();
        System.out.print("Possible options: [high] [medium] [low]");
        System.out.print("\nPriority: ");
        String priority = scanner.nextLine();
        System.out.print("Wright it like this: dd.mm.yyyy");
        System.out.print("\nDue Date: ");
        String dueDate = scanner.nextLine();
        Starter.toDoList.add(title, description, priority, dueDate);
        System.out.println();
    }



    static void editTask() {
        System.out.print("\nWhat task do you want to edit? ");
        String title = scanner.nextLine();
        System.out.println("Possible options: [title] [description] [status] [priority] [due date]");
        System.out.print("\nWhat information would you like to edit: ");
        String information = scanner.nextLine();
        switch (information) {
            case "title":
            case "description": break;
            case "status": System.out.print("Possible options: [completed] [in progress] [not started]: "); break;
            case "priority": System.out.print("Possible options: [high] [medium] [low]: "); break;
            case "due date": System.out.print("Wright it like this: dd.mm.yyyy: "); break;
            default: Utility.printColored("ERROR: Invalid input!", "31");
        }
        System.out.print("\nTo what would you like to edit it: ");
        String newInformation = scanner.nextLine();
        Starter.toDoList.edit(title, information, newInformation);
        System.out.println();
    }



    static void removeTask() {
        System.out.print("\nWhat task do you want to remove? ");
        String title = scanner.nextLine();
        Starter.toDoList.remove(title);
        System.out.println();
    }



    static void checkOffTask() {
        System.out.print("\nWhat task do you want to check off? ");
        String title = scanner.nextLine();
        Starter.toDoList.checkOff(title);
        System.out.println();
    }



    static void sortTask() {
        System.out.println("\nPossible options: [priority] [status] [addition] [due date]");
        System.out.print("Sort by: ");
        String sort = scanner.nextLine();
        Starter.toDoList.sort(sort);
    }
}
