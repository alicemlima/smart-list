package dev.alima.smart.domain.mapper;

import dev.alima.smart.domain.dto.command.TaskCommand;
import dev.alima.smart.domain.dto.command.UpdateTaskCommand;
import dev.alima.smart.domain.dto.response.TaskResponse;
import dev.alima.smart.domain.entity.TaskEntity;
import dev.alima.smart.domain.values.Status;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {
    public TaskResponse taskEntityToTaskResponse(final TaskEntity tasks) {
        return TaskResponse.builder()
                .id(tasks.getId())
                .title(tasks.getTitle())
                .subtitle(tasks.getSubtitle())
                .status(tasks.getStatus())
                .endDate(tasks.getEndDate())
                .build();
    }

    public TaskEntity taskCommandToTaskEntity(final TaskCommand taskCommand) {
        return TaskEntity.builder()
                .title(taskCommand.getTitle())
                .subtitle(taskCommand.getSubtitle())
                .status(Status.TODO)
                .endDate(taskCommand.getEndDate())
                .build();
    }

    public TaskEntity updateTaskCommandToTaskEntity(UpdateTaskCommand updateTaskCommand, TaskEntity taskEntity) {
        return TaskEntity.builder()
                .id(updateTaskCommand.getId())
                .title(updateTaskCommand.getTitle() == null ? taskEntity.getTitle() : updateTaskCommand.getTitle())
                .subtitle(updateTaskCommand.getSubtitle() == null ? taskEntity.getSubtitle() : updateTaskCommand.getSubtitle())
                .status(updateTaskCommand.getStatus() == null ? taskEntity.getStatus() : updateTaskCommand.getStatus())
                .endDate(updateTaskCommand.getEndDate() == null ? taskEntity.getEndDate() : updateTaskCommand.getEndDate())
                .build();
    }
}
