package com.enterprise.tasks.services;

import com.enterprise.tasks.dto.Project;
import com.enterprise.tasks.repository.ProjectRepository;
import com.enterprise.tasks.utils.ProjectTasksConstants;
import com.enterprise.tasks.utils.ProjectsUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class ProjectService {

    private static final Logger logger = LoggerFactory.getLogger(ProjectService.class);

    @Autowired
    private ProjectRepository projectRepository;

    public Project addOrUpdateProject(Project project) {

        logger.info(ProjectTasksConstants.SERVICE + " addOrUpdateProject(" + Project.class + ")");

        if (project.getProjectStart() == null || project.getProjectStart().equals("")) {
            project.setProjectStart(ProjectsUtils.convertDateToLocalDateTime(new Date()));
        }

        if (project.getPriority() == null || project.getPriority().equals("")) {
            project.setPriority(ProjectTasksConstants.PRIORITY_MINOR);
        }

        if (project.getProjectLogo() == null || project.getProjectLogo().equals("")) {
            project.setProjectLogo(ProjectTasksConstants.PROJECT_DEFAULT_IMAGE_LOGO);
        }

        return projectRepository.save(project);
    }

    public Iterable<Project> getAllProjects() {
        logger.info(ProjectTasksConstants.SERVICE + " getAllProjects()");
        return projectRepository.findAll();
    }

    public Optional<Project> findById(Long id) {
        logger.info(ProjectTasksConstants.SERVICE + " findById()");
        return projectRepository.findById(id);
    }

    public void deleteProject(Long id) {
        logger.info(ProjectTasksConstants.SERVICE + " deleteProject()");
        projectRepository.deleteById(id);
    }
}
