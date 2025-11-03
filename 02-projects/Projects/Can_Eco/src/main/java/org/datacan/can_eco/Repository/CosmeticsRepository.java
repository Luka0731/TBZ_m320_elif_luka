package org.datacan.can_eco.Repository;

import org.datacan.can_eco.Model.Cosmetics;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CosmeticsRepository extends JpaRepository<Cosmetics, UUID> {

    long countByCategory(String category);

    long countByPriceIsLessThan(int price);

    long countByPriceIsGreaterThan(int price);

    long countByPriceIsBetween(int low, int high);

    List<Cosmetics> findByCategory(String category);

    List<Cosmetics> findByPriceIsLessThan(int price);

    List<Cosmetics> findByPriceIsGreaterThan(int price);

    List<Cosmetics> findByPriceIsBetween(int low, int high);

    List<Cosmetics> findByVegan(boolean vegan);

    List<Cosmetics> findByCrueltyFree(boolean crueltyFree);
}
