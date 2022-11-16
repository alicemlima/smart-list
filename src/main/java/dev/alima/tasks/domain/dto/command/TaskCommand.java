package dev.alima.tasks.domain.dto.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Getter
@AllArgsConstructor
@Builder
public class TaskCommand {
    @NotEmpty(message = "Subtitle cannot be empty")
    private String title;
    @NotEmpty(message = "Subtitle cannot be empty")
    private String subtitle;
}