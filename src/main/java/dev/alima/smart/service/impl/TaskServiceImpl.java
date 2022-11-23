package dev.alima.smart.service.impl;

import dev.alima.smart.domain.dto.command.TaskCommand;
import dev.alima.smart.domain.dto.command.UpdateTaskCommand;
import dev.alima.smart.domain.dto.response.TaskResponse;
import dev.alima.smart.domain.entity.TaskEntity;
import dev.alima.smart.domain.mapper.TaskMapper;
import dev.alima.smart.exception.BadRequestException;
import dev.alima.smart.repository.TaskRepository;
import dev.alima.smart.service.TaskService;
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
