package dev.alima.smart.domain.mapper;

import dev.alima.smart.domain.dto.command.GoalCommand;
import dev.alima.smart.domain.dto.command.UpdateGoalCommand;
import dev.alima.smart.domain.dto.response.GoalResponse;
import dev.alima.smart.domain.entity.GoalEntity;
import dev.alima.smart.domain.values.Status;
import org.springframework.stereotype.Component;

@Component
public class GoalMapper {
    public GoalResponse goalEntityToGoalResponse(GoalEntity goalEntity) {
        return GoalResponse.builder()
                .id(goalEntity.getId())
                .title(goalEntity.getTitle())
                .subtitle(goalEntity.getSubtitle())
                .status(goalEntity.getStatus())
                .EndDate(goalEntity.getEndDate())
                .build();
    }

    public GoalEntity goalCommandToGoalEntity(GoalCommand goalCommand) {
        return GoalEntity.builder()
                .title(goalCommand.getTitle())
                .subtitle(goalCommand.getSubtitle())
                .status(Status.TODO)
                .endDate(goalCommand.getEndDate())
                .build();
    }

    public GoalEntity updateGoalCommandToGoalEntity(UpdateGoalCommand updateGoalCommand, GoalEntity goalEntity) {
        return GoalEntity.builder()
                .id(updateGoalCommand.getId())
                .title(updateGoalCommand.getTitle() == null ? goalEntity.getTitle() : updateGoalCommand.getTitle())
                .subtitle(updateGoalCommand.getSubtitle() == null ? goalEntity.getSubtitle() : updateGoalCommand.getSubtitle())
                .status(updateGoalCommand.getStatus() == null ? goalEntity.getStatus() : updateGoalCommand.getStatus())
                .endDate(updateGoalCommand.getEndDate() == null ? goalEntity.getEndDate() : updateGoalCommand.getEndDate())
                .build();
    }
}
