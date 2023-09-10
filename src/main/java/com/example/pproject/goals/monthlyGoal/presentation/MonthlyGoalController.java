package com.example.pproject.goals.monthlyGoal.presentation;

import com.example.pproject.account.dto.AccountDto;
import com.example.pproject.goals.monthlyGoal.application.MonthlyGoalService;
import com.example.pproject.goals.monthlyGoal.domain.MonthlyGoal;
import com.example.pproject.goals.monthlyGoal.dto.request.MonthlyGoalRequest;
import com.example.pproject.goals.monthlyGoal.dto.request.MonthlyGoalUpdateRequest;
import com.example.pproject.goals.monthlyGoal.dto.response.MonthlyGoalResponse;
import com.example.pproject.goals.monthlyGoal.dto.response.MonthlyGoalResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/monthlyGoals")
@Slf4j
@RequiredArgsConstructor
public class MonthlyGoalController {
    private final MonthlyGoalService service;

    @PostMapping("")
    ResponseEntity<MonthlyGoalResponse> monthlyGoalAdd(@AuthenticationPrincipal AccountDto accountDto,
                                                       @RequestBody MonthlyGoalRequest request) {
        Long memberId = accountDto.getMemberId();
        MonthlyGoal monthlyGoal = new MonthlyGoal(memberId, request);
        MonthlyGoalResponse response = service.save(monthlyGoal);
        return ResponseEntity.created(URI.create("/api/monthlyGoals/" + response.getMonthlyGoalId())).body(response);
    }

    @GetMapping("/rootGoal/{rootGoalId}/date/{date}")
    ResponseEntity<MonthlyGoalResponses> monthlyGoalList(@AuthenticationPrincipal AccountDto accountDto,
                                                         @PathVariable(required = false) Long rootGoalId,
                                                         @PathVariable LocalDate date) {
        if (rootGoalId == null)
            rootGoalId = 0L;

        Long memberId = accountDto.getMemberId();
        List<MonthlyGoalResponse> list = service.findGoals(memberId, rootGoalId, date);
        MonthlyGoalResponses response = new MonthlyGoalResponses(list);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{monthlyGoalId}")
    ResponseEntity<Void> deleteMonthlyGoal(@AuthenticationPrincipal AccountDto accountDto, @PathVariable Long monthlyGoalId) {
        service.delete(monthlyGoalId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{monthlyGoalId}/completed")
    ResponseEntity<MonthlyGoalResponse> updateCompleted(@AuthenticationPrincipal AccountDto accountDto,
                                                        @PathVariable Long monthlyGoalId,
                                                        @RequestBody MonthlyGoalUpdateRequest request) {
        MonthlyGoalResponse response = service.update(monthlyGoalId, request);
        return ResponseEntity.ok(response);
    }

}
