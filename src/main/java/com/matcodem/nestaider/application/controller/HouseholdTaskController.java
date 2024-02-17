package com.matcodem.nestaider.application.controller;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.matcodem.nestaider.application.action.AddHouseholdTaskAction;
import com.matcodem.nestaider.application.action.CompleteTaskAction;
import com.matcodem.nestaider.application.action.DeleteTaskByIdAction;
import com.matcodem.nestaider.application.action.GetTaskByIdAction;
import com.matcodem.nestaider.application.action.UpdateTaskAction;
import com.matcodem.nestaider.application.result.AddHouseholdTaskResult;
import com.matcodem.nestaider.application.result.CompleteTaskResult;
import com.matcodem.nestaider.application.result.DeleteTaskByIdResult;
import com.matcodem.nestaider.application.result.GetAllTasksResult;
import com.matcodem.nestaider.application.result.GetTaskByIdResult;
import com.matcodem.nestaider.application.result.UpdateTaskResult;
import com.matcodem.nestaider.application.action.GetAllTasksAction;
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

	@GetMapping("/search")
	public ResponseEntity<GetTaskByIdResult> getTaskById(@RequestBody GetTaskByIdAction action) {
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

