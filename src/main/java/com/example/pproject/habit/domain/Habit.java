package com.example.pproject.habit.domain;

import com.example.pproject.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

@Getter
@Entity
public class Habit extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long habitId;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String habitType;

    @Column(nullable = false)
    private Long memberId;
}
