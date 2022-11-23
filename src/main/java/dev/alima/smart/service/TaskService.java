package dev.alima.smart.service;

import dev.alima.smart.domain.dto.command.TaskCommand;
import dev.alima.smart.domain.dto.command.UpdateTaskCommand;
import dev.alima.smart.domain.dto.response.TaskResponse;

import java.util.List;

public interface TaskService {
    List<TaskResponse> findAll();

    TaskResponse findById(Long id);

    List<TaskResponse> findByTitle(String title);

    void save(final TaskCommand taskCommand);

    void delete(final long id);

    void update(final UpdateTaskCommand updateTaskCommand);
}
