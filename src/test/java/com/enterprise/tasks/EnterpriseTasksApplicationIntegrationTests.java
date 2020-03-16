package com.enterprise.tasks;

import com.enterprise.tasks.dto.Project;
import com.enterprise.tasks.services.EmployeeService;
import com.enterprise.tasks.services.ProjectService;
import com.enterprise.tasks.services.ProjectTasksService;
import com.enterprise.tasks.web.ProjectController;
import org.apache.logging.log4j.util.StringBuilders;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
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
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.regex.Matcher;

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

    @InjectMocks
    private ProjectController projectController;

    @Before
    public void setUp () {
        mockMvc = MockMvcBuilders.standaloneSetup(projectController).build();
    }

    @Test
    void contextLoadsProjects() throws Exception {
        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.get("/api/projects/getProjects?id=1")
                .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                //.andExpect(MockMvcResultMatchers.jsonPath("$[0].project_description", Matchers.is("Plateforme pour les techniciens dans le Cameroun Plateforme pour les techniciens dans le Cameroun Plateforme pour les techniciens dans le Cameroun")))
                //.andExpect(MockMvcResultMatchers.jsonPath("$[0].project_name", Matchers.is("KazouTech")))
                //.andExpect(MockMvcResultMatchers.jsonPath("$[0].priority", Matchers.is("PRIORITY_MINOR")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.*", Matchers.hasSize(4)))
                .andReturn();
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
    void contextPostProjectTask() throws Exception {
        String jsonBody = "{\n" +
                "  \"nameTask\": \"Test Task\",\n" +
                "  \"descriptionTask\": \"Test Description Task\",\n" +
                "  \"status\": \"IN PROGRESS\",\n" +
                "  \"priority\": \"MINOR\"\n" +
                "}";
        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.post("/api/tasks/saveTask")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andExpect(MockMvcResultMatchers.status().is(201))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nameTask", Matchers.is("Test Task")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.descriptionTask", Matchers.is("Test Description Task")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status", Matchers.is("IN PROGRESS")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.priority", Matchers.is("MINOR")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.*", Matchers.hasSize(5)))
                .andReturn();
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
