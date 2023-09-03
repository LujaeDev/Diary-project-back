package com.example.pproject.goals.monthlyGoal.dto.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class MonthlyGoalRequest {
    private String content;
    private Long parentGoalId;
    //year and month
    private LocalDate date;
}

