package com.enterprise.tasks.web;

import com.enterprise.tasks.dto.Project;
import com.enterprise.tasks.services.ProjectService;
import com.enterprise.tasks.utils.ProjectTasksConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/projects")
@CrossOrigin("*")
public class ProjectController {

    private static final Logger logger = LoggerFactory.getLogger(ProjectController.class);

    @Autowired
    private ProjectService projectService;

    @PostMapping(value = "/saveProject", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> saveProject(@Valid @RequestBody Project project, BindingResult result) {

        logger.info(ProjectTasksConstants.BEGIN + " POST -> /api/projects/saveProject - Obejct [" + Project.class + "]");

        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<String, String>();
            for (FieldError error : result.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
                return new ResponseEntity<Object>(errors, HttpStatus.BAD_REQUEST);
            }
        }
        Project pj = projectService.addOrUpdateProject(project);

        logger.info(ProjectTasksConstants.END + " POST -> /api/projects/saveProject - Obejct [" + Project.class + "]");
        return new ResponseEntity<Object>(pj, HttpStatus.CREATED);
    }

    @GetMapping("/getProjects")
    public Iterable<Project> getProjects() {
        logger.info(ProjectTasksConstants.BEGIN + " GET -> /api/projects/getProjects - Obejct [" + Project.class + "]");
        Iterable<Project> projects = projectService.getAllProjects();
        logger.info(ProjectTasksConstants.END + " GET -> /api/projects/getProjects - Obejct [" + Project.class + "]");
        return projects;
    }

    @GetMapping("/project/{id}")
    public ResponseEntity<Object> getProjectById(@PathVariable("id") Long idProject) {
        logger.info(ProjectTasksConstants.BEGIN + " GET -> /api/projects/project/" + idProject);
        Optional<Project> project = projectService.findById(idProject);
        logger.info(ProjectTasksConstants.END + " GET -> /api/projects/project/" + idProject);
        return new ResponseEntity<Object>(project, HttpStatus.OK);
    }

    @DeleteMapping("/project/{id}")
    public ResponseEntity<Object> deleteTaskById(@PathVariable("id") Long idProject) {
        logger.info(ProjectTasksConstants.BEGIN + " DELETE -> /api/projects/project/" + idProject);
        projectService.deleteProject(idProject);
        logger.info(ProjectTasksConstants.END + " DELETE -> /api/projects/project/" + idProject);
        return new ResponseEntity<Object>("Project deleted with success", HttpStatus.OK);
    }
}
