package org.datacan.can_eco.Controller.Demander;

import org.datacan.can_eco.Model.Household;
import org.datacan.can_eco.Service.Demander.HouseholdService;
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
@RequestMapping("/api/v1/household")
@CrossOrigin(origins = "http://localhost:5173")
public class HouseholdController {

    @Autowired
    private HouseholdService householdService;

    @GetMapping("/")
    public ResponseEntity<List<Household>> getAllHousehold() {
        return ResponseEntity.ok(householdService.getAllHousehold());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Household> getHousehold(@PathVariable UUID id) {
        return ResponseEntity.ok(householdService.getHouseholdById(id));
    }

    @GetMapping("/count")
    public long getHouseholdCount() {
        return householdService.countHousehold();
    }

    @GetMapping("/category")
    public ResponseEntity<List<Object>> getByCategory(@RequestParam String category) {
        long countCategory = householdService.countByCategory(category);
        if (countCategory == 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<Household> categoryList = householdService.getAllHouseholdByCategory(category);

        List<Object> response = new ArrayList<>();
        response.add(categoryList);
        response.add(countCategory);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/price/less")
    public ResponseEntity<?> getByLessPrice(@RequestParam int price) {
        long countLess = householdService.countByPriceLessThan(price);
        List<Household> lessList = householdService.getAllHouseholdByPriceLessThan(price);

        if (countLess <= 0) {
            return new ResponseEntity<>("No household items in that price range", HttpStatus.NOT_FOUND);
        }

        List<Object> response = new ArrayList<>();
        response.add(countLess);
        response.add(lessList);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/price/greater")
    public ResponseEntity<?> getByGreaterPrice(@RequestParam int price) {
        long countGreater = householdService.countByPriceGreaterThan(price);
        List<Household> greaterList = householdService.getAllHouseholdByPriceGreaterThan(price);

        if (countGreater <= 0) {
            return new ResponseEntity<>("No household items in that price range", HttpStatus.NOT_FOUND);
        }

        List<Object> response = new ArrayList<>();
        response.add(countGreater);
        response.add(greaterList);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/price/between")
    public ResponseEntity<?> getByBetweenPrice(@RequestParam int low, @RequestParam int high) {
        long countBetween = householdService.countByPriceBetween(low, high);
        List<Household> betweenList = householdService.getAllHouseholdByPriceBetween(low, high);

        if (countBetween <= 0) {
            return new ResponseEntity<>("No household items in that price range", HttpStatus.NOT_FOUND);
        }

        List<Object> response = new ArrayList<>();
        response.add(countBetween);
        response.add(betweenList);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/filter")
    public ResponseEntity<Object> getFilter(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) Integer lowPrice,
            @RequestParam(required = false) Integer highPrice) {

        List<Household> all = householdService.getAllHousehold();

        List<Household> filtered = all.stream()
                .filter(h -> category == null || h.getCategory().equalsIgnoreCase(category))
                .filter(h -> lowPrice == null || h.getPrice() >= lowPrice)
                .filter(h -> highPrice == null || h.getPrice() <= highPrice)
                .collect(Collectors.toList());

        if (filtered.isEmpty()) return ResponseEntity.noContent().build();

        return ResponseEntity.ok(filtered);
    }
}
