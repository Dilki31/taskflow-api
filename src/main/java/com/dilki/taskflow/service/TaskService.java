package com.dilki.taskflow.service;

import com.dilki.taskflow.dto.TaskRequest;
import com.dilki.taskflow.entity.Task;
import com.dilki.taskflow.entity.User;
import com.dilki.taskflow.repository.TaskRepository;
import com.dilki.taskflow.repository.UserRepository;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    // Returns the currently logged-in user
    private User getCurrentUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByEmail(email).orElseThrow();
    }

    private Task.TaskStatus parseStatus(String status) {
        if (status == null || status.isBlank()) {
            return Task.TaskStatus.TODO;
        }
        try {
            return Task.TaskStatus.valueOf(status.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid status. Use TODO, IN_PROGRESS or DONE");
        }
    }

    // Finds a task only if it belongs to the current user (prevents access to other users' tasks)
    private Task findOwnTask(Long taskId) {
        return taskRepository.findByIdAndUserId(taskId, getCurrentUser().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found"));
    }

    public Task createTask(TaskRequest request) {
        Task task = Task.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .status(parseStatus(request.getStatus()))
                .createdAt(LocalDateTime.now())
                .user(getCurrentUser())
                .build();
        return taskRepository.save(task);
    }

    public List<Task> getMyTasks() {
        return taskRepository.findByUserId(getCurrentUser().getId());
    }

    public Task updateTaskStatus(Long taskId, String status) {
        Task task = findOwnTask(taskId);
        task.setStatus(parseStatus(status));
        return taskRepository.save(task);
    }

    public void deleteTask(Long taskId) {
        taskRepository.delete(findOwnTask(taskId));
    }
}
