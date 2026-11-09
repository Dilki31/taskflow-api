package com.dilki.taskflow.service;

import com.dilki.taskflow.dto.TaskRequest;
import com.dilki.taskflow.entity.Task;
import com.dilki.taskflow.entity.User;
import com.dilki.taskflow.repository.TaskRepository;
import com.dilki.taskflow.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    // ලොගින් වෙලා ඉන්න යූසර්ව ගන්නවා
    private User getCurrentUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByEmail(email).orElseThrow();
    }

    public Task createTask(TaskRequest request) {
        Task task = Task.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .status(Task.TaskStatus.valueOf(request.getStatus().toUpperCase()))
                .createdAt(LocalDateTime.now())
                .user(getCurrentUser())
                .build();
        return taskRepository.save(task);
    }

    public List<Task> getMyTasks() {
        return taskRepository.findByUserId(getCurrentUser().getId());
    }

    public Task updateTaskStatus(Long taskId, String status) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        task.setStatus(Task.TaskStatus.valueOf(status.toUpperCase()));
        return taskRepository.save(task);
    }

    public void deleteTask(Long taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        taskRepository.delete(task);
    }
}