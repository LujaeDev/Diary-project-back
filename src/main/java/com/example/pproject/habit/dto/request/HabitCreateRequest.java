package com.example.pproject.habit.dto.request;

import lombok.Data;

@Data
public class HabitCreateRequest {
    private String content;
    private String habitType;
}
