package dev.alima.tasks.domain.dto.command;

import dev.alima.tasks.domain.values.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
public class UpdateTaskCommand {
    @NotNull
    private Long id;
    private String title;
    private String subtitle;
    private Status status;
}