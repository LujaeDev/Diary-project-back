package com.example.pproject.goals.annualGoal.dto.request;

import lombok.Data;

@Data
public class AnnualGoalRequest {
    private String category;
    private String content;
    private Integer year;
}
