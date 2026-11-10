package com.dilki.taskflow.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TaskRequest {

    @NotBlank(message = "is required")
    private String title;

    private String description;

    private String status; // TODO, IN_PROGRESS, DONE (defaults to TODO)
}
