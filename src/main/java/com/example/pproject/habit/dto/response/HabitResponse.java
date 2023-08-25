package com.example.pproject.habit.dto.response;

import com.example.pproject.habit.domain.Habit;
import lombok.Data;

@Data
public class HabitResponse {
    private Long habitId;
    private String content;
    private String habitType;

    public HabitResponse(Habit habit) {
        this.habitId = habit.getHabitId();
        this.content = habit.getContent();
        this.habitType = habit.getHabitType();
    }
}
