package com.example.pproject.habit.domain;

import com.example.pproject.global.entity.BaseEntity;
import com.example.pproject.habit.dto.request.HabitCreateRequest;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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

    public Habit() {

    }

    public Habit(Long memberId, HabitCreateRequest request) {
        this.content = request.getContent();
        this.habitType = request.getHabitType();
        this.memberId = memberId;

    }
}
