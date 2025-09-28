package ch.tbz.budgedbuddy.parsing;

import ch.tbz.budgedbuddy.service.BudgetService;
import ch.tbz.budgedbuddy.service.ReportingService;

public class HelpCommand implements Command {
    @Override
    public String execute(BudgetService budgetService, ReportingService reportingService) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("help\n");
        stringBuilder.append("add-category <name> <limit>\n");
        stringBuilder.append("list-categories\n");
        stringBuilder.append("add-expense <category> <amount> <yyyy-MM-dd> [note]\n");
        stringBuilder.append("list-expenses [category]\n");
        stringBuilder.append("summary [yyyy-MM]\n");
        stringBuilder.append("save\n");
        stringBuilder.append("load\n");
        stringBuilder.append("exit\n");
        return stringBuilder.toString();
    }
}
