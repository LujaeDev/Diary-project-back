package com.example.pproject.habit.presentation;

import com.example.pproject.account.dto.AccountDto;
import com.example.pproject.habit.application.HabitService;
import com.example.pproject.habit.domain.Habit;
import com.example.pproject.habit.dto.request.HabitCreateRequest;
import com.example.pproject.habit.dto.response.HabitResponse;
import com.example.pproject.habit.dto.response.HabitResponses;
import com.example.pproject.task.dto.response.TaskResponse;
import com.example.pproject.task.dto.response.TaskResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/habits")
@RequiredArgsConstructor
public class HabitController {
    private final HabitService habitService;

    @GetMapping("")
    ResponseEntity<HabitResponses> habitList(@AuthenticationPrincipal AccountDto accountDto) {
        Long memberId = accountDto.getMemberId();
        HabitResponses response = makeHabitResponses(memberId);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<HabitResponse> habitSave(@AuthenticationPrincipal AccountDto accountDto, @RequestBody HabitCreateRequest request) {
        Long memberId = accountDto.getMemberId();
        HabitResponse response = habitService.save(memberId, request);
        return ResponseEntity.created(URI.create("/api/habits/" + response.getHabitId())).body(response);
    }

    @DeleteMapping("/{habitId}")
    public ResponseEntity<Void> habitDelete(@AuthenticationPrincipal AccountDto accountDto, @PathVariable Long habitId) {
        String habitType = habitService.getHabitType(habitId);
        habitService.delete(habitId);

        List<Habit> response = habitService.getHabitWithHabitType(accountDto.getMemberId(), habitType);
        return ResponseEntity.noContent().build();
    }

    private HabitResponses makeHabitResponses(Long memberId) {
        List<Habit> positiveHabits = habitService.getPositiveHabitsByMemberId(memberId);
        List<Habit> negativeHabits = habitService.getNegativeHabitsByMemberId(memberId);
        return new HabitResponses(positiveHabits, negativeHabits);
    }
}
