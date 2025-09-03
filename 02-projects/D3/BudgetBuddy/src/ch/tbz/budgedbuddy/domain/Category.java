package ch.tbz.budgedbuddy.domain;

public class Category {
    private final String name;
    private final Money monthlyLimit;

    public Category(String name, Money monthlyLimit) {
        this.name = name;
        this.monthlyLimit = monthlyLimit;
    }

    public String getName() {
        return name;
    }

    public Money getMonthlyLimit() {
        return monthlyLimit;
    }
}