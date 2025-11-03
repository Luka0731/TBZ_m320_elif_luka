package org.datacan.can_eco.Controller.Supplier;

import org.datacan.can_eco.Exception.PriceUnsuitable;
import org.datacan.can_eco.Model.Household;
import org.datacan.can_eco.Service.Supplier.AdminHouseholdService;
import org.datacan.can_eco.Service.Supplier.AdminIndustrialService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/admin/household")
@CrossOrigin("http://localhost:5173")
public class AdminHouseholdController {

    @Autowired
    AdminHouseholdService adminHouseholdService;

    @PostMapping("/")
    public ResponseEntity<Household> createHousehold(@Valid @RequestBody Household household) {
        if (household.getPrice() < 1) {
            throw new PriceUnsuitable("Price of the household product can't be under 1!");
        }
        return ResponseEntity.ok(adminHouseholdService.createHousehold(household));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Household> updateHousehold(@PathVariable UUID id, @Valid @RequestBody Household household) {
        return ResponseEntity.ok(adminHouseholdService.updateHousehold(id, household));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHousehold(@PathVariable UUID id) {
        adminHouseholdService.deleteHousehold(id);
        return ResponseEntity.noContent().build();
    }
}
