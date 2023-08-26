package com.example.pproject.task.domain;

import com.example.pproject.habit.domain.Habit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query("SELECT t  FROM Task t WHERE t.memberId = :memberId AND t.taskDate=:date")
    List<Task> findAllByMemberId(@Param("memberId") Long memberId, @Param("date") LocalDate date);


}
