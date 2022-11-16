package dev.alima.tasks.integration;

import dev.alima.tasks.domain.dto.command.UpdateTaskCommand;
import dev.alima.tasks.domain.dto.response.TaskResponse;
import dev.alima.tasks.domain.entity.TaskEntity;
import dev.alima.tasks.domain.values.Status;
import dev.alima.tasks.repository.TaskRepository;
import dev.alima.tasks.util.TaskMock;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
class TaskControllerIT {
    @Autowired
    private TestRestTemplate testRestTemplate;

    @LocalServerPort
    private int port;

    @Autowired
    private TaskRepository taskRepository;

    @Test
    @DisplayName("Test listAll return list of Task when Successful")
    void getTasks_ReturnListOfTasks_WhenSuccessful() {
        TaskEntity savedTask = taskRepository.save(TaskMock.createTaskToBeSaved());

        String expectedTitle = savedTask.getTitle();
        String expectedSubtitle = savedTask.getSubtitle();
        Status expectedStatus = savedTask.getStatus();

        List<TaskEntity> tasks = testRestTemplate.exchange("/tasks", HttpMethod.GET, null,
                new ParameterizedTypeReference<List<TaskEntity>>() {
                }).getBody();

        Assertions.assertThat(tasks)
                .isNotNull()
                .isNotEmpty();

        Assertions.assertThat(tasks.get(0).getTitle()).isEqualTo(expectedTitle);
        Assertions.assertThat(tasks.get(0).getSubtitle()).isEqualTo(expectedSubtitle);
        Assertions.assertThat(tasks.get(0).getStatus()).isEqualTo(expectedStatus);
    }

    @Test
    @DisplayName("Test findById return tasks when successful")
    void getTaskId_ReturnTask_WhenSuccessful() {
        TaskEntity savedTask = taskRepository.save(TaskMock.createTaskToBeSaved());

        Long expectedId = savedTask.getId();

        TaskEntity task = testRestTemplate.getForObject("/tasks/{id}", TaskEntity.class, expectedId);

        Assertions.assertThat(task).isNotNull();

        Assertions.assertThat(task.getId()).isNotNull().isEqualTo(expectedId);
    }

    @Test
    @DisplayName("Test getTaskTitle return a list of tasks when successful")
    void getTaskTitle_ReturnListOfTaskResponse_WhenSuccessful() {
        TaskEntity savedTask = taskRepository.save(TaskMock.createTaskToBeSaved());

        String expectedTitle = savedTask.getTitle();

        String url = String.format("/tasks/find?title=%s", expectedTitle);

        List<TaskEntity> tasks = testRestTemplate.exchange(url, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<TaskEntity>>() {
                }).getBody();


        Assertions.assertThat(tasks)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);

        Assertions.assertThat(tasks.get(0).getTitle()).isEqualTo(expectedTitle);
    }

    @Test
    @DisplayName("Test getTaskTitle return a empty list when tasks title is not found")
    void getTaskTitle_ReturnListOfTaskResponse_WhenTaskIsNotFound() {
        List<TaskEntity> tasks = testRestTemplate.exchange("/tasks/find?title=test", HttpMethod.GET, null,
                new ParameterizedTypeReference<List<TaskEntity>>() {
                }).getBody();


        Assertions.assertThat(tasks)
                .isNotNull()
                .isEmpty();

    }

    @Test
    @DisplayName("Test delete return task when successful")
    void delete_ReturnsOk_WhenSuccessful() {
        TaskEntity savedTask = taskRepository.save(TaskMock.createTaskToBeSaved());

        HttpStatus status = testRestTemplate.exchange("/tasks/{id}",
                HttpMethod.DELETE, null, String.class, savedTask.getId()).getStatusCode();

        Assertions.assertThat(status).isNotNull();

        Assertions.assertThat(status).isEqualTo(HttpStatus.OK);
    }
}
