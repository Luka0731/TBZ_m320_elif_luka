package ch.noseryoung.blj;

import java.time.LocalDate;
import java.util.UUID;



public class Task {

    // Invisible Attributes
    private final UUID id = UUID.randomUUID();
    private final int addition;

    // Visible Attributes
    private String title;
    private String description;
    private int status = 1;
    private int priority;
    private LocalDate dueDate;



    public Task(String title, String description, int priority, String dueDate, int addition) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.dueDate = Utility.stringAsDate(dueDate);
        this.addition = addition;
        setDefault();
    }



    public String getInformationAsString(String information) {
        return switch (information) {
            case "title" -> this.title;
            case "description" -> this.description;
            case "status" -> Integer.toString(this.status);
            case "priority" -> Integer.toString(this.priority);
            case "id" -> this.id.toString();
            case "addition" -> Integer.toString(this.addition);
            case "due date" -> this.dueDate.toString();
            default -> {
                Utility.printColored("ERROR: '" + information + "' is not an information type to getToDoListByName as an String!", "31");
                yield null;
            }
        };
    }



    public int getInformationAsInteger(String information) {
        return switch (information) {
            case "status" -> this.status;
            case "priority" -> this.priority;
            case "addition" -> this.addition;
            default -> {
                Utility.printColored("ERROR: '" + information + "' is not an information type to getToDoListByName as an Integer!", "31");
                yield 0;
            }
        };
    }



    public LocalDate getTime() {return this.dueDate;}



    public void editMyself(String information, String newInformation) {
        switch (information) {
            case "title":
                this.title = newInformation;
                break;
            case "description":
                this.description = newInformation;
                break;
            case "status":
                this.status = Utility.statusAsNumber(newInformation);
                break;
            case "priority":
                this.priority = Utility.priorityAsNumber(newInformation);
                break;
            case "due date":
                this.dueDate = Utility.stringAsDate(newInformation);
                    break;
            case "id":
            case "addition":
                System.out.println("ERROR: This information is unchangeable");
                break;
            default:
                Utility.printColored("ERROR: '" + information + "' is not an information type!", "31");
        }
        setDefault();
    }


    public void print() {
        System.out.println(getPrintText());
    }



    public String getPrintText() {
        return this.title.toUpperCase() + "\n" +
                "---" + "\n" +
                this.description + "\n" +
                "Status: " + Utility.statusAsText(this.status) + "\n" +
                "Priority: " + Utility.priorityAsText(this.priority) + "\n" +
                "Due Date: " + this.dueDate;
    }



    // |---------- Debugging ----------|

    private void setDefault() {
        if (this.status < 1 || this.status > 3) {
            this.status = 1;
        }
        if (this.priority < 1 || this.priority > 3) {
            this.priority = 2;
        }
    }

}
