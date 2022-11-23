package dev.alima.smart.domain.dto.response;

import dev.alima.smart.domain.values.Status;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class TaskResponse {
    private Long id;
    private String title;
    private String subtitle;
    private Status status;
    private LocalDate endDate;
}
