package dev.alima.smart.domain.dto.command;

import dev.alima.smart.domain.values.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
@Builder
public class UpdateGoalCommand {
    @NotEmpty
    private Long id;
    private String title;
    private String subtitle;
    private Status status;
    private LocalDate endDate;
}