package com.enterprise.tasks.services;

import com.enterprise.tasks.dto.Employee;
import com.enterprise.tasks.repository.EmployeeRepository;
import com.enterprise.tasks.utils.ProjectTasksConstants;
import com.enterprise.tasks.utils.ProjectsUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeService {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee addOrUpdateEmployee(Employee employee) {

        logger.info(ProjectTasksConstants.SERVICE + " addOrUpdateEmployee(" + Employee.class + ")");

        if (employee.getEmployeeMatricule() == null || employee.getEmployeeMatricule() == "") {
            employee.setEmployeeMatricule(ProjectsUtils.generateMatriculeEmployee(employee.getEmployeeFirstName(), employee.getEmployeeLastName(),
                    employee.getEmployeeBornDate(), employee.getEmployeeDepartment()));
        }
        return employeeRepository.save(employee);
    }

    public Iterable<Employee> getAllEmployee() {

        logger.info(ProjectTasksConstants.SERVICE + " getAllEmployee()");

        return employeeRepository.findAll();
    }

    public Optional<Employee> findById(Long id) {
        logger.info(ProjectTasksConstants.SERVICE + " findById()");
        return employeeRepository.findById(id);
    }

    public void deleteEmployee(Long id) {
        logger.info(ProjectTasksConstants.SERVICE + " deleteById()");
        employeeRepository.deleteById(id);
    }
}
