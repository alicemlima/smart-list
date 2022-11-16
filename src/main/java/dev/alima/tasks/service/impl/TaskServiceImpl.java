package dev.alima.tasks.service.impl;

import dev.alima.tasks.domain.dto.command.TaskCommand;
import dev.alima.tasks.domain.dto.command.UpdateTaskCommand;
import dev.alima.tasks.domain.dto.response.TaskResponse;
import dev.alima.tasks.domain.entity.TaskEntity;
import dev.alima.tasks.domain.mapper.TaskMapper;
import dev.alima.tasks.exception.BadRequestException;
import dev.alima.tasks.repository.TaskRepository;
import dev.alima.tasks.service.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    @Override
    public List<TaskResponse> findAll() {
        return taskRepository.findAll().stream().map(taskMapper::taskEntityToTaskResponse).toList();
    }

    @Override
    public TaskResponse findById(Long id) {
        return taskMapper.taskEntityToTaskResponse(findByIdOrThrowsBadRequestException(id));
    }

    @Override
    public List<TaskResponse> findByTitle(String title) {
        return taskRepository.findByTitle(title).stream().map(taskMapper::taskEntityToTaskResponse).toList();
    }

    @Override
    @Transactional
    public void save(TaskCommand taskCommand) {
        try {
            taskRepository.save(taskMapper.taskCommandToTaskEntity(taskCommand));
        } catch (Exception e) {
            new BadRequestException("Error to save Task");
        }
    }

    private TaskEntity findByIdOrThrowsBadRequestException(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Task not found"));
    }

    @Override
    @Transactional
    public void delete(long id) {
        taskRepository.deleteById(findByIdOrThrowsBadRequestException(id).getId());
    }

    @Override
    @Transactional
    public void update(UpdateTaskCommand updateTaskCommand) {
        TaskEntity taskById = findByIdOrThrowsBadRequestException(updateTaskCommand.getId());
        taskRepository.save(taskMapper.updateTaskCommandToTaskEntity(updateTaskCommand, taskById));
    }

}
