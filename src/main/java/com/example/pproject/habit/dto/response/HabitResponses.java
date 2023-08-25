package com.example.pproject.habit.dto.response;

import com.example.pproject.habit.domain.Habit;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class HabitResponses {
    private List<Habit> positiveHabits;
    private List<Habit> negativeHabits;

    public HabitResponses(List<Habit> positiveHabits, List<Habit> negativeHabits) {
        this.positiveHabits = positiveHabits;
        this.negativeHabits = negativeHabits;
    }
}
