package uk.gov.hmcts.reform.dev.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import uk.gov.hmcts.reform.dev.models.Task;
import uk.gov.hmcts.reform.dev.services.TaskService;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void shouldCreateTaskSuccessfully() throws Exception {
        Task validTask = new Task("Sample Task",
                                  "This is a sample task description",
                                  "Completed",
                                  "2025-12-31T23:59");

        TaskService taskService = mock(TaskService.class);
        when(taskService.createTask(validTask)).thenReturn(1);

        mockMvc.perform(post("/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(validTask)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void shouldReturnValidationErrorForEmptyTitle() throws Exception {
        Task invalidTask = new Task("",
                                    "Description with no title",
                                    "",
                                    "");

        mockMvc.perform(post("/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidTask)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void shouldReturnValidationErrorForExcessiveTitleLength() throws Exception {
        String longTitle = "T".repeat(101);
        Task invalidTask = new Task(longTitle,
                                    "Description",
                                    "",
                                    "");

        mockMvc.perform(post("/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidTask)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void shouldReturnValidationErrorForExcessiveDescriptionLength() throws Exception {
        String longDescription = "D".repeat(501);
        Task invalidTask = new Task("Sample Task",
                                    longDescription,
                                    "",
                                    "");

        mockMvc.perform(post("/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidTask)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void shouldCreateTaskSuccessfullyIfOptionalFieldsInvalid() throws Exception {
        Task task = new Task("Sample Task",
                             "",
                             "Invalid",
                             "Invalid");

        mockMvc.perform(post("/tasks")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(task)))
            .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
