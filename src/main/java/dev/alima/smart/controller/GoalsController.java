package dev.alima.smart.controller;

import dev.alima.smart.domain.dto.command.GoalCommand;
import dev.alima.smart.domain.dto.command.UpdateGoalCommand;
import dev.alima.smart.domain.dto.response.GoalResponse;
import dev.alima.smart.service.GoalService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "goal", produces = APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class GoalsController {
    private GoalService goalService;

    @Operation(summary = "Get Goals list")
    @GetMapping
    public ResponseEntity<List<GoalResponse>> getGoals() {
        return ResponseEntity.ok(goalService.findAll());
    }

    @Operation(summary = "Get goals list by title")
    @GetMapping(path = "/find")
    public ResponseEntity<List<GoalResponse>> getGoalTitle(@RequestParam(required = false) String title) {
        return ResponseEntity.ok(goalService.findByTitle(title));
    }

    @Operation(summary = "Post goal")
    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<String> save(@RequestBody @Valid GoalCommand goalCommand) {
        goalService.save(goalCommand);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Delete goal")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> delete(@PathVariable long id) {
        goalService.delete(id);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Update goal")
    @PatchMapping
    public ResponseEntity<String> update(@RequestBody @Valid UpdateGoalCommand updateGoalCommand) {
        goalService.update(updateGoalCommand);
        return ResponseEntity.ok().build();
    }
}
