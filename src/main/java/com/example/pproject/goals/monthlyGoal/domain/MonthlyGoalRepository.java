package com.example.pproject.goals.monthlyGoal.domain;

import com.example.pproject.goals.annualGoal.domain.AnnualGoal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MonthlyGoalRepository extends JpaRepository<MonthlyGoal, Long> {
    @Query("SELECT m  FROM MonthlyGoal m WHERE m.memberId =:memberId AND m.parentGoalId=:rootGoalId " +
            "AND m.date BETWEEN DATE(CONCAT(:year, '-', :month, '-01')) AND DATE(CONCAT(:year, '-', :month, '-', :endDay))")
    List<MonthlyGoal> findAllByMemberIdAndRootGoalIdAndDate(
            @Param("memberId") Long memberId,
            @Param("rootGoalId") Long rootGoalId,
            @Param("year") int year,
            @Param("month") int month,
            @Param("endDay") int endDay);
}
