package org.datacan.can_eco.Controller.Supplier;

import org.datacan.can_eco.Model.Cosmetics;
import org.datacan.can_eco.Service.Supplier.AdminCosmeticsService;
import org.datacan.can_eco.Service.Supplier.AdminIndustrialService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/admin/cosmetics")
@CrossOrigin("http://localhost:5173")
public class AdminCosmeticsController {

    @Autowired
    AdminCosmeticsService adminCosmeticsService;

    @PostMapping("/")
    public ResponseEntity<Cosmetics> createCosmetics(@Valid @RequestBody Cosmetics cosmetics) {
        if (cosmetics.getPrice() < 1) {
            throw new IllegalArgumentException("Price of the cosmetics product can't be under 1!");
        }
        return ResponseEntity.ok(adminCosmeticsService.createCosmetics(cosmetics));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cosmetics> updateCosmetics(@PathVariable UUID id, @Valid @RequestBody Cosmetics cosmetics) {
        return ResponseEntity.ok(adminCosmeticsService.updateCosmetics(id, cosmetics));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCosmetics(@PathVariable UUID id) {
        adminCosmeticsService.deleteCosmetics(id);
        return ResponseEntity.noContent().build();
    }
}
