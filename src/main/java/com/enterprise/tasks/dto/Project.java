package com.enterprise.tasks.dto;

import com.enterprise.tasks.utils.ProjectTasksConstants;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import io.swagger.annotations.ApiModel;

import javax.persistence.*;
import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;

@Entity
@ApiModel(description = "Details about the Project")
public class Project implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @NotBlank(message = ProjectTasksConstants.PROJECT_NAME_REQUIRED)
    private String projectName;

    @NotBlank(message = ProjectTasksConstants.PROJECT_DESCRIPTION_REQUIRED)
    private String projectDescription;

    @NotBlank(message = ProjectTasksConstants.PROJECT_CREATED_BY_REQUIRED)
    private String projectCreatedBy;

    private String projectLogo;

    private String priority;

    @JsonFormat(pattern = ProjectTasksConstants.PROJECT_PATTERN_VALIDATION)
    private LocalDateTime projectStart;

    @JsonFormat(pattern = ProjectTasksConstants.PROJECT_PATTERN_VALIDATION)
    private LocalDateTime projectEnd;

    @OneToMany(mappedBy = "project")
    @JsonManagedReference
    private Collection<ProjectTasks> projectTasksList;

    @AssertFalse(message = ProjectTasksConstants.PROJECT_START_BEFORE_END_DATE)
    @JsonProperty(access = Access.WRITE_ONLY)
    public boolean isValid() {
        return this.getProjectStart().isAfter(this.getProjectEnd());
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public LocalDateTime getProjectStart() {
        return projectStart;
    }

    public void setProjectStart(LocalDateTime projectStart) {
        this.projectStart = projectStart;
    }

    public LocalDateTime getProjectEnd() {
        return projectEnd;
    }

    public void setProjectEnd(LocalDateTime projectEnd) {
        this.projectEnd = projectEnd;
    }

    public Collection<ProjectTasks> getProjectTasksList() {
        return projectTasksList;
    }

    public void setProjectTasksList(Collection<ProjectTasks> projectTasksList) {
        this.projectTasksList = projectTasksList;
    }

    public String getProjectCreatedBy() {
        return projectCreatedBy;
    }

    public void setProjectCreatedBy(String projectCreatedBy) {
        this.projectCreatedBy = projectCreatedBy;
    }

    public String getProjectLogo() {
        return projectLogo;
    }

    public void setProjectLogo(String projectLogo) {
        this.projectLogo = projectLogo;
    }
}
