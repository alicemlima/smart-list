package dev.alima.tasks.domain.dto.response;

import dev.alima.tasks.domain.values.Status;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TaskResponse {
    private Long id;
    private String title;
    private String subtitle;
    private Status status;
}
