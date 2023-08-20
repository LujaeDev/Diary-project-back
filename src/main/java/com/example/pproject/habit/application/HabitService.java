package com.example.pproject.habit.application;

import com.example.pproject.habit.domain.Habit;
import com.example.pproject.habit.domain.HabitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class HabitService {
    private final HabitRepository repository;
    public List<Habit> findByMemberId(Long memberId){
        return repository.findByMemberId(memberId);
    }

    public List<String> getPositiveHabitsByMemberId(Long memberId){
        List<Habit> habits = findByMemberId(memberId);
        List<String> positiveHabits = habits.stream().filter((h) -> h.equals("positive")).map(h -> h.getContent()).collect(toList());
        return positiveHabits;
    }

    public List<String> getNegativeHabitsByMemberId(Long memberId){
        List<Habit> habits = findByMemberId(memberId);
        List<String> negativeHabits = habits.stream().filter((h) -> h.equals("negative")).map(h -> h.getContent()).collect(toList());
        return negativeHabits;
    }
}
