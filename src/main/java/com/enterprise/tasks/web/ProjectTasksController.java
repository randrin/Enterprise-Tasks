package com.enterprise.tasks.web;

import com.enterprise.tasks.dto.ProjectTasks;
import com.enterprise.tasks.services.ProjectTasksService;
import com.enterprise.tasks.utils.ProjectTasksConstants;
import io.swagger.annotations.ApiOperation;
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
@RequestMapping("/api/tasks")
@CrossOrigin("*")
public class ProjectTasksController {

    private static final Logger logger = LoggerFactory.getLogger(ProjectTasksController.class);
    @Autowired
    private ProjectTasksService projectTasksService;

    @PostMapping(value = "/saveTask", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(
            value = "Add a new Project Task",
            notes = "Add a specific project task in the enterprise",
            response = ProjectTasks.class)
    public ResponseEntity<Object> saveTask(@Valid @RequestBody ProjectTasks projectTask, BindingResult result) {

        logger.info(ProjectTasksConstants.BEGIN + " POST -> /api/tasks/saveTask - Obejct [" + ProjectTasks.class + "]");

        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<String, String>();
            for (FieldError error : result.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
                return new ResponseEntity<Object>(errors, HttpStatus.BAD_REQUEST);
            }
        }
        ProjectTasks pt = projectTasksService.addOrUpdateTask(projectTask);

        logger.info(ProjectTasksConstants.END + " POST -> /api/tasks/saveTask - Obejct [" + ProjectTasks.class + "]");
        return new ResponseEntity<Object>(pt, HttpStatus.CREATED);
    }

    @GetMapping("/getTasks")
    @ApiOperation(
            value = "Get all Project Tasks by ID",
            notes = "Retrieve all project tasks from the enterprise",
            response = ProjectTasks.class)
    public Iterable<ProjectTasks> getTasks() {
        logger.info(ProjectTasksConstants.BEGIN + " GET -> /api/tasks/getTasks - Obejct [" + ProjectTasks.class + "]");
        Iterable<ProjectTasks> projectTasks = projectTasksService.getAllTasks();
        logger.info(ProjectTasksConstants.END + " GET -> /api/tasks/getTasks - Obejct [" + ProjectTasks.class + "]");
        return projectTasks;
    }

    @GetMapping("/task/{id}")
    @ApiOperation(
            value = "Find Project Task by ID",
            notes = "Provide an id to identify the specific project task from the enterprise",
            response = ProjectTasks.class)
    public ResponseEntity<Object> getTaskById(@PathVariable("id") Long idTask) {
        logger.info(ProjectTasksConstants.BEGIN + " GET -> /api/tasks/task/" + idTask);
        Optional<ProjectTasks> projectTask = projectTasksService.findById(idTask);
        logger.info(ProjectTasksConstants.END + " GET -> /api/tasks/task/" + idTask);
        return new ResponseEntity<Object>(projectTask, HttpStatus.OK);
    }

    @DeleteMapping("/task/{id}")
    @ApiOperation(
            value = "Delete Project Task by ID",
            notes = "Provide an id to delete the specific project task from the enterprise",
            response = ProjectTasks.class)
    public ResponseEntity<Object> deleteTaskById(@PathVariable("id") Long idTask) {
        logger.info(ProjectTasksConstants.BEGIN + " DELETE -> /api/tasks/task/" + idTask);
        projectTasksService.deleteProjectTask(idTask);
        logger.info(ProjectTasksConstants.END + " DELETE -> /api/tasks/task/" + idTask);
        return new ResponseEntity<Object>("Project Task deleted with success", HttpStatus.OK);
    }
}
