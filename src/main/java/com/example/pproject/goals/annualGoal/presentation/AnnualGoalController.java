package com.example.pproject.goals.annualGoal.presentation;

import com.example.pproject.account.dto.AccountDto;
import com.example.pproject.goals.annualGoal.application.AnnualGoalService;
import com.example.pproject.goals.annualGoal.domain.AnnualGoal;
import com.example.pproject.goals.annualGoal.dto.request.AnnualGoalRequest;
import com.example.pproject.goals.annualGoal.dto.response.AnnualGoalResponse;
import com.example.pproject.goals.annualGoal.dto.response.AnnualGoalResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/annualGoals")
@RequiredArgsConstructor
public class AnnualGoalController {
    private final AnnualGoalService annualGoalService;

    @GetMapping("/{year}/{category}")
    ResponseEntity<AnnualGoalResponses> annualGoalList(@AuthenticationPrincipal AccountDto accountDto,
                                                       @PathVariable Integer year,
                                                       @PathVariable String category) {
        Long memberId = accountDto.getMemberId();

        List<AnnualGoalResponse> listAnnualGoal = annualGoalService.findGoals(memberId, year, category);
        AnnualGoalResponses response = new AnnualGoalResponses(listAnnualGoal);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{year}")
    ResponseEntity<AnnualGoalResponses> annualGoalList(@AuthenticationPrincipal AccountDto accountDto,
                                                       @PathVariable Integer year) {
        Long memberId = accountDto.getMemberId();

        List<AnnualGoalResponse> listAnnualGoal = annualGoalService.findGoals(memberId, year);
        AnnualGoalResponses response = new AnnualGoalResponses(listAnnualGoal);
        return ResponseEntity.ok(response);
    }

    @PostMapping()
    ResponseEntity<AnnualGoalResponse> annualGoalAdd(@AuthenticationPrincipal AccountDto accountDto, @RequestBody AnnualGoalRequest annualGoalRequest) {
        AnnualGoal annualGoal = new AnnualGoal(accountDto.getMemberId(), annualGoalRequest);
        AnnualGoalResponse response = annualGoalService.save(annualGoal);

        //레퍼런스 코드
        //return ResponseEntity.created(URI.create("/api/categories/" + categoryResponse.getId())).body(categoryResponse);
        return ResponseEntity.created(URI.create("/api/annualGoals/" + response.getAnnualGoalId())).body(response);
    }

    @DeleteMapping("/{annualGoalId}")
    public ResponseEntity<Void> annualGoalDelete(@AuthenticationPrincipal AccountDto accountDto, @PathVariable Long annualGoalId) {
        annualGoalService.delete(annualGoalId);
        return ResponseEntity.noContent().build();
    }


}
