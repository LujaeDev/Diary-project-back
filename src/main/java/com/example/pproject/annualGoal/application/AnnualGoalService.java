package com.example.pproject.annualGoal.application;

import com.example.pproject.annualGoal.domain.AnnualGoal;
import com.example.pproject.annualGoal.domain.AnnualGoalRepository;
import com.example.pproject.annualGoal.dto.response.AnnualGoalResponse;
import com.example.pproject.task.domain.Task;
import com.example.pproject.task.domain.TaskRepository;
import com.example.pproject.task.dto.response.TaskResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AnnualGoalService {
    private final AnnualGoalRepository repository;

    public List<AnnualGoalResponse> findGoals(Long id, int year, String category) {
        List<AnnualGoal> ret = repository.findAllByMemberIdAndYearAndCategory(id, year, category);

        return ret.stream().map((a) -> new AnnualGoalResponse(a)).collect(Collectors.toList());
    }

    public AnnualGoalResponse save(AnnualGoal annualGoal) {
        AnnualGoal save = repository.save(annualGoal);
        AnnualGoalResponse response = new AnnualGoalResponse(save);
        return response;
    }

    public void delete(Long annualGoalId) {
        repository.deleteById(annualGoalId);
    }
}
