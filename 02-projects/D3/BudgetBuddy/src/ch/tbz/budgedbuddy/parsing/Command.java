package ch.tbz.budgedbuddy.parsing;

import ch.tbz.budgedbuddy.service.BudgetService;
import ch.tbz.budgedbuddy.service.ReportingService;

public interface Command {
    String execute(BudgetService budgetService, ReportingService reportingService) throws Exception;
    default boolean isSummary(){
        return false;
    }
}
