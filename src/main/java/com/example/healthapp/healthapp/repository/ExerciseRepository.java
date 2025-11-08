package com.example.healthapp.healthapp.repository;

import com.example.healthapp.healthapp.entity.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ExerciseRepository extends JpaRepository<Exercise, Integer> {
    List<Exercise> findByUserId(Integer userId);
    List<Exercise> findByUserIdAndExerciseDateBetween(Integer userId, LocalDate from, LocalDate to);
}
