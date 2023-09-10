package com.example.pproject.diary.dto.request;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DiaryUpdateRequest {
    private String title;
    private String content;
}
