package dev.alima.tasks.domain.dto.command;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
public class TaskCommand {
    @NotBlank
    private String title;
    @NotBlank
    private String subtitle;
}