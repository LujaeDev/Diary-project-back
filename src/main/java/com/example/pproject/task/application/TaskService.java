package com.example.pproject.task.application;

import com.example.pproject.task.domain.Task;
import com.example.pproject.task.domain.TaskRepository;
import com.example.pproject.task.dto.response.TaskResponse;
import com.example.pproject.task.dto.response.TaskResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository repository;

    public List<TaskResponse> findTasksWithMemberIdAndDate(Long id, LocalDate date) {
        List<Task> ret = repository.findAllByMemberId(id, date);

        return ret.stream().map((t) -> new TaskResponse(t)).collect(Collectors.toList());
    }

    public TaskResponse save(Task task) {
        Task save = repository.save(task);
        TaskResponse response = new TaskResponse(save);
        return response;
    }

    public void delete(Long taskId) {
        repository.deleteById(taskId);
    }
}
