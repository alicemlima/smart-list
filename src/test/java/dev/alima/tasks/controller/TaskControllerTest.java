package dev.alima.tasks.controller;

import dev.alima.tasks.domain.dto.command.TaskCommand;
import dev.alima.tasks.domain.dto.command.UpdateTaskCommand;
import dev.alima.tasks.domain.dto.response.TaskResponse;
import dev.alima.tasks.service.TaskService;
import dev.alima.tasks.util.TaskMock;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.List;

@ExtendWith(SpringExtension.class)
class TaskControllerTest {
    @InjectMocks
    private TaskController taskController;

    @Mock
    private TaskService taskService;

    @BeforeEach
    void setUp() {
        BDDMockito.when(taskService.findAll()).thenReturn(List.of(TaskMock.createValidTaskResponse()));

        BDDMockito.when(taskService.findById(ArgumentMatchers.anyLong()))
                .thenReturn(TaskMock.createValidTaskResponse());

        BDDMockito.when(taskService.findByTitle(ArgumentMatchers.anyString()))
                .thenReturn(List.of(TaskMock.createValidTaskResponse()));

        BDDMockito.doNothing().when(taskService).save(ArgumentMatchers.any(TaskCommand.class));
        BDDMockito.doNothing().when(taskService).update(ArgumentMatchers.any(UpdateTaskCommand.class));
        BDDMockito.doNothing().when(taskService).delete(ArgumentMatchers.anyLong());
    }

    @Test
    @DisplayName("Test listAll return list of Task when Successful")
    void getTasks_ReturnListOfTasks_WhenSuccessful() {
        String expectedTitle = TaskMock.createValidTaskEntity().getTitle();
        String expectedSubtitle = TaskMock.createValidTaskEntity().getSubtitle();

        List<TaskResponse> taskEntityList = taskController.getTasks().getBody();

        Assertions.assertThat(taskEntityList)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);

        Assertions.assertThat(taskEntityList.get(0).getTitle()).isEqualTo(expectedTitle);
        Assertions.assertThat(taskEntityList.get(0).getSubtitle()).isEqualTo(expectedSubtitle);
    }

    @Test
    @DisplayName("Test findById return tasks when successful")
    void getTaskId_ReturnTask_WhenSuccessful() {
        Long expectedId = TaskMock.createValidTaskResponse().getId();

        TaskResponse task = taskController.getTaskId(2L).getBody();

        Assertions.assertThat(task).isNotNull();

        Assertions.assertThat(task.getId()).isNotNull().isEqualTo(expectedId);
    }

    @Test
    @DisplayName("Test getTaskTitle return a list of tasks when successful")
    void getTaskTitle_ReturnListOfTaskResponse_WhenSuccessful() {
        String expectedTitle = TaskMock.createValidTaskResponse().getTitle();

        List<TaskResponse> tasks = taskController.getTaskTitle("Hello").getBody();

        Assertions.assertThat(tasks)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);

        Assertions.assertThat(tasks.get(0).getTitle()).isEqualTo(expectedTitle);
    }

    @Test
    @DisplayName("Test getTaskTitle return a empty list when tasks title is not found")
    void getTaskTitle_ReturnListOfTaskResponse_WhenTaskIsNotFound() {
        BDDMockito.when(taskService.findByTitle(ArgumentMatchers.anyString()))
                .thenReturn(Collections.emptyList());

        List<TaskResponse> tasks = taskController.getTaskTitle("Hey").getBody();

        Assertions.assertThat(tasks)
                .isNotNull()
                .isEmpty();

    }

    @Test
    @DisplayName("Test save return task when successful")
    void save_ReturnsOk_WhenSuccessful() {

        ResponseEntity<String> taskBody = taskController.save(TaskMock.createValidTaskCommand());

        Assertions.assertThat(taskBody).isEqualTo(ResponseEntity.ok().build());

    }

    @Test
    @DisplayName("Test update return task when successful")
    void update_ReturnsOk_WhenSuccessful() {

        ResponseEntity<String> taskResponse = taskController.update(TaskMock.createUpdatedTask());

        Assertions.assertThat(taskResponse).isEqualTo(ResponseEntity.ok().build());

    }

    @Test
    @DisplayName("Test delete return task when successful")
    void delete_ReturnsOk_WhenSuccessful() {
        Long expectedId = TaskMock.createValidTaskResponse().getId();

        ResponseEntity<String> taskResponse = taskController.delete(expectedId);

        Assertions.assertThat(taskResponse).isEqualTo(ResponseEntity.ok().build());
    }
}