package uk.gov.hmcts.reform.dev.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.hmcts.reform.dev.models.Task;
import uk.gov.hmcts.reform.dev.services.TaskService;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RequiredArgsConstructor
@RestController
public class TaskController {

    private final TaskService taskService;

    @GetMapping(value = "/tasks", produces = "application/json")
    public ResponseEntity<List<Task>> getTasks() {
        return ok(taskService.getAllTasks());

    }

    @PostMapping(value = "/tasks", produces = "application/json")
    public ResponseEntity<Integer> createTask(@Valid @RequestBody Task task) {
        return ok(taskService.createTask(task));
    }

    @GetMapping(value = "/tasks/{id}", produces = "application/json")
    public ResponseEntity<Task> getTask(@PathVariable int id) {
        return ok(taskService.getTaskFromId(id));
    }

}
