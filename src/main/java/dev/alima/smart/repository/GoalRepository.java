package dev.alima.smart.repository;

import dev.alima.smart.domain.entity.GoalEntity;
import dev.alima.smart.domain.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoalRepository extends JpaRepository<GoalEntity, Long> {
    List<GoalEntity> findByTitle(String title);
}
