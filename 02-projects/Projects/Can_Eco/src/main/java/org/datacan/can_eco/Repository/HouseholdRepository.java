package org.datacan.can_eco.Repository;

import org.datacan.can_eco.Model.Household;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface HouseholdRepository extends JpaRepository<Household, UUID> {

    long countByCategory(String category);

    long countByPriceIsLessThan(int price);

    long countByPriceIsGreaterThan(int price);

    long countByPriceIsBetween(int low, int high);

    List<Household> findByCategory(String category);

    List<Household> findByPriceIsLessThan(int price);

    List<Household> findByPriceIsGreaterThan(int price);

    List<Household> findByPriceIsBetween(int low, int high);
}
