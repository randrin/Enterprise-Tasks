package com.enterprise.tasks.web;

import com.enterprise.tasks.dto.ProjectTasks;
import com.enterprise.tasks.service.ProjectTasksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @Autowired
    private ProjectTasksService projectTasksService;

    @PostMapping("/saveTask")
    public ResponseEntity<Object> saveTask (@Valid @RequestBody ProjectTasks projectTask, BindingResult result) {

        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<String, String>();
            for (FieldError error : result.getFieldErrors())  {
                errors.put(error.getField(), error.getDefaultMessage());
                return new ResponseEntity<Object>(errors, HttpStatus.BAD_REQUEST);
            }
        }
        ProjectTasks pt = projectTasksService.addOrUpdateTask(projectTask);

        return new ResponseEntity<Object>(pt, HttpStatus.CREATED);
    }

    @GetMapping("/getTasks")
    public Iterable<ProjectTasks> getTasks() {
        Iterable<ProjectTasks> projectTasks = projectTasksService.getAllTasks();
        return projectTasks;
    }

    @GetMapping("/task/{id}")
    public ResponseEntity<Object> getTaskById(@PathVariable("id") Long idTask){
        Optional<ProjectTasks> projectTask = projectTasksService.findById(idTask);
        return new ResponseEntity<Object>(projectTask, HttpStatus.OK);
    }
}
