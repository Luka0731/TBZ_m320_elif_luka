package ch.tbz.budgedbuddy.parsing;

import ch.tbz.budgedbuddy.service.BudgetService;
import ch.tbz.budgedbuddy.service.ReportingService;

public class ListCategoriesCommand implements Command {
    @Override
    public String execute(BudgetService budgetService, ReportingService reportingService) {
        return reportingService.listCategoriesFormatted();
    }
}
