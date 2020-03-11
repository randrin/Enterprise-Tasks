package com.enterprise.tasks.service;

import com.enterprise.tasks.dto.Employee;
import com.enterprise.tasks.repository.EmployeeRepository;
import com.enterprise.tasks.utils.ProjectsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee addOrUpdateEmployee(Employee employee) {

        if (employee.getEmployeeMatricule() == null || employee.getEmployeeMatricule() == "") {
            employee.setEmployeeMatricule(ProjectsUtils.generateMatriculeEmployee(employee.getEmployeeFirstName(), employee.getEmployeeLastName(),
                    employee.getEmployeeBornDate(), employee.getEmployeeDepartment()));
        }
        return employeeRepository.save(employee);
    }

    public Iterable<Employee> getAllEmployee() {
        return employeeRepository.findAll();
    }

    public Optional<Employee> findById(Long id) {
        return employeeRepository.findById(id);
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

}
