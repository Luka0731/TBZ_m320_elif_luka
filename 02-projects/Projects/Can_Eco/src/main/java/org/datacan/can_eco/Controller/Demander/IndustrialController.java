package org.datacan.can_eco.Controller.Demander;

import org.datacan.can_eco.Model.Industrial;
import org.datacan.can_eco.Service.Demander.IndustrialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/industrial")
@CrossOrigin(origins = "http://localhost:5173")
public class IndustrialController {

    @Autowired
    private IndustrialService industrialService;

    @GetMapping("/")
    public ResponseEntity<List<Industrial>> getAllIndustrial() {
        return ResponseEntity.ok(industrialService.getAllIndustrial());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Industrial> getIndustrial(@PathVariable UUID id) {
        return ResponseEntity.ok(industrialService.getIndustrialById(id));
    }

    @GetMapping("/count")
    public long getIndustrialCount() {
        return industrialService.countIndustrial();
    }

    @GetMapping("/category")
    public ResponseEntity<List<Object>> getByCategory(@RequestParam String category) {
        long countCategory = industrialService.countByCategory(category);
        if (countCategory == 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<Industrial> categoryList = industrialService.getAllIndustrialByCategory(category);

        List<Object> response = new ArrayList<>();
        response.add(categoryList);
        response.add(countCategory);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/price/less")
    public ResponseEntity<?> getByLessPrice(@RequestParam int price) {
        long countLess = industrialService.countByPriceLessThan(price);
        List<Industrial> lessList = industrialService.getAllIndustrialByPriceLessThan(price);

        if (countLess == 0) {
            return new ResponseEntity<>("No industrial products in that price range", HttpStatus.NOT_FOUND);
        }

        List<Object> response = new ArrayList<>();
        response.add(countLess);
        response.add(lessList);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/price/greater")
    public ResponseEntity<?> getByGreaterPrice(@RequestParam int price) {
        long countGreater = industrialService.countByPriceGreaterThan(price);
        List<Industrial> greaterList = industrialService.getAllIndustrialByPriceGreaterThan(price);

        if (countGreater == 0) {
            return new ResponseEntity<>("No industrial products in that price range", HttpStatus.NOT_FOUND);
        }

        List<Object> response = new ArrayList<>();
        response.add(countGreater);
        response.add(greaterList);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/price/between")
    public ResponseEntity<?> getByBetweenPrice(@RequestParam int low, @RequestParam int high) {
        long countBetween = industrialService.countByPriceBetween(low, high);
        List<Industrial> betweenList = industrialService.getAllIndustrialByPriceBetween(low, high);

        if (countBetween == 0) {
            return new ResponseEntity<>("No industrial products in that price range", HttpStatus.NOT_FOUND);
        }

        List<Object> response = new ArrayList<>();
        response.add(countBetween);
        response.add(betweenList);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/poisonous")
    public ResponseEntity<?> getPoisonous(@RequestParam boolean poisonous) {
        long countPoisonous = industrialService.countByPoisonous(poisonous);
        List<Industrial> listPoisonous = poisonous ?
                industrialService.getAllPoisonousIndustrial() :
                industrialService.getAllNonPoisonousIndustrial();

        if (countPoisonous == 0) {
            return new ResponseEntity<>("No industrial products found with poisonous = " + poisonous, HttpStatus.NOT_FOUND);
        }

        List<Object> response = new ArrayList<>();
        response.add(countPoisonous);
        response.add(listPoisonous);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
