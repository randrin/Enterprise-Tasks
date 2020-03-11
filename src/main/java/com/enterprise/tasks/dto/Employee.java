package com.enterprise.tasks.dto;

import com.enterprise.tasks.utils.ProjectTasksConstants;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;

@Entity
public class Employee  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = ProjectTasksConstants.EMPLOYEE_FIRST_NAME_REQUIRED)
    private String employeeFirstName;

    private String employeeLastName;

    @NotBlank(message = ProjectTasksConstants.EMPLOYEE_DEPARTMENT_REQUIRED)
    private String employeeDepartment;

    @NotBlank(message = ProjectTasksConstants.EMPLOYEE_GENDER_REQUIRED)
    private String employeeGender;

    @NotBlank(message = ProjectTasksConstants.EMPLOYEE_GENDER_REQUIRED)
    private String employeeEmail;

    @JsonFormat(pattern = ProjectTasksConstants.PROJECT_PATTERN_VALIDATION)
    private LocalDateTime employeeBornDate;

    private String employeePhoneNumber;

    private String employeeMatricule;

    @OneToMany(mappedBy = "employee")
    private Collection<ProjectTasks> projectTasksList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmployeeFirstName() {
        return employeeFirstName;
    }

    public void setEmployeeFirstName(String employeeFirstName) {
        this.employeeFirstName = employeeFirstName;
    }

    public String getEmployeeLastName() {
        return employeeLastName;
    }

    public void setEmployeeLastName(String employeeLastName) {
        this.employeeLastName = employeeLastName;
    }

    public String getEmployeeDepartment() {
        return employeeDepartment;
    }

    public void setEmployeeDepartment(String employeeDepartment) {
        this.employeeDepartment = employeeDepartment;
    }

    public String getEmployeeGender() {
        return employeeGender;
    }

    public void setEmployeeGender(String employeeGender) {
        this.employeeGender = employeeGender;
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }

    public LocalDateTime getEmployeeBornDate() {
        return employeeBornDate;
    }

    public void setEmployeeBornDate(LocalDateTime employeeBornDate) {
        this.employeeBornDate = employeeBornDate;
    }

    public String getEmployeePhoneNumber() {
        return employeePhoneNumber;
    }

    public void setEmployeePhoneNumber(String employeePhoneNumber) {
        this.employeePhoneNumber = employeePhoneNumber;
    }

    public String getEmployeeMatricule() {
        return employeeMatricule;
    }

    public void setEmployeeMatricule(String employeeMatricule) {
        this.employeeMatricule = employeeMatricule;
    }

    public Collection<ProjectTasks> getProjectTasksList() {
        return projectTasksList;
    }

    public void setProjectTasksList(Collection<ProjectTasks> projectTasksList) {
        this.projectTasksList = projectTasksList;
    }
}
