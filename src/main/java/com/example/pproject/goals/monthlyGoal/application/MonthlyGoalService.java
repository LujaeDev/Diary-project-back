package com.example.pproject.goals.monthlyGoal.application;

import com.example.pproject.goals.monthlyGoal.domain.MonthlyGoal;
import com.example.pproject.goals.monthlyGoal.domain.MonthlyGoalRepository;
import com.example.pproject.goals.monthlyGoal.dto.request.MonthlyGoalRequest;
import com.example.pproject.goals.monthlyGoal.dto.response.MonthlyGoalResponse;
import com.example.pproject.goals.monthlyGoal.dto.response.MonthlyGoalResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MonthlyGoalService {
    private final MonthlyGoalRepository repository;
    private final int[] endDay = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public MonthlyGoalResponse save(MonthlyGoal monthlyGoal) {

        MonthlyGoal saved = repository.save(monthlyGoal);
        return new MonthlyGoalResponse(saved);
    }

    public List<MonthlyGoalResponse> findGoals(Long memberId, Long rootMemberId, LocalDate date) {
        int year = date.getYear();
        int month = date.getMonthValue();
        List<MonthlyGoal> ret = repository.findAllByMemberIdAndRootGoalIdAndDate(memberId, rootMemberId, year, month, endDay[month]);
        return ret.stream().map((m) -> {
            return new MonthlyGoalResponse(m);
        }).collect(Collectors.toList());
    }

    public void delete(Long monthlyGoalId) {
        repository.deleteById(monthlyGoalId);
    }

}
