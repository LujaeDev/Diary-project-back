package com.example.pproject.goals.monthlyGoal.domain;

import com.example.pproject.global.entity.BaseEntity;
import com.example.pproject.goals.annualGoal.dto.request.AnnualGoalRequest;
import com.example.pproject.goals.monthlyGoal.dto.request.MonthlyGoalRequest;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Entity
public class MonthlyGoal extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long monthlyGoalId;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private Long memberId;

    @Column
    private Long parentGoalId;

    //year and month
    @Column(nullable = false)
    private LocalDate date;

    public MonthlyGoal() {
    }

    public MonthlyGoal(Long memberId, MonthlyGoalRequest request) {
        content = request.getContent();
        parentGoalId = request.getParentGoalId();
        date = request.getDate();
        this.memberId = memberId;
    }
}
