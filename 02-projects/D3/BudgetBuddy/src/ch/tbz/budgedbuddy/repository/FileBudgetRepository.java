package ch.tbz.budgedbuddy.repository;

import ch.tbz.budgedbuddy.domain.Category;
import ch.tbz.budgedbuddy.domain.DataModel;
import ch.tbz.budgedbuddy.domain.Expense;
import ch.tbz.budgedbuddy.domain.Money;
import ch.tbz.budgedbuddy.exception.StorageException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;

public class FileBudgetRepository implements BudgetRepository {
    private final Path dataFolderPath;

    public FileBudgetRepository(Path dataFolderPath) {
        this.dataFolderPath = dataFolderPath;
    }

    @Override
    public void save(DataModel dataModel) throws StorageException {
        try {
            Files.createDirectories(dataFolderPath);

            Path categoriesFilePath = dataFolderPath.resolve("categories.csv");
            try (BufferedWriter bufferedWriter = Files.newBufferedWriter(categoriesFilePath, StandardCharsets.UTF_8)) {
                bufferedWriter.write("name;limit_cents\n");
                for (Category category : dataModel.categories) {
                    String line = category.getName() + ";" + category.getMonthlyLimit().getCents() + "\n";
                    bufferedWriter.write(line);
                }
            }

            Path expensesFilePath = dataFolderPath.resolve("expenses.csv");
            try (BufferedWriter bufferedWriter = Files.newBufferedWriter(expensesFilePath, StandardCharsets.UTF_8)) {
                bufferedWriter.write("date;category;amount_cents;note\n");
                for (Expense expense : dataModel.expenses) {
                    String noteText = expense.getNote() == null ? "" : expense.getNote().replace("\n", " ");
                    String line = expense.getDate()
                            + ";" + expense.getCategory()
                            + ";" + expense.getAmount().getCents()
                            + ";" + noteText
                            + "\n";
                    bufferedWriter.write(line);
                }
            }

        } catch (IOException ioException) {
            throw new StorageException("Speichern fehlgeschlagen", ioException);
        }
    }

    @Override
    public DataModel load() throws StorageException {
        DataModel dataModel = new DataModel();

        try {
            Path categoriesFilePath = dataFolderPath.resolve("categories.csv");
            if (Files.exists(categoriesFilePath)) {
                try (BufferedReader bufferedReader = Files.newBufferedReader(categoriesFilePath, StandardCharsets.UTF_8)) {
                    String lineText = bufferedReader.readLine(); // Header
                    while ((lineText = bufferedReader.readLine()) != null) {
                        if (lineText.isBlank()) {
                            continue;
                        }
                        String[] parts = lineText.split(";");
                        if (parts.length >= 2) {
                            String categoryName = parts[0];
                            int limitCents = Integer.parseInt(parts[1]);
                            dataModel.categories.add(new Category(categoryName, new Money(limitCents)));
                        }
                    }
                }
            }

            Path expensesFilePath = dataFolderPath.resolve("expenses.csv");
            if (Files.exists(expensesFilePath)) {
                try (BufferedReader bufferedReader = Files.newBufferedReader(expensesFilePath, StandardCharsets.UTF_8)) {
                    String lineText = bufferedReader.readLine(); // Header
                    while ((lineText = bufferedReader.readLine()) != null) {
                        if (lineText.isBlank()) {
                            continue;
                        }
                        String[] parts = lineText.split(";");
                        if (parts.length >= 4) {
                            LocalDate expenseDate = LocalDate.parse(parts[0]);
                            String categoryName = parts[1];
                            int amountCents = Integer.parseInt(parts[2]);
                            String noteText = parts[3];
                            dataModel.expenses.add(new Expense(categoryName, new Money(amountCents), expenseDate, noteText));
                        }
                    }
                }
            }

            return dataModel;

        } catch (IOException ioException) {
            throw new StorageException("Laden fehlgeschlagen", ioException);
        }
    }
}