package ch.tbz.budgedbuddy.parsing;

import ch.tbz.budgedbuddy.domain.Money;
import ch.tbz.budgedbuddy.exception.ParseException;
import ch.tbz.budgedbuddy.service.BudgetService;
import ch.tbz.budgedbuddy.service.ReportingService;

import java.time.LocalDate;
import java.time.YearMonth;

public class CommandFactory {
    private CommandFactory() {}

    static Command parseAddCategory(String[] tokenArray) throws ParseException {
        if (tokenArray.length < 3) {
            throw new ParseException("add-category <name> <limit>");
        }
        String categoryName = tokenArray[1];
        Money monthlyLimit = Money.ofString(tokenArray[2]);
        return (budgetService, reportingService) -> {
            budgetService.addCategory(categoryName, monthlyLimit);
            return "Kategorie angelegt: " + categoryName;
        };
    }

    static Command parseAddExpense(String[] tokenArray, String fullInputLine) throws ParseException {
        if (tokenArray.length < 4) {
            throw new ParseException("add-expense <category> <amount> <yyyy-MM-dd> [note]");
        }
        String categoryName = tokenArray[1];
        Money amountMoney = Money.ofString(tokenArray[2]);
        LocalDate expenseDate;
        try {
            expenseDate = LocalDate.parse(tokenArray[3]);
        } catch (Exception parseError) {
            throw new ParseException("Datum-Format yyyy-MM-dd");
        }
        String noteText = tokenArray.length > 4 ? fullInputLine.substring(fullInputLine.indexOf(tokenArray[4])) : "";
        return (budgetService, reportingService) -> {
            budgetService.addExpense(categoryName, amountMoney, expenseDate, noteText);
            return "Ausgabe erfasst.";
        };
    }

    static Command parseListExpenses(String[] tokenArray) {
        String maybeCategoryName = tokenArray.length > 1 ? tokenArray[1] : null;
        return (budgetService, reportingService) -> reportingService.listExpensesFormatted(maybeCategoryName);
    }


    static Command parseSummary(String[] tokenArray) throws ParseException {
        YearMonth yearMonth = YearMonth.now();
        if (tokenArray.length > 1) {
            try {
                yearMonth = YearMonth.parse(tokenArray[1]);
            } catch (Exception parseError) {
                throw new ParseException("summary [yyyy-MM]");
            }
        }
        YearMonth finalYearMonth = yearMonth;
        return new Command() {
            @Override
            public String execute(BudgetService budgetService, ReportingService reportingService) {
                return reportingService.monthlySummaryFormatted(finalYearMonth);
            }

            @Override
            public boolean isSummary() { return true; }
        };
    }
}
