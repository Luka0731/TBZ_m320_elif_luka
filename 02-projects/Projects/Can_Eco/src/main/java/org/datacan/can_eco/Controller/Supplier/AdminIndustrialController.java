package org.datacan.can_eco.Controller.Supplier;

import org.datacan.can_eco.Model.Industrial;
import org.datacan.can_eco.Service.Supplier.AdminIndustrialService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/admin/industrial")
@CrossOrigin("http://localhost:5173")
public class AdminIndustrialController {

    @Autowired
    private AdminIndustrialService adminIndustrialService;

    @PostMapping("/")
    public ResponseEntity<Industrial> createIndustrial(@Valid @RequestBody Industrial industrial) {
        if (industrial.getPrice() < 1) {
            throw new IllegalArgumentException("Price of the industrial product can't be under 1!");
        }
        return ResponseEntity.ok(adminIndustrialService.createIndustrial(industrial));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Industrial> updateIndustrial(@PathVariable UUID id, @Valid @RequestBody Industrial industrial) {
        return ResponseEntity.ok(adminIndustrialService.updateIndustrial(id, industrial));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIndustrial(@PathVariable UUID id) {
        adminIndustrialService.deleteIndustrial(id);
        return ResponseEntity.noContent().build();
    }
}
