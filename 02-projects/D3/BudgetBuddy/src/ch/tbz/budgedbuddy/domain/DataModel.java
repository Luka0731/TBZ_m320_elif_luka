package ch.tbz.budgedbuddy.domain;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

public class DataModel {
    public final List<Category> categories = new ArrayList<>();
    public final List<Expense> expenses = new ArrayList<>();
    public YearMonth currentMonth = YearMonth.now();
}