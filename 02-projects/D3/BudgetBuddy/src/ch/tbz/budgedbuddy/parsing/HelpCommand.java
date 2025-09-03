package ch.tbz.budgedbuddy.parsing;

import ch.tbz.budgedbuddy.service.BudgetService;
import ch.tbz.budgedbuddy.service.ReportingService;

public class HelpCommand implements Command {
    @Override
    public String execute(BudgetService budgetService, ReportingService reportingService) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("help");
        stringBuilder.append("add-category <name> <limit>");
        stringBuilder.append("list-categories");
        stringBuilder.append("add-expense <category> <amount> <yyyy-MM-dd> [note]");
        stringBuilder.append("list-expenses [category]");
        stringBuilder.append("summary [yyyy-MM]");
        stringBuilder.append("save");
        stringBuilder.append("load");
        stringBuilder.append("exit");
        return stringBuilder.toString();
    }
}
