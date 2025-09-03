package ch.tbz.budgedbuddy.domain;

import java.time.LocalDate;

public class Expense {
    private final String category;
    private final Money amount;
    private final LocalDate date;
    private final String note;

    public Expense(String category, Money amount, LocalDate date, String note) {
        this.category = category;
        this.amount = amount;
        this.date = date;
        this.note = note;
    }

    public String getCategory() {
        return category;
    }

    public Money getAmount() {
        return amount;
    }

    public LocalDate getDate() {
        return date;

    }
    public String getNote() {
        return note;
    }
}