package com.example.healthapp.healthapp.repository;

import com.example.healthapp.healthapp.entity.HealthData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface HealthDataRepository extends JpaRepository<HealthData, Integer> {
    List<HealthData> findByUserId(Integer userId);
    List<HealthData> findByUserIdAndRecordDateBetween(Integer userId, LocalDate from, LocalDate to);
}
