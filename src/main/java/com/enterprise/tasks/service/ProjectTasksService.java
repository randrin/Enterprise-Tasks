package com.enterprise.tasks.service;

import com.enterprise.tasks.dto.ProjectTasks;
import com.enterprise.tasks.repository.ProjectTasksRepository;
import com.enterprise.tasks.utils.ProjectTasksConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProjectTasksService {

    @Autowired
    private ProjectTasksRepository projectTasksRepository;

    public ProjectTasks addOrUpdateTask(ProjectTasks projectTasks) {

        if (projectTasks.getStatus() == null || projectTasks.getStatus() == "") {
            projectTasks.setStatus(ProjectTasksConstants.TO_DO);
        }
        return projectTasksRepository.save(projectTasks);
    }

    public Iterable<ProjectTasks> getAllTasks() {
        return projectTasksRepository.findAll();
    }

    public Optional<ProjectTasks> findById(Long id) {
        return projectTasksRepository.findById(id);
    }

    public void deleteProjectTask(Long id) {
        // Optional<ProjectTasks> projectTask = findById(id);
        projectTasksRepository.deleteById(id);
    }
}
