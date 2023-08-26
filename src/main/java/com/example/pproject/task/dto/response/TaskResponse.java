package com.example.pproject.task.dto.response;

import com.example.pproject.task.domain.Task;
import com.fasterxml.jackson.databind.ser.std.StdArraySerializers;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class TaskResponse {
    private Long taskId;
    private String content;
    private Boolean success;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private LocalDate date;

    public TaskResponse(Task task){
        setTaskId(task.getTaskId());
        setContent(task.getContent());
        setSuccess(task.getSuccess());
        setStartTime(task.getStartTime());
        setEndTime(task.getEndTime());
        setDate(task.getTaskDate());
    }
}
