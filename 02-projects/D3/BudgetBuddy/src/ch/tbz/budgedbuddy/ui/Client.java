package ch.tbz.budgedbuddy.ui;

import ch.tbz.budgedbuddy.service.BudgetService;
import ch.tbz.budgedbuddy.service.ReportingService;

public class Client {
    private BudgetService budgetService;
    private ReportingService reportingService;

    public Client(BudgetService budgetService, ReportingService reportingService) {
        this.budgetService = budgetService;
        this.reportingService = reportingService;
    }

    public void run() {
        while (true) {
            // todo--------------------------------------------!!!!
        }
    }
}
