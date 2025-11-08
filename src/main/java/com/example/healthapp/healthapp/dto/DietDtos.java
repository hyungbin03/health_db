package com.example.healthapp.healthapp.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class DietDtos {

    // 공통 필드 (요청 바디용)
    public static class Base {
        @NotNull
        public Integer userId;

        @NotBlank
        public String mealDate;   // "yyyy-MM-dd" 문자열로 받음

        @NotBlank
        public String mealType;

        @NotBlank
        public String foodName;

        @Min(0)
        public Integer calories;

        public String memo;
    }

    // 단건 생성
    public static class Create extends Base { }

    // 단건 수정
    public static class Update extends Base { }

    // 여러 건 생성
    public static class BulkCreate {
        @NotNull
        public List<Create> items;
    }
}
