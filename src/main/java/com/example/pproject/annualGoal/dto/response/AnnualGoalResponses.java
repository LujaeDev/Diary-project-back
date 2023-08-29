package com.example.pproject.annualGoal.dto.response;

import com.example.pproject.task.dto.response.TaskResponse;
import lombok.Getter;

import java.util.List;

@Getter
public class AnnualGoalResponses {
    List<AnnualGoalResponse> annualGoalResponseList;

    public AnnualGoalResponses(List<AnnualGoalResponse> listTaskResponse) {
        annualGoalResponseList = listTaskResponse;
    }
}
