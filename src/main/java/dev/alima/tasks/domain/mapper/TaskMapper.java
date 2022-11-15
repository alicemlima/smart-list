package dev.alima.tasks.domain.mapper;

import dev.alima.tasks.domain.dto.command.TaskCommand;
import dev.alima.tasks.domain.dto.command.UpdateTaskCommand;
import dev.alima.tasks.domain.dto.response.TaskResponse;
import dev.alima.tasks.domain.entity.TaskEntity;
import dev.alima.tasks.domain.values.Status;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TaskMapper {
    public List<TaskResponse> taskEntityToTaskResponse(final List<TaskEntity> tasks) {
        return tasks.stream()
                .map(task -> TaskResponse.builder()
                        .id(task.getId())
                        .title(task.getTitle())
                        .subtitle(task.getSubtitle())
                        .status(task.getStatus())
                        .build())
                .toList();
    }

    public TaskEntity taskCommandToTaskEntity(final TaskCommand taskCommand) {
        return TaskEntity.builder()
                .title(taskCommand.getTitle())
                .subtitle(taskCommand.getSubtitle())
                .status(Status.TODO)
                .build();
    }

    public TaskEntity updateTaskCommandToTaskEntity(UpdateTaskCommand updateTaskCommand, TaskEntity taskEntity) {
        return TaskEntity.builder()
                .id(updateTaskCommand.getId())
                .title(updateTaskCommand.getTitle() == null ? taskEntity.getTitle() : updateTaskCommand.getTitle())
                .subtitle(updateTaskCommand.getSubtitle() == null ? taskEntity.getSubtitle() : updateTaskCommand.getSubtitle())
                .status(updateTaskCommand.getStatus() == null ? taskEntity.getStatus() : updateTaskCommand.getStatus())
                .build();
    }
}
