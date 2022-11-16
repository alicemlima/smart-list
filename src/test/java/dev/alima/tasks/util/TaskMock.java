package dev.alima.tasks.util;

import dev.alima.tasks.domain.dto.command.TaskCommand;
import dev.alima.tasks.domain.dto.command.UpdateTaskCommand;
import dev.alima.tasks.domain.dto.response.TaskResponse;
import dev.alima.tasks.domain.entity.TaskEntity;
import dev.alima.tasks.domain.values.Status;

public class TaskMock {
    public static TaskEntity createTaskToBeSaved() {
        return TaskEntity.builder()
                .title("IMPA Project")
                .subtitle("IMPA API")
                .status(Status.TODO)
                .build();
    }

    public static TaskEntity createValidTaskEntity() {
        return TaskEntity.builder()
                .id(1L)
                .title("IMPA Project valid")
                .subtitle("IMPA API valid")
                .status(Status.TODO)
                .build();
    }

    public static TaskResponse createValidTaskResponse() {
        return TaskResponse.builder()
                .id(1L)
                .title("IMPA Project valid")
                .subtitle("IMPA API valid")
                .status(Status.TODO)
                .build();
    }

    public static UpdateTaskCommand createUpdatedTask() {
        return UpdateTaskCommand.builder()
                .id(1L)
                .title("Send IMPA Project")
                .subtitle("IMPA project working")
                .status(Status.PROGRESS)
                .build();
    }

    public static TaskCommand createValidTaskCommand() {
        return TaskCommand.builder()
                .title("IMPA project working")
                .subtitle("Send IMPA Project")
                .build();
    }
}
