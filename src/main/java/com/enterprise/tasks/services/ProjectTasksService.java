package com.enterprise.tasks.services;

import com.enterprise.tasks.dto.ProjectTasks;
import com.enterprise.tasks.repository.ProjectTasksRepository;
import com.enterprise.tasks.utils.ProjectTasksConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProjectTasksService {

    private static final Logger logger = LoggerFactory.getLogger(ProjectTasksService.class);

    @Autowired
    private ProjectTasksRepository projectTasksRepository;

    public ProjectTasks addOrUpdateTask(ProjectTasks projectTasks) {

        logger.info(ProjectTasksConstants.SERVICE + " addOrUpdateTask(" + ProjectTasks.class + ")");

        if (projectTasks.getStatus() == null || projectTasks.getStatus() == "") {
            projectTasks.setStatus(ProjectTasksConstants.TO_DO);
        }
        return projectTasksRepository.save(projectTasks);
    }

    public Iterable<ProjectTasks> getAllTasks() {
        logger.info(ProjectTasksConstants.SERVICE + " getAllTasks()");
        return projectTasksRepository.findAll();
    }

    public Optional<ProjectTasks> findById(Long id) {
        logger.info(ProjectTasksConstants.SERVICE + " findById()");
        return projectTasksRepository.findById(id);
    }

    public void deleteProjectTask(Long id) {
        logger.info(ProjectTasksConstants.SERVICE + " deleteProjectTask()");
        projectTasksRepository.deleteById(id);
    }
}
