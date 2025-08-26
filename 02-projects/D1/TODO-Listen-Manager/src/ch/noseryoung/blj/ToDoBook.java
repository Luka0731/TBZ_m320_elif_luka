package ch.noseryoung.blj;

import java.util.ArrayList;
import java.util.Scanner;



public class ToDoBook {

    static final Scanner scanner = new Scanner(System.in);
    private final ArrayList<ToDoList> toDoLists = new ArrayList<>();



    public void add(String title) {toDoLists.add(new ToDoList(title));}



    public void remove(String title) {
        if (!doesExist(title)) {return;}
        toDoLists.remove(getToDoListByTitle(title));
    }



    public void edit(String title, String information, String newInformation) {
        if (!doesExist(title)) {return;}
        getToDoListByTitle(title).editMyself(information, newInformation);
    }



    public void print() {
        System.out.println("----------------------------------------------------------------------");
        for (ToDoList toDoList : toDoLists) {
            toDoList.printStats();
        }
        System.out.println("----------------------------------------------------------------------\n");
    }



    public ToDoList getToDoListByTitle(String title) {
        ArrayList<ToDoList> toDoListsWithSameTitle = new ArrayList<>();
        for (ToDoList toDoList : toDoLists) {
            if (toDoList.getInformationAsString("title").equalsIgnoreCase(title)) {
                toDoListsWithSameTitle.add(toDoList);
            }
        }
        if (toDoListsWithSameTitle.size() > 1) {
            return clarifyWhatToDoList(toDoListsWithSameTitle);
        } else if(toDoListsWithSameTitle.isEmpty()){
            Utility.printColored("ERROR: There is no TODO-List with the title '" + title + "'", "31");
            return null;
        } else {
            return toDoListsWithSameTitle.getFirst();
        }
    }



    // |---------- Debugging ----------|

    private ToDoList clarifyWhatToDoList(ArrayList<ToDoList> toDoListsWithSameTitle) {
        int index = 1;
        for (ToDoList toDoList : toDoListsWithSameTitle) {
            System.out.println("Number " + index);
            System.out.println("----------------------------------------------------------------------");
            toDoList.printStats();
            System.out.println("----------------------------------------------------------------------");
            index++;
        }
        System.out.print("There are multiple TODO-Lists with the same title. Wiche one do you mean? ");
        do  {
            index = scanner.nextInt();
        } while (index > toDoListsWithSameTitle.size() || index < 1);
        return toDoListsWithSameTitle.get(index - 1);
    }



    public boolean doesExist(String title) {
        for (ToDoList toDoList : toDoLists) {
            if (toDoList.getInformationAsString("title").equalsIgnoreCase(title)) {
                return true;
            }
        }
        Utility.printColored("ERROR: There is no TODO-List with the title '" + title + "'", "31");
        return false;
    }
}
