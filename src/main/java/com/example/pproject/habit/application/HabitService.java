package com.example.pproject.habit.application;

import com.example.pproject.habit.domain.Habit;
import com.example.pproject.habit.domain.HabitRepository;
import com.example.pproject.habit.dto.request.HabitCreateRequest;
import com.example.pproject.habit.dto.response.HabitResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class HabitService {
    private final HabitRepository repository;

    public List<Habit> findByMemberId(Long memberId) {
        return repository.findByMemberId(memberId);
    }

    public List<Habit> getPositiveHabitsByMemberId(Long memberId) {
        List<Habit> habits = findByMemberId(memberId);
        List<Habit> positiveHabits = habits.stream().filter((h) -> h.getHabitType().equals("positive")).collect(toList());
        return positiveHabits;
    }

    public List<Habit> getNegativeHabitsByMemberId(Long memberId) {
        List<Habit> habits = findByMemberId(memberId);
        List<Habit> negativeHabits = habits.stream().filter((h) -> h.getHabitType().equals("negative")).collect(toList());
        return negativeHabits;
    }

    public String getHabitType(Long habitId) {
        Optional<Habit> habit = repository.findById(habitId);
        return habit.get().getHabitType();
    }

    public List<Habit> getHabitWithHabitType(Long memberId, String habitType) {
        if (habitType.equals("positive")) return getPositiveHabitsByMemberId(memberId);
        else return getNegativeHabitsByMemberId(memberId);
    }

    public HabitResponse save(final Long memberId, final HabitCreateRequest habitRequest) {
        Habit habit = new Habit(memberId, habitRequest);
        Habit savedHabit = repository.save(habit);
        return new HabitResponse(savedHabit);
    }

    public void delete(Long taskId) {
        repository.deleteById(taskId);
    }
}
