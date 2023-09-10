package com.example.pproject.diary.presentation;

import com.example.pproject.account.dto.AccountDto;
import com.example.pproject.diary.application.DiaryService;
import com.example.pproject.diary.domain.Diary;
import com.example.pproject.diary.dto.request.DiarySaveRequest;
import com.example.pproject.diary.dto.request.DiaryUpdateRequest;
import com.example.pproject.diary.dto.response.DiaryResponse;
import com.example.pproject.diary.dto.response.DiaryResponses;
import com.example.pproject.global.response.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aot.generate.AccessControl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDate;

@RestController
@RequestMapping("api/diaries")
@RequiredArgsConstructor
@Slf4j
public class DiaryController {
    private final DiaryService service;

    @PostMapping()
    ResponseEntity<DiaryResponse> save(@AuthenticationPrincipal AccountDto accountDto, @RequestBody DiarySaveRequest requestForm) {
        DiaryResponse response = service.save(new Diary(requestForm, accountDto.getMemberId()));
        return ResponseEntity.created(URI.create("/api/diaries/" + response.getDiaryId())).body(response);
    }

    @GetMapping("/date/{date}")
    ResponseEntity<DiaryResponses> diaryList(@AuthenticationPrincipal AccountDto accountDto,
                                             @PathVariable LocalDate date) {
        DiaryResponses diaryResponses = service.find(accountDto.getMemberId(), date);
        return ResponseEntity.ok(diaryResponses);
    }

    @GetMapping("{diaryId}")
    ResponseEntity<DiaryResponse> diaryList(@AuthenticationPrincipal AccountDto accountDto,
                                            @PathVariable Long diaryId) {
        DiaryResponse diaryResponse = service.findById(diaryId);
        return ResponseEntity.ok(diaryResponse);
    }

    @DeleteMapping("/{diaryId}")
    ResponseEntity<Void> deleteDiary(@AuthenticationPrincipal AccountDto accountDto, @PathVariable Long diaryId) {
        service.delete(diaryId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{diaryId}")
    ResponseEntity<Void> updateDiary(@AuthenticationPrincipal AccountDto accountDto,
                                     @PathVariable Long diaryId,
                                     @RequestBody DiaryUpdateRequest updateRequest) {

        log.info("here: ");
        service.update(diaryId, updateRequest);
        return ResponseEntity.noContent().build();
    }
}

