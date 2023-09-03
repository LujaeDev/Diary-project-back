package com.example.pproject.goals.monthlyGoal.dto.response;


import lombok.Getter;

import java.util.List;

@Getter
public class MonthlyGoalResponses {
    List<MonthlyGoalResponse> monthlyGoalResponseList;

    public MonthlyGoalResponses(List<MonthlyGoalResponse> list) {
        this.monthlyGoalResponseList = list;
    }
}