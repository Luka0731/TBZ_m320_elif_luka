package ch.noseryoung.blj;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;



public class ToDoList {

    // Invisible Attributes
    static final Scanner scanner = new Scanner(System.in);
    private final ArrayList<Task> tasks = new ArrayList<>();
    private int additionCounter = 0;

    // Visible Attributes
    private String title;

    public ToDoList(String title) {
        this.title = title;
    }



    // |---------- About Myself ----------|

    public void printStats() {
        System.out.println(title.toUpperCase() + " | total tasks: " + tasks.size());
    }



    public void editMyself(String information, String newInformation) {
        switch (information) {
            case "title":
                this.title = newInformation;
                break;
            case "":
            default:
                Utility.printColored("ERROR: '" + information + "' is not an information type!", "31");
        }
    }



    public String getInformationAsString(String information) {
        return switch (information) {
            case "title" -> this.title;
            case "task amount" -> Integer.toString(tasks.size());
            default -> {
                Utility.printColored("ERROR: '" + information + "' is not an information type to get as an String!", "31");
                yield null;
            }
        };
    }



    public void exportToFile() {
        try {
            FileWriter writer = new FileWriter(Utility.toSnakeCase(
                    Utility.dateAsString(LocalDate.now()) + "-" + title + "-" + "export.txt"));
            writer.write(getPrintText());
            writer.close(); // Datei schließen
        } catch (IOException e) {
            Utility.printColored("ERROR: Something went wrong with the export!", "31");
        }

    }




    // |---------- About Tasks ----------|

    public void add(String title, String description, String priority, String dueDate) {
        tasks.add(new Task(title, description, Utility.priorityAsNumber(priority), dueDate, this.additionCounter));
        this.additionCounter++;
    }



    public void remove(String title) {
        if (!doesExist(title)) {return;}
        tasks.remove(getTaskByTitle(title));
    }



    public void edit(String title, String information, String newInformation) {
        if (!doesExist(title)) {return;}
        getTaskByTitle(title).editMyself(information, newInformation);
    }



    public void checkOff(String title) {
        if (!doesExist(title)) {return;}
        Task task = getTaskByTitle(title);
        int status = task.getInformationAsInteger("status");

        if (status == 1 || status == 2) {
            status++;
        } else {
            status = 1;
        }

        task.editMyself("status", Utility.statusAsText(status));
    }



    public void print() {
        System.out.println(getPrintText());
    }



    public String getPrintText() {
        StringBuilder text = new StringBuilder();
        for (Task task : tasks) {
            text.append("----------------------------------------------------------------------\n");
            text.append(task.getPrintText()).append("\n");

        }
        text.append("----------------------------------------------------------------------\n");
        return text.toString();
    }



    public void sort(String information) {
        switch (information) {
            case "priority":
            case "status":
            case "addition": // Integers
                for (int run = 0; run < tasks.size(); run++) {
                    for (int index = 0; index < tasks.size() - 1; index++) {
                        if (tasks.get(index).getInformationAsInteger(information) > tasks.get(index + 1).getInformationAsInteger(information)) {
                            swapValues(tasks.get(index), tasks.get(index + 1));
                        }
                    }
                }
                break;
            case "due date": // Local Data
                for (int run = 0; run < tasks.size(); run++) {
                    for (int index = 0; index < tasks.size() - 1; index++) {
                        if (tasks.get(index).getTime().isAfter(tasks.get(index + 1).getTime())) {
                            swapValues(tasks.get(index), tasks.get(index + 1));
                        }
                    }
                }
                break;
            default:
                Utility.printColored("ERROR: Tasks can't be sorted by '" + information + "'", "31");
        }
    }



    // |---------- Private Functions ----------|

    private void swapValues(Task t1, Task t2) {
        tasks.set(tasks.indexOf(t2), t1);
        tasks.set(tasks.indexOf(t1), t2);
    }



    private Task getTaskByTitle(String title) {

        ArrayList<Task> tasksWithSameTitle = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getInformationAsString("title").equalsIgnoreCase(title)) {
                tasksWithSameTitle.add(task);
            }
        }

        if (tasksWithSameTitle.size() > 1) {
            return clarifyWhatTask(tasksWithSameTitle);
        } else if (tasksWithSameTitle.isEmpty()){
            Utility.printColored("ERROR: There is no task with the title '" + title + "'", "31");
            return null;
        } else {
            return tasksWithSameTitle.getFirst();
        }
    }



    // |---------- Debugging ----------|

    private Task clarifyWhatTask(ArrayList<Task> tasksWithSameTitle) {
        int index = 1;
        for (Task task : tasksWithSameTitle) {
            System.out.println("Number " + index);
            System.out.println("----------------------------------------------------------------------");
            task.print();
            System.out.println("----------------------------------------------------------------------");
            index++;
        }
        System.out.print("There are multiple tasks with the same title. Wiche one do you mean? ");
        do  {
            index = scanner.nextInt();
        } while (index > tasksWithSameTitle.size() || index < 1);
        return tasksWithSameTitle.get(index - 1);
    }



    private boolean doesExist(String title) {
        for (Task task : tasks) {
            if (task.getInformationAsString("title").equalsIgnoreCase(title)) {
                return true;
            }
        }
        Utility.printColored("ERROR: There is no task with the title '" + title + "'", "31");
        return false;
    }
}
