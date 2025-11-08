package com.example.healthapp.healthapp.repository;

import com.example.healthapp.healthapp.entity.Diet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface DietRepository extends JpaRepository<Diet, Integer> {

    // 조회 편의용 (필요할 때 사용)
    List<Diet> findByUserId(Integer userId);
    List<Diet> findByUserIdAndMealDate(Integer userId, LocalDate mealDate);
    List<Diet> findByUserIdAndMealDateBetween(Integer userId, LocalDate from, LocalDate to);
}
