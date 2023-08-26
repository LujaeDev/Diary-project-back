package com.example.pproject.task.domain;


import com.example.pproject.global.entity.BaseEntity;
import com.example.pproject.task.dto.request.TaskCreateRequest;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Entity
public class Task extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long taskId;
    @Column(nullable  = false)
    private String content;
    @Column
    private Boolean success = false;
    @Column
    private LocalDateTime startTime;
    @Column
    private LocalDateTime endTime;
    @Column(nullable = false)
    private LocalDate taskDate;

    @Column(nullable = false)
    private Long memberId;

    public Task(){}
    public Task( Long memberId, TaskCreateRequest taskCreateRequest){
        this.taskId = taskCreateRequest.getTaskId();
        this.content = taskCreateRequest.getContent();
        this.success = taskCreateRequest.getSuccess();
        this.startTime = getStartTime();
        this.endTime = getEndTime();
        this.taskDate = taskCreateRequest.getDate();
        this.memberId = memberId;
    }
}
