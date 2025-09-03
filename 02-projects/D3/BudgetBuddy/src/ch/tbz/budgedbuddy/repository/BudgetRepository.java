package ch.tbz.budgedbuddy.repository;

import ch.tbz.budgedbuddy.domain.DataModel;
import ch.tbz.budgedbuddy.exception.StorageException;

public interface BudgetRepository {
    void save(DataModel dataModel) throws StorageException;
    DataModel load() throws StorageException;
}