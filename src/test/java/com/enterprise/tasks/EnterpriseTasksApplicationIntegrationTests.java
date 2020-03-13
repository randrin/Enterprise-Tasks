package com.enterprise.tasks;

import com.enterprise.tasks.services.EmployeeService;
import com.enterprise.tasks.services.ProjectService;
import com.enterprise.tasks.services.ProjectTasksService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = EnterpriseTasksApplication.class
)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
class EnterpriseTasksApplicationIntegrationTests {

    private static final Logger logger = LoggerFactory.getLogger(EnterpriseTasksApplicationIntegrationTests.class);

    @Autowired
    MockMvc mockMvc;

    @Test
    void contextLoadsProjects() throws Exception {
        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.get("/api/projects/getProjects")
                .accept(MediaType.APPLICATION_JSON)
        ).andReturn();
        logger.info("mvcResult contextLoadsProjects: ", mvcResult.getResponse());
    }

    @Test
    void contextLoadsProjectTasks() throws Exception {
        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.get("/api/tasks/getTasks")
                        .accept(MediaType.APPLICATION_JSON)
        ).andReturn();
        logger.info("mvcResult contextLoadsProjectTasks: ", mvcResult.getResponse());
    }

    @Test
    void contextLoadsEmployees() throws Exception {
        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.get("/api/getEmployees")
                        .accept(MediaType.APPLICATION_JSON)
        ).andReturn();
        logger.info("mvcResult contextLoadsEmployees: ", mvcResult.getResponse());
    }
}
