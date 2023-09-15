package com.example.pproject.diary.dto.response;

import com.example.pproject.diary.domain.Diary;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Date;

@Getter
public class DiaryResponse {
    private Long diaryId;
    private String title;
    private String content;
    private Long memberId;
    private LocalDate date;

    public DiaryResponse(Diary diary) {
        this.diaryId = diary.getDiaryId();
        this.title = diary.getTitle();
        this.content = diary.getContent();
        this.memberId = diary.getMemberId();
        this.date = diary.getDate();
    }
}
