package com.example.pproject.diary.dto.response;

import com.example.pproject.diary.domain.Diary;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;


@Getter
public class DiaryResponses {
    List<DiaryResponse> diaryResponseList;

    public DiaryResponses(List<Diary> list) {
        diaryResponseList = list.stream().map(diary -> new DiaryResponse(diary)).collect(Collectors.toList());
    }
}
