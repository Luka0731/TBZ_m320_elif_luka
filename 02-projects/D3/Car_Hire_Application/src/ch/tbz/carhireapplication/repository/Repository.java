package ch.tbz.carhireapplication.repository;

import ch.tbz.carhireapplication.domain.Rentable;
import java.util.List;
import java.util.UUID;

public interface Repository<T extends Rentable> {
    T getById (UUID id);

    List<T> getAll ();
}
