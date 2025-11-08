package com.example.healthapp.healthapp.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;

public class DietRequest {
    private Integer userId;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate mealDate;
    private String mealType;
    private String foodName;
    private Integer calories;
    private String memo;

    // getter/setter
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
