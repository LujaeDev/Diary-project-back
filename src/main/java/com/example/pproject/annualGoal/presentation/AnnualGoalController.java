package com.example.pproject.annualGoal.presentation;

import com.example.pproject.account.dto.AccountDto;
import com.example.pproject.annualGoal.application.AnnualGoalService;
import com.example.pproject.annualGoal.domain.AnnualGoal;
import com.example.pproject.annualGoal.dto.request.AnnualGoalRequest;
import com.example.pproject.annualGoal.dto.response.AnnualGoalResponse;
import com.example.pproject.annualGoal.dto.response.AnnualGoalResponses;
import com.example.pproject.task.domain.Task;
import com.example.pproject.task.dto.request.TaskCreateRequest;
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
@RequestMapping("/api/annualGoals")
@RequiredArgsConstructor
public class AnnualGoalController {
    private final AnnualGoalService annualGoalService;

    @GetMapping("")
    ResponseEntity<AnnualGoalResponses> annualGoalList(@AuthenticationPrincipal AccountDto accountDto,
                                                       @RequestParam Integer year,
                                                       @RequestParam String category) {
        Long memberId = accountDto.getMemberId();

        List<AnnualGoalResponse> listAnnualGoal = annualGoalService.findGoals(memberId, year, category);
        AnnualGoalResponses response = new AnnualGoalResponses(listAnnualGoal);
        return ResponseEntity.ok(response);
    }

    @PostMapping()
    ResponseEntity<AnnualGoalResponse> annualGoalAdd(@AuthenticationPrincipal AccountDto accountDto, @RequestBody AnnualGoalRequest annualGoalRequest) {
        AnnualGoal annualGoal = new AnnualGoal(accountDto.getMemberId(), annualGoalRequest);
        AnnualGoalResponse response = annualGoalService.save(annualGoal);

        //레퍼런스 코드
        //return ResponseEntity.created(URI.create("/api/categories/" + categoryResponse.getId())).body(categoryResponse);
        return ResponseEntity.created(URI.create("/api/tasks/" + response.getAnnualGoalId())).body(response);
    }


}
