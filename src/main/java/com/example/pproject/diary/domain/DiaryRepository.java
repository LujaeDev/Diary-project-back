package com.example.pproject.diary.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DiaryRepository extends JpaRepository<Diary, Long> {

    @Query("SELECT d  FROM Diary d WHERE d.memberId = :memberId AND d.date=:date")
    List<Diary> findByMemberIdAndDate(@Param("memberId") Long memberId, @Param("date") LocalDate date);
}

