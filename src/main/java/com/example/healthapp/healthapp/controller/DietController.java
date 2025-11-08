package com.example.healthapp.healthapp.controller;

import com.example.healthapp.healthapp.dto.DietDtos;
import com.example.healthapp.healthapp.entity.Diet;
import com.example.healthapp.healthapp.repository.DietRepository;

import jakarta.validation.Valid;                       // ✅ 꼭 있어야 함

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/diets")
public class DietController {

    private final DietRepository dietRepo;

    public DietController(DietRepository dietRepo) {
        this.dietRepo = dietRepo;
    }

    @GetMapping
    public List<Diet> getAll() {
        return dietRepo.findAll();
    }

    @GetMapping("/{id}")
    public Diet getOne(@PathVariable Integer id) {
        return dietRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Diet not found"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Diet create(@RequestBody @Valid DietDtos.Create req) {  // ✅ @Valid
        Diet d = new Diet();
        d.setUserId(req.userId);
        d.setMealDate(LocalDate.parse(req.mealDate));
        d.setMealType(req.mealType);
        d.setFoodName(req.foodName);
        d.setCalories(req.calories);
        d.setMemo(req.memo);
        return dietRepo.save(d);
    }

    @PostMapping("/bulk")
    @ResponseStatus(HttpStatus.CREATED)
    public List<Diet> bulkCreate(@RequestBody @Valid DietDtos.BulkCreate body) { // ✅ @Valid
        return body.items.stream().map(req -> {
            Diet d = new Diet();
            d.setUserId(req.userId);
            d.setMealDate(LocalDate.parse(req.mealDate));
            d.setMealType(req.mealType);
            d.setFoodName(req.foodName);
            d.setCalories(req.calories);
            d.setMemo(req.memo);
            return d;
        }).map(dietRepo::save).toList();
    }

    @PutMapping("/{id}")
    public Diet update(@PathVariable Integer id, @RequestBody @Valid DietDtos.Update req) { // ✅ @Valid
        Diet old = dietRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Diet not found"));
        old.setUserId(req.userId);
        old.setMealDate(LocalDate.parse(req.mealDate));
        old.setMealType(req.mealType);
        old.setFoodName(req.foodName);
        old.setCalories(req.calories);
        old.setMemo(req.memo);
        return dietRepo.save(old);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        if (!dietRepo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Diet not found");
        }
        dietRepo.deleteById(id);
    }
}
