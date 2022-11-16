package dev.alima.tasks.domain.mapper;

import dev.alima.tasks.domain.dto.command.TaskCommand;
import dev.alima.tasks.domain.dto.command.UpdateTaskCommand;
import dev.alima.tasks.domain.dto.response.TaskResponse;
import dev.alima.tasks.domain.entity.TaskEntity;
import dev.alima.tasks.domain.values.Status;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {
    public TaskResponse taskEntityToTaskResponse(final TaskEntity tasks) {
        return TaskResponse.builder()
                .id(tasks.getId())
                .title(tasks.getTitle())
                .subtitle(tasks.getSubtitle())
                .status(tasks.getStatus())
                .build();
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
