package com.example.pproject.goals.monthlyGoal.dto.response;

import com.example.pproject.goals.monthlyGoal.domain.MonthlyGoal;
import lombok.Data;

import java.time.LocalDate;

@Data
public class MonthlyGoalResponse {
    private Long monthlyGoalId;
    private String content;
    private Long memberId;
    private Long parentGoalId;
    //year and month
    private LocalDate date;
    private Boolean completed;

    public MonthlyGoalResponse(MonthlyGoal goal) {
        monthlyGoalId = goal.getMonthlyGoalId();
        content = goal.getContent();
        memberId = goal.getMemberId();
        parentGoalId = goal.getParentGoalId();
        date = goal.getDate();
        completed = goal.getCompleted();
    }
}
