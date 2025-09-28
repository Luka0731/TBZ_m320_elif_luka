package ch.tbz.carhireapplication.domain;

import java.util.UUID;

public interface Rentable {
    @Override
    String toString();

    UUID getId();

    double getFullPrice();

    boolean isAvailable();

    void setIsAvailable(boolean isAvailable);
}