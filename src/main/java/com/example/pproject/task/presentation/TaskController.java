package com.example.pproject.task.presentation;

import com.example.pproject.account.dto.AccountDto;
import com.example.pproject.task.application.TaskService;
import com.example.pproject.task.domain.Task;
import com.example.pproject.task.dto.request.TaskCreateRequest;
import com.example.pproject.task.dto.response.TaskResponse;
import com.example.pproject.task.dto.response.TaskResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;
    @GetMapping("")
    ResponseEntity<TaskResponses> taskList(@AuthenticationPrincipal AccountDto accountDto, @RequestParam String date){
        Long memberId = accountDto.getMemberId();


        log.info(date.toString());
        LocalDate localDate = LocalDate.parse(date);
        List<TaskResponse> listTaskResponse = taskService.findTasksWithMemberIdAndDate(memberId, localDate);
        TaskResponses response = new TaskResponses(listTaskResponse);
        return ResponseEntity.ok(response);
    }

    @PostMapping()
    ResponseEntity<TaskResponse>  taskAdd(@AuthenticationPrincipal AccountDto accountDto, @RequestBody TaskCreateRequest taskCreateRequest){
        Task task = new Task(accountDto.getMemberId(), taskCreateRequest);
        TaskResponse response = taskService.addTask(task);

        log.info("TargetRequest: {}", taskCreateRequest);
        //레퍼런스 코드
        //return ResponseEntity.created(URI.create("/api/categories/" + categoryResponse.getId())).body(categoryResponse);
        return ResponseEntity.created(URI.create("/api/tasks/" + response.getTaskId())).body(response);
    }

}
