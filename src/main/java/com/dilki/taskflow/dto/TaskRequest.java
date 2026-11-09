package com.dilki.taskflow.dto;
import lombok.Data;

@Data
public class TaskRequest {
    private String title;
    private String description;
    private String status; // TODO, IN_PROGRESS, DONE
}