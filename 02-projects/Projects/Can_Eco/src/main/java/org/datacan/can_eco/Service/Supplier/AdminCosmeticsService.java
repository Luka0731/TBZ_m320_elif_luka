package org.datacan.can_eco.Service.Supplier;

import jakarta.persistence.EntityNotFoundException;
import org.datacan.can_eco.Model.Cosmetics;
import org.datacan.can_eco.Repository.CosmeticsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AdminCosmeticsService {

    private final CosmeticsRepository cosmeticsRepository;

    @Autowired
    public AdminCosmeticsService(CosmeticsRepository cosmeticsRepository) {
        this.cosmeticsRepository = cosmeticsRepository;
    }

    public Cosmetics createCosmetics(Cosmetics cosmetics) {
        return cosmeticsRepository.save(cosmetics);
    }

    public Cosmetics updateCosmetics(UUID id, Cosmetics cosmetics) {
        cosmeticsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("There is no Cosmetics product with this id!"));
        cosmetics.setId(id);
        return cosmeticsRepository.save(cosmetics);
    }

    public void deleteCosmetics(UUID id) {
        cosmeticsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("There is no Cosmetics product with this id!"));
        cosmeticsRepository.deleteById(id);
    }

    public Cosmetics getCosmetics(UUID id) {
        return cosmeticsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("There is no Cosmetics product with this id!"));
    }
}
