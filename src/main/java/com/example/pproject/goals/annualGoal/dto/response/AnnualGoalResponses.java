package com.example.pproject.goals.annualGoal.dto.response;

import lombok.Getter;

import java.util.List;

@Getter
public class AnnualGoalResponses {
    List<AnnualGoalResponse> annualGoalResponseList;

    public AnnualGoalResponses(List<AnnualGoalResponse> listTaskResponse) {
        annualGoalResponseList = listTaskResponse;
    }
}
