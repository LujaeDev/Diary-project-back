package com.example.pproject.diary.application;

import com.example.pproject.diary.domain.Diary;
import com.example.pproject.diary.domain.DiaryRepository;
import com.example.pproject.diary.dto.request.DiaryUpdateRequest;
import com.example.pproject.diary.dto.response.DiaryResponse;
import com.example.pproject.diary.dto.response.DiaryResponses;
import com.example.pproject.diary.exception.NoSuchDiaryException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class DiaryService {
    private final DiaryRepository repository;

    public DiaryResponse save(Diary diary) {
        Diary saved = repository.save(diary);
        return new DiaryResponse(saved);
    }

    public DiaryResponses find(Long memberId, LocalDate date) {
        List<Diary> list = repository.findByMemberIdAndDate(memberId, date);
        return new DiaryResponses(list);
    }

    public DiaryResponse findById(Long diaryId) {
        Diary diary = repository.findById(diaryId).orElseThrow(NoSuchDiaryException::new);
        return new DiaryResponse(diary);
    }

    public void delete(Long diaryId) {
        repository.deleteById(diaryId);
    }

    public void update(Long diaryId, DiaryUpdateRequest updateRequest) {
        Diary diary = repository.findById(diaryId).orElseThrow(NoSuchDiaryException::new);
        diary.setTitle(updateRequest.getTitle());
        diary.setContent(updateRequest.getContent());
        repository.save(diary);
    }
}
