package com.enterprise.tasks.dto;

import com.enterprise.tasks.utils.ProjectTasksConstants;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Entity
public class ProjectTasks implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @NotBlank(message = ProjectTasksConstants.NAME_REQUIRED)
    private String nameTask;

    @NotBlank(message = ProjectTasksConstants.DESCRIPTION_REQUIRED)
    private String descriptionTask;

    private String status;

    @NotBlank(message = ProjectTasksConstants.PRIORITY_REQUIRED)
    private String priority;

    public ProjectTasks() {
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getNameTask() {
        return nameTask;
    }

    public void setNameTask(String nameTask) {
        this.nameTask = nameTask;
    }

    public String getDescriptionTask() {
        return descriptionTask;
    }

    public void setDescriptionTask(String descriptionTask) {
        this.descriptionTask = descriptionTask;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }
}
