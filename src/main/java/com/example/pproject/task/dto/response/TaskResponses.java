package com.example.pproject.task.dto.response;

import lombok.Getter;

import java.util.List;

@Getter
public class TaskResponses {
    List<TaskResponse> taskList;

    public TaskResponses(List<TaskResponse> listTaskResponse){
        taskList = listTaskResponse;
    }
}
