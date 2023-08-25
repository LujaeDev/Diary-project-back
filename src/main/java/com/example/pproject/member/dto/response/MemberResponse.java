package com.example.pproject.member.dto.response;

import com.example.pproject.PprojectApplication;
import com.example.pproject.habit.application.HabitService;
import com.example.pproject.habit.domain.Habit;
import com.example.pproject.member.domain.Member;
import lombok.Builder;
import lombok.Data;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Builder
@Data
public class MemberResponse {
    private Long memberId;
    private String displayName;
    private String profileContent;
    private List<Habit> positiveHabits;
    private List<Habit> negativeHabits;


}
