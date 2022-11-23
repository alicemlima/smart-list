package dev.alima.smart.repository;

import dev.alima.smart.domain.entity.TaskEntity;
import dev.alima.smart.domain.values.Status;
import dev.alima.smart.util.TaskMock;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@DisplayName("Test for Task Repository")
class TaskRepositoryTest {
    @Autowired
    private TaskRepository taskRepository;

    @Test
    @DisplayName("Test save Task when Successful")
    void save_PersistTask_WhenSuccessful() {
        TaskEntity task = TaskMock.createTaskToBeSaved();
        TaskEntity taskSaved = this.taskRepository.save(task);
        Assertions.assertThat(taskSaved).isNotNull();
        Assertions.assertThat(taskSaved.getId()).isNotNull();
        Assertions.assertThat(taskSaved.getTitle()).isEqualTo("IMPA Project");
        Assertions.assertThat(taskSaved.getSubtitle()).isEqualTo("IMPA API");
        Assertions.assertThat(taskSaved.getStatus()).isEqualTo(Status.TODO);
    }

    @Test
    @DisplayName("Test update Task when Successful")
    void update_PersistTask_WhenSuccessful() {
        TaskEntity task = TaskMock.createTaskToBeSaved();
        TaskEntity taskSaved = this.taskRepository.save(task);

        taskSaved.setTitle("Send IMPA Project");
        taskSaved.setSubtitle("IMPA project working");
        taskSaved.setStatus(Status.PROGRESS);

        TaskEntity taskUpdated = this.taskRepository.save(taskSaved);

        Assertions.assertThat(taskUpdated).isNotNull();
        Assertions.assertThat(taskUpdated.getId()).isNotNull();
        Assertions.assertThat(taskSaved.getTitle()).isEqualTo(taskUpdated.getTitle());
        Assertions.assertThat(taskSaved.getSubtitle()).isEqualTo(taskUpdated.getSubtitle());
        Assertions.assertThat(taskSaved.getStatus()).isEqualTo(taskUpdated.getStatus());
    }

    @Test
    @DisplayName("Test delete Task when Successful")
    void delete_PersistTask_WhenSuccessful() {
        TaskEntity task = TaskMock.createTaskToBeSaved();
        TaskEntity taskSaved = this.taskRepository.save(task);

        this.taskRepository.delete(taskSaved);

        Optional<TaskEntity> taskOptional = this.taskRepository.findById(taskSaved.getId());

        Assertions.assertThat(taskOptional).isEmpty();
    }

    @Test
    @DisplayName("Test find by Title return a list of Task when Successful")
    void findByTitle_PersistTask_WhenSuccessful() {
        TaskEntity task = TaskMock.createTaskToBeSaved();
        TaskEntity taskSaved = this.taskRepository.save(task);

        String title = taskSaved.getTitle();

        List<TaskEntity> tasks = this.taskRepository.findByTitle(title);

        Assertions.assertThat(tasks).isNotEmpty().contains(taskSaved);
    }

    @Test
    @DisplayName("Test find by Title return empty list when task not found")
    void findByTitle_PersistTask_WhenNotFound() {
        List<TaskEntity> tasks = this.taskRepository.findByTitle("Test test");

        Assertions.assertThat(tasks).isEmpty();
    }

}