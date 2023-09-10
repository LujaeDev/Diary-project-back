package com.example.pproject.goals.annualGoal.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AnnualGoalRepository extends JpaRepository<AnnualGoal, Long> {
    @Query("SELECT a  FROM AnnualGoal a WHERE a.memberId = :memberId AND a.year=:year AND a.category=:category")
    List<AnnualGoal> findAllByMemberIdAndYearAndCategory(
            @Param("memberId") Long memberId,
            @Param("year") int year,
            @Param("category") String category);

    @Query("SELECT a FROM AnnualGoal a Where a.memberId = :memberId AND a.year=:year")
    List<AnnualGoal> findAllByMemberIdAndYear(
            @Param("memberId") Long memberId,
            @Param("year") int year
    );
}
