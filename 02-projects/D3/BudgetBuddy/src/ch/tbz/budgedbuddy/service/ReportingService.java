package ch.tbz.budgedbuddy.service;

import ch.tbz.budgedbuddy.domain.Category;
import ch.tbz.budgedbuddy.domain.Money;
import ch.tbz.budgedbuddy.util.Calculator;
import ch.tbz.budgedbuddy.util.Formatter;

import java.time.YearMonth;
import java.util.List;

public class ReportingService {

    private final BudgetService budgetService;

    public ReportingService(BudgetService budgetService) {
        this.budgetService = budgetService;
    }

    public String listCategoriesFormatted() {
        return Formatter.formatCategories(budgetService.listCategories());
    }

    public String listExpensesFormatted(String categoryName) {
        return Formatter.formatExpenses(budgetService.listExpenses(categoryName));
    }

    public String monthlySummaryFormatted(YearMonth yearMonth) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Summary für ").append(yearMonth).append("\n");

        List<Category> categoryList = budgetService.listCategories();
        for (Category category : categoryList) {
            Money spentMoney = Calculator.spentForCategory(
                    category.getName(),
                    budgetService.listExpenses(null),
                    yearMonth
            );
            int remainingCents = category.getMonthlyLimit().getCents() - spentMoney.getCents();
            if (remainingCents < 0) {
                remainingCents = 0;
            }
            Money remainingMoney = new Money(remainingCents);

            stringBuilder.append("Kategorie: ").append(category.getName())
                    .append("  Ausgegeben: ").append(spentMoney)
                    .append("  Limit: ").append(category.getMonthlyLimit())
                    .append("  Verbleibend: ").append(remainingMoney)
                    .append("\n");
        }
        return stringBuilder.toString();
    }
}
