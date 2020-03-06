package com.enterprise.tasks.web;

import com.enterprise.tasks.dto.Project;
import com.enterprise.tasks.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/projects")
@CrossOrigin("*")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping("/saveProject")
    public ResponseEntity<Object> saveProject (@Valid @RequestBody Project project, BindingResult result) throws ParseException {

        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<String, String>();
            for (FieldError error : result.getFieldErrors())  {
                errors.put(error.getField(), error.getDefaultMessage());
                return new ResponseEntity<Object>(errors, HttpStatus.BAD_REQUEST);
            }
        }
        Project pj = projectService.addOrUpdateProject(project);

        return new ResponseEntity<Object>(pj, HttpStatus.CREATED);
    }

    @GetMapping("/getProjects")
    public Iterable<Project> getProjects() {
        Iterable<Project> projects = projectService.getAllProjects();
        return projects;
    }

    @GetMapping("/project/{id}")
    public ResponseEntity<Object> getProjectById(@PathVariable("id") Long idProject){
        Optional<Project> project = projectService.findById(idProject);
        return new ResponseEntity<Object>(project, HttpStatus.OK);
    }

    @DeleteMapping("/project/{id}")
    public ResponseEntity<Object> deleteTaskById(@PathVariable("id") Long idProject){
        projectService.deleteProject(idProject);
        return new ResponseEntity<Object>("Project deleted with success", HttpStatus.OK);
    }
}
