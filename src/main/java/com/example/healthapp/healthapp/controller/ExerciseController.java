package com.example.healthapp.healthapp.controller;

import com.example.healthapp.healthapp.entity.Exercise;
import com.example.healthapp.healthapp.repository.ExerciseRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/exercises")
public class ExerciseController {
    private final ExerciseRepository repo;
    public ExerciseController(ExerciseRepository repo){ this.repo = repo; }

    @GetMapping public List<Exercise> list(){ return repo.findAll(); }

    @GetMapping("/user/{userId}")
    public List<Exercise> listByUser(
            @PathVariable Integer userId,
            @RequestParam(required=false) LocalDate from,
            @RequestParam(required=false) LocalDate to
    ){
        if(from!=null && to!=null) return repo.findByUserIdAndExerciseDateBetween(userId, from, to);
        return repo.findByUserId(userId);
    }

    @GetMapping("/{id}")
    public Exercise get(@PathVariable Integer id){ return repo.findById(id).orElseThrow(); }

    @PostMapping @ResponseStatus(HttpStatus.CREATED)
    public Exercise create(@RequestBody Exercise e){ e.setExerciseId(null); return repo.save(e); }

    @PutMapping("/{id}")
    public Exercise update(@PathVariable Integer id, @RequestBody Exercise e){
        Exercise old = repo.findById(id).orElseThrow();
        old.setUserId(e.getUserId());
        old.setExerciseDate(e.getExerciseDate());
        old.setExerciseType(e.getExerciseType());
        old.setDuration(e.getDuration());
        old.setCaloriesBurned(e.getCaloriesBurned());
        return repo.save(old);
    }

    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id){ repo.deleteById(id); }
}
