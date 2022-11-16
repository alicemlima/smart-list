package dev.alima.tasks.domain.dto.command;

import dev.alima.tasks.domain.values.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;

@Getter
@AllArgsConstructor
@Builder
public class UpdateTaskCommand {
    @NotEmpty
    private Long id;
    private String title;
    private String subtitle;
    private Status status;
}