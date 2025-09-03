package ch.tbz.budgedbuddy.util;

import ch.tbz.budgedbuddy.domain.Category;
import ch.tbz.budgedbuddy.domain.Expense;
import ch.tbz.budgedbuddy.domain.Money;

import java.util.List;

public class Formatter {
    private Formatter() {}

    public static String formatCategories(List<Category> categoryList) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Kategorien:\n");
        for (Category category : categoryList) {
            stringBuilder.append(category.getName())
                    .append("  Limit: ")
                    .append(category.getMonthlyLimit())
                    .append("\n");
        }
        return stringBuilder.toString();
    }

    public static String formatExpenses(List<Expense> expenseList) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Ausgaben:\n");
        for (Expense expense : expenseList) {
            String noteText = expense.getNote() == null ? "" : expense.getNote();
            stringBuilder.append(expense.getDate())
                    .append(" ")
                    .append(expense.getCategory())
                    .append(" ")
                    .append(expense.getAmount())
                    .append(" ")
                    .append(noteText)
                    .append("\n");
        }
        return stringBuilder.toString();
    }

    public static String formatSummaryLine(String categoryName, Money spentMoney, Money limitMoney) {
        int remainingCents = limitMoney.getCents() - spentMoney.getCents();
        if (remainingCents < 0) {
            remainingCents = 0;
        }
        Money remainingMoney = new Money(remainingCents);
        return "Kategorie: " + categoryName
                + "  Ausgegeben: " + spentMoney
                + "  Limit: " + limitMoney
                + "  Verbleibend: " + remainingMoney;
    }
}