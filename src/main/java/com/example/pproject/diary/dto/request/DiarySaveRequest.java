package com.example.pproject.diary.dto.request;

import lombok.Getter;

import java.time.LocalDate;
import java.util.Date;

@Getter
public class DiarySaveRequest {
    private String title;
    private String content;
    private LocalDate date;
}
