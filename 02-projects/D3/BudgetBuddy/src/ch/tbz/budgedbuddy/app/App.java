package ch.tbz.budgedbuddy.app;

import ch.tbz.budgedbuddy.repository.BudgetRepository;
import ch.tbz.budgedbuddy.repository.FileBudgetRepository;
import ch.tbz.budgedbuddy.service.BudgetService;
import ch.tbz.budgedbuddy.service.ReportingService;
import ch.tbz.budgedbuddy.ui.Client;

import java.nio.file.Path;

public class App {

    public static void main(String[] args) {

        BudgetRepository repo = new FileBudgetRepository(Path.of("budgetbuddy-data.json"));

        BudgetService budgetService = new BudgetService(repo);
        ReportingService reportingService = new ReportingService();

        Client client = new Client(budgetService, reportingService);
        client.run();
    }
}