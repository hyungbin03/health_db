package com.example.healthapp.healthapp.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "health_data")
public class HealthData {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "health_id")
    private Integer healthId;

    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @Column(name = "record_date", nullable = false)
    private LocalDate recordDate;

    private Double weight;            // kg
    @Column(name = "blood_pressure")
    private String bloodPressure;     // "120/80" 형식
    @Column(name = "heart_rate")
    private Integer heartRate;        // bpm

    // getter/setter
    public Integer getHealthId() { return healthId; }
    public void setHealthId(Integer healthId) { this.healthId = healthId; }
    public Integer getUserId() { return userId; }
    public void setUserId(Integer userId) { this.userId = userId; }
    public LocalDate getRecordDate() { return recordDate; }
    public void setRecordDate(LocalDate recordDate) { this.recordDate = recordDate; }
    public Double getWeight() { return weight; }
    public void setWeight(Double weight) { this.weight = weight; }
    public String getBloodPressure() { return bloodPressure; }
    public void setBloodPressure(String bloodPressure) { this.bloodPressure = bloodPressure; }
    public Integer getHeartRate() { return heartRate; }
    public void setHeartRate(Integer heartRate) { this.heartRate = heartRate; }
}
