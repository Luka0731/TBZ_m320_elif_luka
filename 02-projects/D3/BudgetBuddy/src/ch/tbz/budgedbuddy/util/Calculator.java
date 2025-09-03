package ch.tbz.budgedbuddy.util;

import ch.tbz.budgedbuddy.domain.Expense;
import ch.tbz.budgedbuddy.domain.Money;
import java.time.YearMonth;
import java.util.List;

public class Calculator {
    private Calculator() {}

    public static Money sum(List<Expense> expenses) {
        int total = 0;
        for (Expense e : expenses) total += e.getAmount().getCents();
        return new Money(total);
    }

    public static Money spentForCategory(String category, List<Expense> expenses, YearMonth ym) {
        int sum = 0;
        for (Expense e : expenses) {
            if (e.getCategory().equalsIgnoreCase(category) && YearMonth.from(e.getDate()).equals(ym)) {
                sum += e.getAmount().getCents();
            }
        }
        return new Money(sum);
    }
}