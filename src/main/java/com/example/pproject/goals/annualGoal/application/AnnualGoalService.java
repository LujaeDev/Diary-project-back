package com.example.pproject.goals.annualGoal.application;

import com.example.pproject.goals.annualGoal.domain.AnnualGoal;
import com.example.pproject.goals.annualGoal.domain.AnnualGoalRepository;
import com.example.pproject.goals.annualGoal.dto.response.AnnualGoalResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AnnualGoalService {
    private final AnnualGoalRepository repository;

    public List<AnnualGoalResponse> findGoals(Long memberId, int year, String category) {
        List<AnnualGoal> ret = repository.findAllByMemberIdAndYearAndCategory(memberId, year, category);

        return ret.stream().map((a) -> new AnnualGoalResponse(a)).collect(Collectors.toList());
    }

    public List<AnnualGoalResponse> findGoals(Long memberId, int year) {
        List<AnnualGoal> ret = repository.findAllByMemberIdAndYear(memberId, year);
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
