package com.enterprise.tasks.utils;

public class ProjectTasksConstants {

    // TOOLS
    public static final String PROJECT_DEFAULT_IMAGE_LOGO = "https://www.kazoucoin.com/assets/img/photo.jpg";
    public static final String BEGIN = "PROCESSING ...";
    public static final String END = "END ...";
    public static final String SERVICE = "SERVICE ...";
    public static final String METHOD = "METHOD ...";
    public static final String RESULT_METHOD = "RESULT METHOD ...";

    // STATUS
    public static final String TO_DO = "TO_DO";
    public static final String DONE = "DONE";
    public static final String IN_PROGRESS = "IN PROGRESS";
    public static final String CLOSED = "CLOSED";

    // PRIORITY
    public static final String PRIORITY_MINOR = "MINOR";
    public static final String PRIORITY_MEDIUM = "MEDIUM";
    public static final String PRIORITY_MAJOR = "MAJOR";
    public static final String PRIORITY_LOW = "LOW";
    public static final String PRIORITY_PROGRESS = "IN PROGRESS";
    public static final String PRIORITY_HOLD = "IN HOLD";
    public static final String PRIORITY_HIGH = "HIGH";

    // REQUIRED FIELD
    public static final String TASK_NAME_REQUIRED = "Name task cannot be empty";
    public static final String TASK_DESCRIPTION_REQUIRED = "Description task cannot be empty";
    public static final String TASK_PRIORITY_REQUIRED = "Select the priority task";
    public static final String PROJECT_NAME_REQUIRED = "Project Name cannot be empty";
    public static final String PROJECT_DESCRIPTION_REQUIRED = "Project Description cannot be empty";
    public static final String PROJECT_PRIORITY_REQUIRED = "Select the priority project";
    public static final String PROJECT_CREATED_BY_REQUIRED = "Project user's created cannot be empty";
    public static final String PROJECT_IMAGE_LOGO_REQUIRED = "Project logo cannot be empty";
    public static final String PROJECT_END_DATE_REQUIRED = "Project end date cannot be empty";
    public static final String EMPLOYEE_FIRST_NAME_REQUIRED = "First Name Employee cannot be empty";
    public static final String EMPLOYEE_DEPARTMENT_REQUIRED = "Department Employee cannot be empty";
    public static final String EMPLOYEE_GENDER_REQUIRED = "Gender Employee cannot be empty";
    public static final String EMPLOYEE_EMAIL_REQUIRED = "Email Employee cannot be empty";

    // VALIDATIONS
    public static final String PROJECT_START_BEFORE_END_DATE = "Project Start Date will be before End Date";
    public static final String PROJECT_PATTERN_VALIDATION = "dd-MM-yyyy HH:mm:ss";
    public static final String EMPLOYEE_DATE_OF_BORN_PATTERN_VALIDATION = "dd/MM/yyyy";
}
