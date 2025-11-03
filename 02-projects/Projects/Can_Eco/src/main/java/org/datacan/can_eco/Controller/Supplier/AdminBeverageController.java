package org.datacan.can_eco.Controller.Supplier;

import org.datacan.can_eco.Model.Beverage;
import org.datacan.can_eco.Service.Supplier.AdminBeverageService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/admin/beverage")
@CrossOrigin("http://localhost:5173")
public class AdminBeverageController {

    @Autowired
    private AdminBeverageService adminBeverageService;

    @PostMapping("/")
    public ResponseEntity<Beverage> createBeverage(@Valid @RequestBody Beverage beverage) {
        if (beverage.getPrice() < 1) {
            throw new IllegalArgumentException("Price of the beverage can't be under 1!");
        }
        return ResponseEntity.ok(adminBeverageService.createBeverage(beverage));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Beverage> updateBeverage(@PathVariable UUID id, @Valid @RequestBody Beverage beverage) {
        return ResponseEntity.ok(adminBeverageService.updateBeverage(id, beverage));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBeverage(@PathVariable UUID id) {
        adminBeverageService.deleteBeverage(id);
        return ResponseEntity.noContent().build();
    }
}
