package org.datacan.can_eco.Service.Admin;

import jakarta.persistence.EntityNotFoundException;
import org.datacan.can_eco.Model.Household;
import org.datacan.can_eco.Repository.HouseholdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AdminHouseholdService {

    private final HouseholdRepository householdRepository;

    @Autowired
    public AdminHouseholdService(HouseholdRepository householdRepository) {
        this.householdRepository = householdRepository;
    }

    public Household createHousehold(Household household) {
        return householdRepository.save(household);
    }

    public Household updateHousehold(UUID id, Household household) {
        householdRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("There is no Household product with this id!"));
        household.setId(id);
        return householdRepository.save(household);
    }

    public void deleteHousehold(UUID id) {
        householdRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("There is no Household product with this id!"));
        householdRepository.deleteById(id);
    }

    public Household getHousehold(UUID id) {
        return householdRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("There is no Household product with this id!"));
    }
}
