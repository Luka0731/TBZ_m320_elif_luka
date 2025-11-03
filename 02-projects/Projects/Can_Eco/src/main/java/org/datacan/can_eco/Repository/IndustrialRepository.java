package org.datacan.can_eco.Repository;

import org.datacan.can_eco.Model.Industrial;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface IndustrialRepository extends JpaRepository<Industrial, UUID> {

    long countByCategory(String category);

    long countByPriceIsLessThan(int price);

    long countByPriceIsGreaterThan(int price);

    long countByPriceIsBetween(int low, int high);

    long countByPoisonous(boolean poisonous);

    List<Industrial> findByCategory(String category);

    List<Industrial> findByPriceIsLessThan(int price);

    List<Industrial> findByPriceIsGreaterThan(int price);

    List<Industrial> findByPriceIsBetween(int low, int high);

    List<Industrial> findByPoisonous(boolean poisonous);
}
