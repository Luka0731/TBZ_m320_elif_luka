package ch.tbz.budgedbuddy.service;

import ch.tbz.budgedbuddy.domain.Category;
import ch.tbz.budgedbuddy.domain.DataModel;
import ch.tbz.budgedbuddy.domain.Expense;
import ch.tbz.budgedbuddy.domain.Money;
import ch.tbz.budgedbuddy.exception.BudgetExceededException;
import ch.tbz.budgedbuddy.exception.StorageException;
import ch.tbz.budgedbuddy.exception.ValidationException;
import ch.tbz.budgedbuddy.repository.BudgetRepository;
import ch.tbz.budgedbuddy.util.Calculator;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BudgetService {
    private BudgetRepository budgetRepository;
    private final DataModel dataModel = new DataModel();


    public BudgetService(BudgetRepository budgetRepository) {
        this.budgetRepository = budgetRepository;
    }

    public void addCategory(String categoryName, Money monthlyLimit) throws ValidationException {
        if (categoryName == null || categoryName.isBlank()) {
            throw new ValidationException("Kategorie-Name darf nicht leer sein");
        }

        boolean categoryExists = dataModel.categories.stream()
                .anyMatch(existingCategory -> existingCategory.getName().equalsIgnoreCase(categoryName));
        if (categoryExists) {
            throw new ValidationException("Kategorie existiert bereits: " + categoryName);
        }

        if (monthlyLimit.getCents() <= 0) {
            throw new ValidationException("Limit muss größer als 0 sein");
        }

        dataModel.categories.add(new Category(categoryName, monthlyLimit));
    }

    public void addExpense(String categoryName, Money amount, LocalDate date, String note)
            throws ValidationException, BudgetExceededException {

        if (amount.getCents() <= 0) {
            throw new ValidationException("Betrag muss größer als 0 sein");
        }

        boolean categoryExists = dataModel.categories.stream()
                .anyMatch(category -> category.getName().equalsIgnoreCase(categoryName));
        if (!categoryExists) {
            throw new ValidationException("Unbekannte Kategorie: " + categoryName);
        }

        dataModel.expenses.add(new Expense(categoryName, amount, date, note));

        // Budgetprüfung
        YearMonth yearMonth = YearMonth.from(date);
        Money spentMoney = Calculator.spentForCategory(categoryName, dataModel.expenses, yearMonth);
        Money limitMoney = dataModel.categories.stream()
                .filter(category -> category.getName().equalsIgnoreCase(categoryName))
                .findFirst().get().getMonthlyLimit();

        if (spentMoney.getCents() > limitMoney.getCents()) {
            throw new BudgetExceededException("Kategorie '" + categoryName + "' überschritten. Ausgegeben: "
                    + spentMoney + " / Limit: " + limitMoney);
        }
    }

    public List<Category> listCategories() {
        return List.copyOf(dataModel.categories);
    }

    public List<Expense> listExpenses(String categoryNameOrNull) {
        if (categoryNameOrNull == null) {
            return List.copyOf(dataModel.expenses);
        }
        List<Expense> filteredExpenses = new ArrayList<>();
        for (Expense expense : dataModel.expenses) {
            if (expense.getCategory().equalsIgnoreCase(categoryNameOrNull)) {
                filteredExpenses.add(expense);
            }
        }
        return filteredExpenses;
    }

    public Map<String, Money> spentPerCategory(YearMonth yearMonth) {
        Map<String, Money> spentMap = new LinkedHashMap<>();
        for (Category category : dataModel.categories) {
            spentMap.put(category.getName(),
                    Calculator.spentForCategory(category.getName(), dataModel.expenses, yearMonth));
        }
        return spentMap;
    }

    public void save() throws StorageException {
        budgetRepository.save(dataModel);
    }

    public void load() throws StorageException {
        DataModel loadedModel = budgetRepository.load();
        dataModel.categories.clear();
        dataModel.categories.addAll(loadedModel.categories);
        dataModel.expenses.clear();
        dataModel.expenses.addAll(loadedModel.expenses);
        dataModel.currentMonth = loadedModel.currentMonth;
    }
}
