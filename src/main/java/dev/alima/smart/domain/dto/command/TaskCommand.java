package dev.alima.smart.domain.dto.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Data
@Getter
@AllArgsConstructor
@Builder
public class TaskCommand {
    @NotEmpty(message = "Title cannot be empty")
    private String title;
    @NotEmpty(message = "Subtitle cannot be empty")
    private String subtitle;
    private LocalDate endDate;
}