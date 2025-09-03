package ch.tbz.budgedbuddy.parsing;

import ch.tbz.budgedbuddy.exception.InvalidCommandException;
import ch.tbz.budgedbuddy.exception.ParseException;

public class CommandDispatcher {
    public static Command parse(String line) throws InvalidCommandException, ParseException {
        String[] parts = line.trim().split("\\s+");
        String cmd = parts[0].toLowerCase();
        switch(cmd){
            case "help": return new HelpCommand();
            case "add-category": return CommandFactory.parseAddCategory(parts);
            case "list-categories": return (svc, rep) -> rep.listCategoriesFormatted();
            case "add-expense": return CommandFactory.parseAddExpense(parts, line);
            case "list-expenses": return CommandFactory.parseListExpenses(parts);
            case "summary": return CommandFactory.parseSummary(parts);
            case "save": return (svc, rep) -> { svc.save(); return "Gespeichert."; };
            case "load": return (svc, rep) -> { svc.load(); return "Geladen."; };
            default: throw new InvalidCommandException("Unbekannter Befehl: "+cmd);
        }
    }
}