package com.enterprise.tasks.service;

import com.enterprise.tasks.dto.Project;
import com.enterprise.tasks.repository.ProjectRepository;
import com.enterprise.tasks.utils.ProjectTasksConstants;
import com.enterprise.tasks.utils.ProjectsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public Project addOrUpdateProject(Project project) {

        if (project.getProjectStart() == null || project.getProjectStart().equals("")) {
            project.setProjectStart(ProjectsUtils.convertDateToLocalDateTime(new Date()));
        }

        if (project.getPriority() == null || project.getPriority().equals("")) {
            project.setPriority(ProjectTasksConstants.PRIORITY_MINOR);
        }

        return projectRepository.save(project);
    }

    public Iterable<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Optional<Project> findById(Long id) {
        return projectRepository.findById(id);
    }

    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }
}
