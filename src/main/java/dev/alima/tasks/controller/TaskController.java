package dev.alima.tasks.controller;

import dev.alima.tasks.domain.dto.command.TaskCommand;
import dev.alima.tasks.domain.dto.command.UpdateTaskCommand;
import dev.alima.tasks.domain.dto.response.TaskResponse;
import dev.alima.tasks.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "tasks", produces = APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class TaskController {
    private TaskService taskService;

    @Operation(summary = "Get tasks list")
    @GetMapping
    public ResponseEntity<List<TaskResponse>> getTasks() {
        return ResponseEntity.ok(taskService.findAll());
    }

    @Operation(summary = "Post task")
    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<String> save(@RequestBody TaskCommand taskCommand) {
        taskService.save(taskCommand);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Delete task")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> delete(@PathVariable long id) {
        taskService.delete(id);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Update task title")
    @PatchMapping
    public ResponseEntity<String> update(@RequestBody UpdateTaskCommand updateTaskCommand){
        taskService.update(updateTaskCommand);
        return ResponseEntity.ok().build();
    }
}
