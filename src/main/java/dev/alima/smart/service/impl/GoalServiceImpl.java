package dev.alima.smart.service.impl;

import dev.alima.smart.domain.dto.command.GoalCommand;
import dev.alima.smart.domain.dto.command.UpdateGoalCommand;
import dev.alima.smart.domain.dto.response.GoalResponse;
import dev.alima.smart.domain.entity.GoalEntity;
import dev.alima.smart.domain.mapper.GoalMapper;
import dev.alima.smart.exception.BadRequestException;
import dev.alima.smart.repository.GoalRepository;
import dev.alima.smart.service.GoalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class GoalServiceImpl implements GoalService {
    private final GoalRepository goalRepository;
    private final GoalMapper goalMapper;
    @Override
    public List<GoalResponse> findAll() {
        return goalRepository.findAll().stream().map(goalMapper::goalEntityToGoalResponse).toList();
    }

    @Override
    public  GoalResponse findById(Long id) {
        return goalMapper.goalEntityToGoalResponse(findByIdOrThrowsBadRequestException(id));
    }

    @Override
    public List<GoalResponse> findByTitle(String title) {
        return goalRepository.findByTitle(title).stream().map(goalMapper::goalEntityToGoalResponse).toList();
    }

    @Override
    @Transactional
    public void save(GoalCommand goalCommand) {
        try {
            goalRepository.save(goalMapper.goalCommandToGoalEntity(goalCommand));
        } catch (Exception e) {
            new BadRequestException("Error to Save a new goal");
        }
    }

    private GoalEntity findByIdOrThrowsBadRequestException(Long id){
        return goalRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Task not Found"));
    }

    @Override
    @Transactional
    public void delete(long id) {
        goalRepository.deleteById(findByIdOrThrowsBadRequestException(id).getId());
    }

    @Override
    @Transactional
    public void update(UpdateGoalCommand updateGoalCommand) {
        GoalEntity goalEntity = findByIdOrThrowsBadRequestException(updateGoalCommand.getId());
        goalRepository.save(goalMapper.updateGoalCommandToGoalEntity(updateGoalCommand, goalEntity));
    }
}
