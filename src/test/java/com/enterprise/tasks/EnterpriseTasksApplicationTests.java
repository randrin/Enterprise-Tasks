package com.enterprise.tasks;

import com.enterprise.tasks.services.EmployeeService;
import com.enterprise.tasks.services.ProjectService;
import com.enterprise.tasks.services.ProjectTasksService;
import com.enterprise.tasks.web.EmployeeController;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;

@RunWith(SpringRunner.class)
@WebMvcTest
class EnterpriseTasksApplicationTests {

    private static final Logger logger = LoggerFactory.getLogger(EnterpriseTasksApplicationTests.class);

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ProjectService projectService;

    @MockBean
    EmployeeService employeeService;

    @MockBean
    ProjectTasksService projectTasksService;

    @Test
    void contextLoadsProjects() throws Exception {
        Mockito.when(projectService.getAllProjects()).thenReturn(
                Collections.emptyList()
        );
        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.get("/api/projects/getProjects")
                .accept(MediaType.APPLICATION_JSON)
        ).andReturn();
        logger.info("mvcResult contextLoadsProjects: ", mvcResult.getResponse());
        Mockito.verify(projectService).getAllProjects();
    }

    @Test
    void contextLoadsProjectTasks() throws Exception {
        Mockito.when(projectTasksService.getAllTasks()).thenReturn(
                Collections.emptyList()
        );
        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.get("/api/tasks/getTasks")
                        .accept(MediaType.APPLICATION_JSON)
        ).andReturn();
        logger.info("mvcResult contextLoadsProjectTasks: ", mvcResult.getResponse());
        Mockito.verify(projectTasksService).getAllTasks();
    }

    @Test
    void contextLoadsEmployees() throws Exception {
        Mockito.when(employeeService.getAllEmployee()).thenReturn(
                Collections.emptyList()
        );
        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.get("/api/getEmployees")
                        .accept(MediaType.APPLICATION_JSON)
        ).andReturn();
        logger.info("mvcResult contextLoadsEmployees: ", mvcResult.getResponse());
        Mockito.verify(employeeService).getAllEmployee();
    }
}
