package com.example.healthapp.healthapp.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "diet")
public class Diet {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer dietId;

    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @Column(nullable = false) private LocalDate mealDate;
    @Column(nullable = false) private String mealType;
    @Column(nullable = false) private String foodName;
    private Integer calories;
    private String memo;

    // ===== Getter/Setter 전부 생성 =====
    public Integer getDietId() { return dietId; }
    public void setDietId(Integer dietId) { this.dietId = dietId; }

    public Integer getUserId() { return userId; }
    public void setUserId(Integer userId) { this.userId = userId; }

    public LocalDate getMealDate() { return mealDate; }
    public void setMealDate(LocalDate mealDate) { this.mealDate = mealDate; }

    public String getMealType() { return mealType; }
    public void setMealType(String mealType) { this.mealType = mealType; }

    public String getFoodName() { return foodName; }
    public void setFoodName(String foodName) { this.foodName = foodName; }

    public Integer getCalories() { return calories; }
    public void setCalories(Integer calories) { this.calories = calories; }

    public String getMemo() { return memo; }
    public void setMemo(String memo) { this.memo = memo; }
}
