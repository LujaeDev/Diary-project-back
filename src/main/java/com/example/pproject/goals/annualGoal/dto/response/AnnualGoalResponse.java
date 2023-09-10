package com.example.pproject.goals.annualGoal.dto.response;

import com.example.pproject.goals.annualGoal.domain.AnnualGoal;
import lombok.Data;

@Data
public class AnnualGoalResponse {
    private Long annualGoalId;
    private String category;
    private String content;
    private Long memberId;
    private Integer year;

    public AnnualGoalResponse(AnnualGoal annualGoal) {
        annualGoalId = annualGoal.getAnnualGoalId();
        category = annualGoal.getCategory();
        content = annualGoal.getContent();
        memberId = annualGoal.getMemberId();
        year = annualGoal.getYear();
    }
}
