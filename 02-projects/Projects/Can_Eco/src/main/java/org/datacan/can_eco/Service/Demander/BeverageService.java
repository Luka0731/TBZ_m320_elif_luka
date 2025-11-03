package org.datacan.can_eco.Service.Demander;

import jakarta.persistence.EntityNotFoundException;
import org.datacan.can_eco.Model.Beverage;
import org.datacan.can_eco.Repository.BeverageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BeverageService {

    @Autowired
    BeverageRepository beverageRepository;

    public List<Beverage> getAllBeverage() {
        return beverageRepository.findAll();
    }

    public Beverage getBeverageById(UUID id) {
        return (Beverage) beverageRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Beverage with id " + id + " not found"));
    }

    public long countBeverage() {
        return beverageRepository.count();
    }

    public long countAlcoholicBeverage() {
        return beverageRepository.countByAlcoholic(true);
    }

    public long countNonAlcoholicBeverage() {
        return beverageRepository.countByAlcoholic(false);
    }

    public long countByCatgory(String catgory) {
        return beverageRepository.countByCategory(catgory);
    }

    public long countByPriceLessThan(double price) {
        return beverageRepository.countByPriceIsLessThan(price);
    }

    public long countByPriceGreaterThan(double price) {
        return beverageRepository.countByPriceIsGreaterThan(price);
    }

    public long countByPriceBetwenn(double start, double end) {
        return beverageRepository.countByPriceIsBetween(start, end);
    }

    public List<BeverageRepository> getAllBeverageByCategory(String category) {
        return beverageRepository.findByCategory(category);
    }

    public List<BeverageRepository> getAllBeverageByPriceLessThan(double start, double end) {
        return beverageRepository.findByPriceIsLessThan(start);
    }

    public List<BeverageRepository> getAllBeverageByPriceGreaterThan(double start, double end) {
        return beverageRepository.findByPriceIsGreaterThan(start);
    }

    public List<BeverageRepository> getAllBeverageByPriceBetween(double start, double end) {
        return beverageRepository.findByPriceIsBetween(start, end);
    }

    public List<BeverageRepository> getAllNonAlcoholicBeverage() {
        return beverageRepository.findByAlcoholic(false);
    }

    public List<BeverageRepository> getAllAlcoholicBeverage() {
        return beverageRepository.findByAlcoholic(true);
    }


}
