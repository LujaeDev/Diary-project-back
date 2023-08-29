package com.example.pproject.annualGoal.domain;

import com.example.pproject.annualGoal.dto.request.AnnualGoalRequest;
import com.example.pproject.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class AnnualGoal extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long annualGoalId;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private Long memberId;

    @Column(nullable = false)
    private Integer year;

    public AnnualGoal() {
    }

    public AnnualGoal(Long memberId, AnnualGoalRequest request) {
        category = request.getCategory();
        content = request.getContent();
        year = request.getYear();
        this.memberId = memberId;
    }
}
