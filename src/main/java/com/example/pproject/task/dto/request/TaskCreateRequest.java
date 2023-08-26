package com.example.pproject.task.dto.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@ToString
@Setter
@Getter
public class TaskCreateRequest {
    private Long taskId;
    private String content;
    private Boolean success;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private LocalDate date;

    public void setDate(String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

       this.date = LocalDate.parse(date, formatter);
    }
}
