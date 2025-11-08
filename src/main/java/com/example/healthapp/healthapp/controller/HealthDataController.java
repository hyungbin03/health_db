package com.example.healthapp.healthapp.controller;

import com.example.healthapp.healthapp.entity.HealthData;
import com.example.healthapp.healthapp.repository.HealthDataRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/health-data")
public class HealthDataController {
    private final HealthDataRepository repo;
    public HealthDataController(HealthDataRepository repo){ this.repo = repo; }

    @GetMapping public List<HealthData> list(){ return repo.findAll(); }

    @GetMapping("/user/{userId}")
    public List<HealthData> listByUser(
            @PathVariable Integer userId,
            @RequestParam(required=false) LocalDate from,
            @RequestParam(required=false) LocalDate to
    ){
        if(from!=null && to!=null) return repo.findByUserIdAndRecordDateBetween(userId, from, to);
        return repo.findByUserId(userId);
    }

    @GetMapping("/{id}")
    public HealthData get(@PathVariable Integer id){ return repo.findById(id).orElseThrow(); }

    @PostMapping @ResponseStatus(HttpStatus.CREATED)
    public HealthData create(@RequestBody HealthData h){ h.setHealthId(null); return repo.save(h); }

    @PutMapping("/{id}")
    public HealthData update(@PathVariable Integer id, @RequestBody HealthData h){
        HealthData old = repo.findById(id).orElseThrow();
        old.setUserId(h.getUserId());
        old.setRecordDate(h.getRecordDate());
        old.setWeight(h.getWeight());
        old.setBloodPressure(h.getBloodPressure());
        old.setHeartRate(h.getHeartRate());
        return repo.save(old);
    }

    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id){ repo.deleteById(id); }
}
