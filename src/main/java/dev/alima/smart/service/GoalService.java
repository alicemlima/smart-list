package dev.alima.smart.service;

import dev.alima.smart.domain.dto.command.GoalCommand;
import dev.alima.smart.domain.dto.command.UpdateGoalCommand;
import dev.alima.smart.domain.dto.response.GoalResponse;

import java.util.List;

public interface GoalService {
    List<GoalResponse> findAll();

    GoalResponse findById(Long id);

    List<GoalResponse> findByTitle(String title);

    void update(UpdateGoalCommand updateGoalCommand);

    void delete(long id);

    void save(GoalCommand goalCommand);
}
