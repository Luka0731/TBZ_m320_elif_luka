package ch.noseryoung.blj;



public class Starter {

    // Important fields for the whole task manager
    public static int run = 1;
    public static ToDoBook toDoBook = new ToDoBook();
    public static ToDoList toDoList;


    public static void main(String[] ignoredArgs) {

        Utility.beginningSetup(); // Setup

        System.out.println("\nWELCOME TO THE TASK MANAGER!\n");

        //  Book-Loop
        while (run == 1) {
            toDoBook.print();
            Input.toDoBook();

            // List-Loop
            while (run == 2) {
                toDoList.print();
                Input.toDoList();
            }
        }

        System.out.println("\nTHANK YOU FOR USING THE TASK MANAGER!");
    }
}
