package com.matcodem.nestaider.application.controller;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.matcodem.nestaider.application.action.task.AddHouseholdTaskAction;
import com.matcodem.nestaider.application.action.task.CompleteTaskAction;
import com.matcodem.nestaider.application.action.task.DeleteTaskByIdAction;
import com.matcodem.nestaider.application.action.task.GetTaskByIdAction;
import com.matcodem.nestaider.application.action.task.UpdateTaskAction;
import com.matcodem.nestaider.application.result.task.AddHouseholdTaskResult;
import com.matcodem.nestaider.application.result.task.CompleteTaskResult;
import com.matcodem.nestaider.application.result.task.DeleteTaskByIdResult;
import com.matcodem.nestaider.application.result.task.GetAllTasksResult;
import com.matcodem.nestaider.application.result.task.GetTaskByIdResult;
import com.matcodem.nestaider.application.result.task.UpdateTaskResult;
import com.matcodem.nestaider.application.action.task.GetAllTasksAction;
import com.matcodem.nestaider.domain.service.TaskProcessorExecutor;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor
public class HouseholdTaskController {

	private final TaskProcessorExecutor taskProcessorExecutor;

	@PostMapping
	public ResponseEntity<AddHouseholdTaskResult> addHouseholdTask(@RequestBody AddHouseholdTaskAction action) {
		AddHouseholdTaskResult result = taskProcessorExecutor.processTaskAction(action);
		return ResponseEntity.status(HttpStatus.CREATED).body(result);
	}

	@PostMapping("/complete")
	public ResponseEntity<CompleteTaskResult> completeTask(@RequestBody CompleteTaskAction action) {
		CompleteTaskResult result = taskProcessorExecutor.processTaskAction(action);
		return ResponseEntity.ok().body(result);
	}

	@GetMapping("/search/{taskId}")
	public ResponseEntity<GetTaskByIdResult> getTaskById(@PathVariable("taskId") Long taskId) {
		GetTaskByIdAction action = new GetTaskByIdAction(taskId);
		GetTaskByIdResult result = taskProcessorExecutor.processTaskAction(action);
		return ResponseEntity.ok(result);
	}

	@PutMapping
	public ResponseEntity<UpdateTaskResult> updateTask(@RequestBody UpdateTaskAction action) {
		UpdateTaskResult result = taskProcessorExecutor.processTaskAction(action);
		return ResponseEntity.ok(result);
	}


	@DeleteMapping
	public ResponseEntity<DeleteTaskByIdResult> deleteTask(@RequestBody DeleteTaskByIdAction action) {
		DeleteTaskByIdResult result = taskProcessorExecutor.processTaskAction(action);
		return ResponseEntity.ok().body(result);
	}

	@GetMapping
	public ResponseEntity<GetAllTasksResult> getAllTasks(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "1") int size,
			@RequestParam(defaultValue = "dueDate") String sortBy,
			@RequestParam(defaultValue = "asc") String sortOrder,
			@RequestParam(required = false) String name,
			@RequestParam(required = false) LocalDateTime dueDate,
			@RequestParam(required = false) Boolean completed) {

		GetAllTasksAction.TaskFilter taskFilter = new GetAllTasksAction.TaskFilter(name, dueDate, completed);
		GetAllTasksAction action = new GetAllTasksAction(page, size, sortBy, sortOrder, taskFilter);

		GetAllTasksResult result = taskProcessorExecutor.processTaskAction(action);
		return ResponseEntity.ok().body(result);
	}

}

