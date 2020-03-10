package com.enterprise.tasks.web;

import com.enterprise.tasks.dto.Employee;
import com.enterprise.tasks.service.EmployeeService;
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
@RequestMapping("/api")
@CrossOrigin("*")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/saveEmployee")
    public ResponseEntity<Object> saveEmployee (@Valid @RequestBody Employee employee, BindingResult result) {

        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<String, String>();
            for (FieldError error : result.getFieldErrors())  {
                errors.put(error.getField(), error.getDefaultMessage());
                return new ResponseEntity<Object>(errors, HttpStatus.BAD_REQUEST);
            }
        }
        Employee epl = employeeService.addOrUpdateEmployee(employee);

        return new ResponseEntity<Object>(epl, HttpStatus.CREATED);
    }

    @GetMapping("/getTasksEmployee")
    public Iterable<Employee> getTasksEmployee() {
        Iterable<Employee> employeeTasks = employeeService.getAllTasksEmployee();
        return employeeTasks;
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<Object> getEmployeeById(@PathVariable("id") Long idEmployee){
        Optional<Employee> employee = employeeService.findById(idEmployee);
        return new ResponseEntity<Object>(employee, HttpStatus.OK);
    }

    @DeleteMapping("/employee/{id}")
    public ResponseEntity<Object> deleteEmployeeById(@PathVariable("id") Long idEmployee){
        employeeService.deleteEmployee(idEmployee);
        return new ResponseEntity<Object>("Employee deleted with success", HttpStatus.OK);
    }
}