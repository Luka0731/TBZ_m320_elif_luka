package org.datacan.can_eco.Repository;

import org.datacan.can_eco.Model.Beverage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface BeverageRepository extends JpaRepository<Beverage, UUID> {


    long countByAlcoholic(boolean alcoholic);

    long countByCategory(String category);

    long countByPriceIsLessThan(double price);

    long countByPriceIsGreaterThan(double price);

    long countByPriceIsBetween(double low, double high);



    List<BeverageRepository> findByAlcoholic(boolean alcoholic);

    List<BeverageRepository> findByCategory(String category);

    List<BeverageRepository> findByPriceIsLessThan(double price);

    List<BeverageRepository> findByPriceIsGreaterThan(double price);

    List<BeverageRepository> findByPriceIsBetween(double low, double high);
}
