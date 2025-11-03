package org.datacan.can_eco.Service.Demander;

import jakarta.persistence.EntityNotFoundException;
import org.datacan.can_eco.Exception.NotFound;
import org.datacan.can_eco.Model.Industrial;
import org.datacan.can_eco.Repository.IndustrialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class IndustrialService {

    @Autowired
    private IndustrialRepository industrialRepository;

    public List<Industrial> getAllIndustrial() {
        return industrialRepository.findAll();
    }

    public Industrial getIndustrialById(UUID id) {
        return industrialRepository.findById(id)
                .orElseThrow(() -> new NotFound("Industrial product with id " + id + " not found"));
    }

    public long countIndustrial() {
        return industrialRepository.count();
    }

    public long countByCategory(String category) {
        return industrialRepository.countByCategory(category);
    }

    public long countByPriceLessThan(int price) {
        return industrialRepository.countByPriceIsLessThan(price);
    }

    public long countByPriceGreaterThan(int price) {
        return industrialRepository.countByPriceIsGreaterThan(price);
    }

    public long countByPriceBetween(int low, int high) {
        return industrialRepository.countByPriceIsBetween(low, high);
    }

    public long countByPoisonous(boolean poisonous) {
        return industrialRepository.countByPoisonous(poisonous);
    }

    public List<Industrial> getAllIndustrialByCategory(String category) {
        return industrialRepository.findByCategory(category);
    }

    public List<Industrial> getAllIndustrialByPriceLessThan(int price) {
        return industrialRepository.findByPriceIsLessThan(price);
    }

    public List<Industrial> getAllIndustrialByPriceGreaterThan(int price) {
        return industrialRepository.findByPriceIsGreaterThan(price);
    }

    public List<Industrial> getAllIndustrialByPriceBetween(int low, int high) {
        return industrialRepository.findByPriceIsBetween(low, high);
    }

    public List<Industrial> getAllPoisonousIndustrial() {
        return industrialRepository.findByPoisonous(true);
    }

    public List<Industrial> getAllNonPoisonousIndustrial() {
        return industrialRepository.findByPoisonous(false);
    }
}
