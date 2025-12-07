package uk.gov.hmcts.reform.dev.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import uk.gov.hmcts.reform.dev.models.Task;
import uk.gov.hmcts.reform.dev.repositories.TaskRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TaskServiceTest {

    @Autowired
    private TaskRepository taskRepository;

    @Test
    void createTask_shouldPersistTaskInDatabase() {
        // Arrange
        TaskService taskService = new TaskService(taskRepository);
        Task task = new Task("Test Title",
                             "Test Description",
                             "Completed",
                             "2025-12-31T20:00");

        // Act
        int taskId = taskService.createTask(task);

        // Assert
        Task savedTask = taskRepository.findById(taskId).orElse(null);
        assertNotNull(savedTask);
        assertEquals("Test Title", savedTask.getTitle());
        assertEquals("Test Description", savedTask.getDescription());
        assertEquals("Completed", savedTask.getStatus());
        assertEquals("2025-12-31T20:00", savedTask.getDueDate());
    }

    @Test
    void shouldSanitizeInvalidHtml() {
        // Arrange
        TaskService taskService = new TaskService(taskRepository);
        Task task = new Task("Test Title",
                             "",
                             "Invalid",
                             "Invalid");

        // Act
        int taskId = taskService.createTask(task);

        // Assert
        Task savedTask = taskRepository.findById(taskId).orElse(null);
        assertNotNull(savedTask);
        assertEquals("Test Title", savedTask.getTitle());
        assertEquals("", savedTask.getDescription());
        assertEquals("", savedTask.getStatus());
        assertEquals("", savedTask.getDueDate());
    }
}
