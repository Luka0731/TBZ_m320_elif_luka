package org.datacan.can_eco.Service.Supplier;

import jakarta.persistence.EntityNotFoundException;
import org.datacan.can_eco.Model.Beverage;
import org.datacan.can_eco.Repository.BeverageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AdminBeverageService {

    private final BeverageRepository beverageRepository;

    @Autowired
    public AdminBeverageService(BeverageRepository beverageRepository) {
        this.beverageRepository = beverageRepository;
    }

    public Beverage createBeverage(Beverage beverage) {
        return beverageRepository.save(beverage);
    }

    public Beverage updateBeverage(UUID id, Beverage beverage) {
        beverageRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("There is no Beverage with this id!"));
        beverage.setId(id);
        return beverageRepository.save(beverage);
    }

    public void deleteBeverage(UUID id) {
        beverageRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("There is no Beverage with this id!"));
        beverageRepository.deleteById(id);
    }

    public Beverage getBeverage(UUID id) {
        return (Beverage) beverageRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("There is no Beverage with this id!"));
    }
}
