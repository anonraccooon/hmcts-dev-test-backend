package uk.gov.hmcts.reform.dev.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uk.gov.hmcts.reform.dev.exceptions.NoSuchTaskExistsException;
import uk.gov.hmcts.reform.dev.models.Task;
import uk.gov.hmcts.reform.dev.repositories.TaskRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public int createTask(Task task) {
        taskRepository.save(task);
        return task.getId();
    }

    public Task getTaskFromId(int id) {
        return taskRepository.findById(id)
            .orElseThrow(() -> new NoSuchTaskExistsException("Task with ID " + id + " not found"));

    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }
}
