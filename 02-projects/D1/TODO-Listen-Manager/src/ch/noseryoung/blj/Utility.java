package ch.noseryoung.blj;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;



public class Utility {

    // |---------- Value Conversion (Fake Enum) ----------|

    static String priorityAsText(int priority) {
        return switch (priority) {
            case 1 -> "high";
            case 2 -> "medium";
            case 3 -> "low";
            default -> "priorityUnknown";
        };
    }

    static int priorityAsNumber(String priority) {
        return switch (priority) {
            case "high" -> 1;
            case "medium" -> 2;
            case "low" -> 3;
            default -> 0;
        };
    }


    static String statusAsText(int status) {
        return switch (status) {
            case 1 -> "not started";
            case 2 -> "in progress";
            case 3 -> "completed";
            default -> "statusUnknown";
        };
    }



    static int statusAsNumber(String status) {
        return switch (status) {
            case "not started" -> 1;
            case "in progress" -> 2;
            case "completed" -> 3;
            default -> 0;
        };
    }



    // |---------- Outsourcing ----------|

    static void beginningSetup() {
        Starter.toDoBook.add("Noser Young");
        Starter.toDoBook.add("TBZ");
        Starter.toDoBook.add("Private");
        Starter.toDoBook.getToDoListByTitle("Noser Young").add("clean floor", "I have to clean the floor at Tim's place",  "low", "31.12.2007");
        Starter.toDoBook.getToDoListByTitle("Noser Young").add("clean floor", "I have to clean the floor at Alisha's kitchen",  "low", "31.12.2007");
        Starter.toDoBook.getToDoListByTitle("Noser Young").add("send e-mail", "I have to respond to the client",  "high", "31.12.2007");
        Starter.toDoBook.getToDoListByTitle("Noser Young").add("do programm", "Finish the Programm I'm working on",  "", "01.12.2007");
        Starter.toDoBook.getToDoListByTitle("Noser Young").add("execute the control", "Check my Programm for coding errors",  "", "31.12.2006");
        Starter.toDoBook.getToDoListByTitle("Noser Young").add("talk to bobby", "We need to discuss the new programm, that we are implementing",  "", "31.12.2007");
        Starter.toDoBook.getToDoListByTitle("TBZ").add("do homework", "Finish the old homework, that I forgot",  "", "31.12.2007");
        Starter.toDoBook.getToDoListByTitle("TBZ").add("Do homework", "Do ABU",  "low", "31.12.2007");
        Starter.toDoBook.getToDoListByTitle("TBZ").add("Do Homework", "Do the Assignment for English",  "High", "31.12.2009");
        Starter.toDoBook.getToDoListByTitle("Private").add("Macarons", "Bake Macarons for LdM",  "high", "31.11.2007");
    }



    static String convertStringToSymbols(String string, char symbol) {
        return String.valueOf(symbol).repeat(string.length());
    }



    static String getTitleOfToDoList() {
       return Starter.toDoList.getInformationAsString("title");
    }



    static String toSnakeCase(String string) {
        return string.replace(" ", "_").toLowerCase();
    }



    static String dateAsString(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }



    static LocalDate stringAsDate(String dueDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return LocalDate.parse(dueDate, formatter);
    }




    // |---------- Color ----------|

    static String toColor(String string, String color) {
        return "\u001B[" + color + "m" + string + "\u001B[0m";
    }

    static void printColored(String string, String color) {
        System.out.print(toColor(string, color));
    }
}
