package uk.gov.hmcts.reform.dev.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uk.gov.hmcts.reform.dev.exceptions.NoSuchTaskExistsException;
import uk.gov.hmcts.reform.dev.models.Task;
import uk.gov.hmcts.reform.dev.repositories.TaskRepository;

import java.util.List;

/**
 * Service class for managing Task entities, including creation, retrieval, and listing.
 * Interacts with the TaskRepository for database operations.
 */
@RequiredArgsConstructor
@Service
public class TaskService {

    private final TaskRepository taskRepository;

    /**
     * Creates a new task and saves it to the repository.
     *
     * @param task the Task object to create
     * @return the ID of the newly created task
     */
    public int createTask(Task task) {
        taskRepository.save(task);
        return task.getId();
    }

    /**
     * Retrieves a task by its ID.
     *
     * @param id the ID of the task to retrieve
     * @return the Task object if found
     * @throws NoSuchTaskExistsException if no task exists with the given ID
     */
    public Task getTaskFromId(int id) {
        return taskRepository.findById(id)
            .orElseThrow(() -> new NoSuchTaskExistsException("Task with ID " + id + " not found"));
    }

    /**
     * Retrieves all tasks from the repository.
     *
     * @return a list of all Task objects
     */
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }
}
