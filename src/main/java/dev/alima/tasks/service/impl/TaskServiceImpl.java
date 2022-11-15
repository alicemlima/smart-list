package dev.alima.tasks.service.impl;

import dev.alima.tasks.domain.dto.command.TaskCommand;
import dev.alima.tasks.domain.dto.command.UpdateTaskCommand;
import dev.alima.tasks.domain.dto.response.TaskResponse;
import dev.alima.tasks.domain.entity.TaskEntity;
import dev.alima.tasks.domain.mapper.TaskMapper;
import dev.alima.tasks.repository.TaskRepository;
import dev.alima.tasks.service.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    public List<TaskResponse> findAll() {
        return taskMapper.taskEntityToTaskResponse(taskRepository.findAll());
    }

    @Override
    public void save(TaskCommand taskCommand) {
        try {
            taskRepository.save(taskMapper.taskCommandToTaskEntity(taskCommand));
        } catch (Exception e) {
            log.error("Error {}", taskCommand.getTitle());
        }
    }

    private TaskEntity findByIdOrThrowsBadRequestException(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Task not found"));
    }

    @Override
    public void delete(long id) {
        taskRepository.deleteById(findByIdOrThrowsBadRequestException(id).getId());
    }

    @Override
    public void update(UpdateTaskCommand updateTaskCommand) {
            TaskEntity taskById = findByIdOrThrowsBadRequestException(updateTaskCommand.getId());
            taskRepository.save(taskMapper.updateTaskCommandToTaskEntity(updateTaskCommand, taskById));
    }

}
