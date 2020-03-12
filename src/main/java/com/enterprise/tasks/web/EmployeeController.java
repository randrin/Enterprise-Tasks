package com.enterprise.tasks.web;

import com.enterprise.tasks.dto.Employee;
import com.enterprise.tasks.services.EmployeeService;
import com.enterprise.tasks.utils.ProjectTasksConstants;
import com.enterprise.tasks.utils.ProjectsUtils;
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
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class EmployeeController {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);
    @Autowired
    private EmployeeService employeeService;

    @PostMapping(value = "/saveEmployee", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> saveEmployee(@Valid @RequestBody Employee employee, BindingResult result) {

        logger.info(ProjectTasksConstants.BEGIN + " POST -> /api/saveEmployee - Obejct [" + Employee.class + "]");
        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<String, String>();
            for (FieldError error : result.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
                return new ResponseEntity<Object>(errors, HttpStatus.BAD_REQUEST);
            }
        }
        logger.info("EmployeeDate: ", ProjectsUtils.convertDateToLocalDateTime(new Date(String.valueOf(employee.getEmployeeBornDate()))));
        Employee epl = employeeService.addOrUpdateEmployee(employee);

        logger.info(ProjectTasksConstants.END + " POST -> /api/saveEmployee - Obejct [" + Employee.class + "]");
        return new ResponseEntity<Object>(epl, HttpStatus.CREATED);
    }

    @GetMapping("/getEmployees")
    public Iterable<Employee> getEmployees() {
        logger.info(ProjectTasksConstants.BEGIN + " GET -> /api/getEmployees - Obejct [" + Employee.class + "]");
        Iterable<Employee> employees = employeeService.getAllEmployee();
        logger.info(ProjectTasksConstants.END + " GET -> /api/getEmployees - Obejct [" + Employee.class + "]");
        return employees;
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<Object> getEmployeeById(@PathVariable("id") Long idEmployee) {
        logger.info(ProjectTasksConstants.BEGIN + " GET -> /api/employee/" + idEmployee);
        Optional<Employee> employee = employeeService.findById(idEmployee);
        logger.info(ProjectTasksConstants.END + " GET -> /api/employee/" + idEmployee);
        return new ResponseEntity<Object>(employee, HttpStatus.OK);
    }

    @DeleteMapping("/employee/{id}")
    public ResponseEntity<Object> deleteEmployeeById(@PathVariable("id") Long idEmployee) {
        logger.info(ProjectTasksConstants.BEGIN + " DELETE -> /api/employee/" + idEmployee);
        employeeService.deleteEmployee(idEmployee);
        logger.info(ProjectTasksConstants.END + " DELETE -> /api/employee/" + idEmployee);
        return new ResponseEntity<Object>("Employee deleted with success", HttpStatus.OK);
    }
}
