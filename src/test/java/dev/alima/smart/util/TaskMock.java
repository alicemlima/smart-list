package dev.alima.smart.util;

import dev.alima.smart.domain.dto.command.TaskCommand;
import dev.alima.smart.domain.dto.command.UpdateTaskCommand;
import dev.alima.smart.domain.dto.response.TaskResponse;
import dev.alima.smart.domain.entity.TaskEntity;
import dev.alima.smart.domain.values.Status;

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
