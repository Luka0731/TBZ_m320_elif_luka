package org.datacan.can_eco.Controller.Demander;

import org.datacan.can_eco.Model.Beverage;
import org.datacan.can_eco.Repository.BeverageRepository;
import org.datacan.can_eco.Service.Demander.BeverageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/beverage")
@CrossOrigin(origins = "http://localhost:5173")
public class BeverageController {

    @Autowired
    BeverageService beverageService;

    @GetMapping("/")
    public ResponseEntity<List<Beverage>> getAllBeverages() {
        return ResponseEntity.ok(beverageService.getAllBeverage());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Beverage> getBeverage(@PathVariable UUID id) {
        return ResponseEntity.ok(beverageService.getBeverageById(id));
    }

    @GetMapping("/count")
    public long getBeverageCount() {
        return beverageService.countBeverage();
    }

    @GetMapping("/category")
    public ResponseEntity<List<Object>> getByCategory(@RequestParam String category) {
        long countCategory = beverageService.countByCatgory(category);
        if (countCategory == 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<BeverageRepository> categoryList = beverageService.getAllBeverageByCategory(category);

        List<Object> response = new ArrayList<>();
        response.add(categoryList);
        response.add(countCategory);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/price/less")
    public ResponseEntity<?> getByLessPrice(@RequestParam double price) {
        long countLess = beverageService.countByPriceLessThan(price);
        List<BeverageRepository> lessList = beverageService.getAllBeverageByPriceLessThan(price, price);

        if (countLess <= 0) {
            return new ResponseEntity<>("No beverages in that price range", HttpStatus.NOT_FOUND);
        }

        List<Object> response = new ArrayList<>();
        response.add(countLess);
        response.add(lessList);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/price/greater")
    public ResponseEntity<?> getByGreaterPrice(@RequestParam double price) {
        long countGreater = beverageService.countByPriceGreaterThan(price);
        List<BeverageRepository> greaterList = beverageService.getAllBeverageByPriceGreaterThan(price, price);
        if (countGreater <= 0) {
            return new ResponseEntity<>("No beverages in that price range", HttpStatus.NOT_FOUND);
        }
        List<Object> response = new ArrayList<>();
        response.add(countGreater);
        response.add(greaterList);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/price/between")
    public ResponseEntity<?> getByBetweenPrice(@RequestParam double start, @RequestParam double end) {
        long countBetween = beverageService.countByPriceBetwenn(start, end);
        List<BeverageRepository> betweenList = beverageService.getAllBeverageByPriceBetween(start, end);
        if (countBetween <= 0) {
            return new ResponseEntity<>("No beverages in that price range", HttpStatus.NOT_FOUND);
        }
        List<Object> response = new ArrayList<>();
        response.add(countBetween);
        response.add(betweenList);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/alcoholic")
    public ResponseEntity<?> getByAlcoholic(@RequestParam boolean alcoholic) {
        List<Object> response = new ArrayList<>();
        long countAlcoholic;
        List<BeverageRepository> alcoholicBeverages;
        if (alcoholic) {
            countAlcoholic = beverageService.countAlcoholicBeverage();
            alcoholicBeverages = beverageService.getAllAlcoholicBeverage();
        } else {
            countAlcoholic = beverageService.countNonAlcoholicBeverage();
            alcoholicBeverages = beverageService.getAllNonAlcoholicBeverage();
        }

        response.add(countAlcoholic);
        response.add(alcoholicBeverages);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/filter")
    public ResponseEntity<Object> getFilter(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) Double lowPrice,
            @RequestParam(required = false) Double highPrice,
            @RequestParam(required = false) Boolean alcoholic) {

        List<Beverage> all = beverageService.getAllBeverage();

        List<Beverage> filtered = all.stream()
                .filter(b -> alcoholic == null || b.getAlcoholic () == alcoholic)
                .filter(b -> category == null || b.getCategory().equalsIgnoreCase(category))
                .filter(b -> highPrice == null || b.getPrice() <= highPrice)
                .filter(b -> lowPrice == null || b.getPrice() >= lowPrice)
                .collect(Collectors.toList());

        if (filtered.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(filtered);
    }
}
