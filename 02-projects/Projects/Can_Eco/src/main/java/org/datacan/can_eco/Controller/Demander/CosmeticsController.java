package org.datacan.can_eco.Controller.Demander;

import org.datacan.can_eco.Model.Cosmetics;
import org.datacan.can_eco.Service.Demander.CosmeticsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/cosmetics")
@CrossOrigin(origins = "http://localhost:5173")
public class CosmeticsController {

    @Autowired
    CosmeticsService cosmeticsService;

    @GetMapping("/")
    public ResponseEntity<List<Cosmetics>> getAllCosmetics() {
        return ResponseEntity.ok(cosmeticsService.getAllCosmetics());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cosmetics> getCosmetics(@PathVariable UUID id) {
        return ResponseEntity.ok(cosmeticsService.getCosmeticsById(id));
    }

    @GetMapping("/count")
    public long getCosmeticsCount() {
        return cosmeticsService.countCosmetics();
    }

    @GetMapping("/category")
    public ResponseEntity<List<Object>> getByCategory(@RequestParam String category) {
        long countCategory = cosmeticsService.countByCategory(category);
        if (countCategory == 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<Cosmetics> categoryList = cosmeticsService.getAllCosmeticsByCategory(category);

        List<Object> response = new ArrayList<>();
        response.add(categoryList);
        response.add(countCategory);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/price/less")
    public ResponseEntity<?> getByLessPrice(@RequestParam int price) {
        long countLess = cosmeticsService.countByPriceLessThan(price);
        List<Cosmetics> lessList = cosmeticsService.getAllCosmeticsByPriceLessThan(price);

        if (countLess <= 0) {
            return new ResponseEntity<>("No cosmetics in that price range", HttpStatus.NOT_FOUND);
        }

        List<Object> response = new ArrayList<>();
        response.add(countLess);
        response.add(lessList);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/price/greater")
    public ResponseEntity<?> getByGreaterPrice(@RequestParam int price) {
        long countGreater = cosmeticsService.countByPriceGreaterThan(price);
        List<Cosmetics> greaterList = cosmeticsService.getAllCosmeticsByPriceGreaterThan(price);
        if (countGreater <= 0) {
            return new ResponseEntity<>("No cosmetics in that price range", HttpStatus.NOT_FOUND);
        }
        List<Object> response = new ArrayList<>();
        response.add(countGreater);
        response.add(greaterList);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/price/between")
    public ResponseEntity<?> getByBetweenPrice(@RequestParam int low, @RequestParam int high) {
        long countBetween = cosmeticsService.countByPriceBetween(low, high);
        List<Cosmetics> betweenList = cosmeticsService.getAllCosmeticsByPriceBetween(low, high);
        if (countBetween <= 0) {
            return new ResponseEntity<>("No cosmetics in that price range", HttpStatus.NOT_FOUND);
        }
        List<Object> response = new ArrayList<>();
        response.add(countBetween);
        response.add(betweenList);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/vegan")
    public ResponseEntity<?> getByVegan(@RequestParam boolean vegan) {
        long countVegan = cosmeticsService.countByVegan(vegan);
        List<Cosmetics> veganList = cosmeticsService.getAllVeganCosmetics(vegan);
        if (countVegan <= 0) {
            return new ResponseEntity<>("No cosmetics with the specified vegan status", HttpStatus.NOT_FOUND);
        }
        List<Object> response = new ArrayList<>();
        response.add(countVegan);
        response.add(veganList);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/crueltyFree")
    public ResponseEntity<?> getByCrueltyFree(@RequestParam boolean crueltyFree) {
        long countCF = cosmeticsService.countByCrueltyFree(crueltyFree);
        List<Cosmetics> cfList = cosmeticsService.getAllCrueltyFreeCosmetics(crueltyFree);
        if (countCF <= 0) {
            return new ResponseEntity<>("No cosmetics with the specified cruelty-free status", HttpStatus.NOT_FOUND);
        }
        List<Object> response = new ArrayList<>();
        response.add(countCF);
        response.add(cfList);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/filter")
    public ResponseEntity<Object> getFilter(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) Integer lowPrice,
            @RequestParam(required = false) Integer highPrice,
            @RequestParam(required = false) Boolean vegan,
            @RequestParam(required = false) Boolean crueltyFree) {

        List<Cosmetics> all = cosmeticsService.getAllCosmetics();

        List<Cosmetics> filtered = all.stream()
                .filter(c -> vegan == null || c.isVegan() == vegan)
                .filter(c -> crueltyFree == null || c.isCruelty_free() == crueltyFree)
                .filter(c -> category == null || c.getCategory().equalsIgnoreCase(category))
                .filter(c -> highPrice == null || c.getPrice() <= highPrice)
                .filter(c -> lowPrice == null || c.getPrice() >= lowPrice)
                .collect(Collectors.toList());

        if (filtered.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(filtered);
    }
}
