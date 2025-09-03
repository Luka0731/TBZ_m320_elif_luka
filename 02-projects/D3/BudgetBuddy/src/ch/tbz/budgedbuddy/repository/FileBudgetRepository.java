package ch.tbz.budgedbuddy.repository;

import ch.tbz.budgedbuddy.domain.DataModel;

public class FileBudgetRepository implements BudgetRepository {
    public FileBudgetRepository(Object path) {
    }

    @Override
    public void save(DataModel dataModel) {

    }

    @Override
    public DataModel load() {
        return null;
    }
}
