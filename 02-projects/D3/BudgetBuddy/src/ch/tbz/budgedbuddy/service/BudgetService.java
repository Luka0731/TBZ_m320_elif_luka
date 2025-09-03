package ch.tbz.budgedbuddy.service;

import ch.tbz.budgedbuddy.repository.BudgetRepository;

public class BudgetService {
    private BudgetRepository budgetRepository;

    public BudgetService(BudgetRepository budgetRepository) {
        this.budgetRepository = budgetRepository;
    }
}
