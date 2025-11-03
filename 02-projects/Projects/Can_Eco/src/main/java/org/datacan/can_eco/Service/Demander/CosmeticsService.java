package org.datacan.can_eco.Service.Demander;

import jakarta.persistence.EntityNotFoundException;
import org.datacan.can_eco.Model.Cosmetics;
import org.datacan.can_eco.Repository.CosmeticsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CosmeticsService {

    @Autowired
    private CosmeticsRepository cosmeticsRepository;

    public List<Cosmetics> getAllCosmetics() {
        return cosmeticsRepository.findAll();
    }

    public Cosmetics getCosmeticsById(UUID id) {
        return cosmeticsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cosmetics with id " + id + " not found"));
    }

    public long countCosmetics() {
        return cosmeticsRepository.count();
    }

    public long countByCategory(String category) {
        return cosmeticsRepository.countByCategory(category);
    }

    public long countByPriceLessThan(int price) {
        return cosmeticsRepository.countByPriceIsLessThan(price);
    }

    public long countByPriceGreaterThan(int price) {
        return cosmeticsRepository.countByPriceIsGreaterThan(price);
    }

    public long countByPriceBetween(int low, int high) {
        return cosmeticsRepository.countByPriceIsBetween(low, high);
    }

    public long countByCrueltyFree(boolean crueltyFree) {
        return cosmeticsRepository.countByCrueltyFree(crueltyFree);
    }

    public long countByVegan(boolean vegan) {
        return cosmeticsRepository.countByVegan(vegan);
    }


    public List<Cosmetics> getAllCosmeticsByCategory(String category) {
        return cosmeticsRepository.findByCategory(category);
    }

    public List<Cosmetics> getAllCosmeticsByPriceLessThan(int price) {
        return cosmeticsRepository.findByPriceIsLessThan(price);
    }

    public List<Cosmetics> getAllCosmeticsByPriceGreaterThan(int price) {
        return cosmeticsRepository.findByPriceIsGreaterThan(price);
    }

    public List<Cosmetics> getAllCosmeticsByPriceBetween(int low, int high) {
        return cosmeticsRepository.findByPriceIsBetween(low, high);
    }

    public List<Cosmetics> getAllVeganCosmetics(boolean vegan) {
        return cosmeticsRepository.findByVegan(vegan);
    }

    public List<Cosmetics> getAllCrueltyFreeCosmetics(boolean crueltyFree) {
        return cosmeticsRepository.findByCrueltyFree(crueltyFree);
    }



}
