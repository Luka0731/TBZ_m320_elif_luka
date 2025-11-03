package org.datacan.can_eco.Service.Demander;

import jakarta.persistence.EntityNotFoundException;
import org.datacan.can_eco.Model.Household;
import org.datacan.can_eco.Repository.HouseholdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HouseholdService {

    @Autowired
    private HouseholdRepository householdRepository;

    public List<Household> getAllHousehold() {
        return householdRepository.findAll();
    }

    public Household getHouseholdById(UUID id) {
        return householdRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Household with id " + id + " not found"));
    }

    public long countHousehold() {
        return householdRepository.count();
    }

    public long countByCategory(String category) {
        return householdRepository.countByCategory(category);
    }

    public long countByPriceLessThan(int price) {
        return householdRepository.countByPriceIsLessThan(price);
    }

    public long countByPriceGreaterThan(int price) {
        return householdRepository.countByPriceIsGreaterThan(price);
    }

    public long countByPriceBetween(int low, int high) {
        return householdRepository.countByPriceIsBetween(low, high);
    }

    public List<Household> getAllHouseholdByCategory(String category) {
        return householdRepository.findByCategory(category);
    }

    public List<Household> getAllHouseholdByPriceLessThan(int price) {
        return householdRepository.findByPriceIsLessThan(price);
    }

    public List<Household> getAllHouseholdByPriceGreaterThan(int price) {
        return householdRepository.findByPriceIsGreaterThan(price);
    }

    public List<Household> getAllHouseholdByPriceBetween(int low, int high) {
        return householdRepository.findByPriceIsBetween(low, high);
    }
}
