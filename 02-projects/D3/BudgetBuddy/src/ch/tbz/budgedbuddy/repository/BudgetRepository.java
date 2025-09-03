package ch.tbz.budgedbuddy.repository;

import ch.tbz.budgedbuddy.domain.DataModel;

public interface BudgetRepository {
    void save(DataModel dataModel);

    DataModel load();
}
