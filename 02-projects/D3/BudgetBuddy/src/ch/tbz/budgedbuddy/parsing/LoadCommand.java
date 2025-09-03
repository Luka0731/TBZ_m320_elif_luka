package ch.tbz.budgedbuddy.parsing;

import ch.tbz.budgedbuddy.service.BudgetService;
import ch.tbz.budgedbuddy.service.ReportingService;

public class LoadCommand implements Command {
    @Override
    public String execute(BudgetService budgetService, ReportingService reportingService) throws Exception {
        budgetService.load();
        return "Geladen.";
    }
}
