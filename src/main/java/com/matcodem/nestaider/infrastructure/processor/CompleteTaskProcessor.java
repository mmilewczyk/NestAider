package com.matcodem.nestaider.infrastructure.processor;

import static com.matcodem.nestaider.domain.service.TaskProcessorExecutor.TASK_WITH_TASK_ID_HAS_NOT_BEEN_FOUND;

import java.time.LocalDateTime;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.matcodem.nestaider.application.action.CompleteTaskAction;
import com.matcodem.nestaider.application.result.CompleteTaskResult;
import com.matcodem.nestaider.domain.model.HouseholdTaskEntity;
import com.matcodem.nestaider.domain.model.dto.HouseholdTask;
import com.matcodem.nestaider.event.event.TaskCompletedEvent;
import com.matcodem.nestaider.exception.EventPublishingException;
import com.matcodem.nestaider.exception.TaskAlreadyCompletedException;
import com.matcodem.nestaider.exception.TaskNotFoundException;
import com.matcodem.nestaider.infrastructure.mapper.HouseholdTaskMapper;
import com.matcodem.nestaider.infrastructure.repository.TaskRepository;
import com.matcodem.nestaider.user.model.UserEntity;
import com.matcodem.nestaider.user.service.AuthenticationService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class CompleteTaskProcessor implements TaskProcessor<CompleteTaskAction, CompleteTaskResult> {
	private final TaskRepository taskRepository;
	private final AuthenticationService authenticationService;
	private final ApplicationEventPublisher eventPublisher;

	@Override
	public CompleteTaskResult execute(CompleteTaskAction action) {
		HouseholdTaskEntity householdTaskEntity = taskRepository.findById(action.taskId())
				.orElseThrow(() -> new TaskNotFoundException(TASK_WITH_TASK_ID_HAS_NOT_BEEN_FOUND.formatted(action.taskId())));

		if (householdTaskEntity.isCompleted()) {
			throw new TaskAlreadyCompletedException("Task is already completed");
		}

		UserEntity user = authenticationService.getCurrentUser();

		markTaskAsCompleted(householdTaskEntity, user);

		HouseholdTask householdTask = HouseholdTaskMapper.mapToHouseholdTask(householdTaskEntity);

		publishTaskCompletedEvent(householdTask, user);

		return new CompleteTaskResult(householdTask);
	}

	private void markTaskAsCompleted(HouseholdTaskEntity task, UserEntity user) {
		task.setCompleted(true);
		task.setLastDueDate(LocalDateTime.now());

		try {
			taskRepository.save(task);
			log.info("Task {} marked as completed by user {}.", task.getId(), user.getUsername());
		} catch (DataAccessException e) {
			log.error("Error occurred while saving task {} to repository: {}", task.getId(), e.getMessage());
			throw new RuntimeException("Error while saving task to repository", e);
		}
	}

	private void publishTaskCompletedEvent(HouseholdTask householdTask, UserEntity user) {
		try {
			eventPublisher.publishEvent(new TaskCompletedEvent(this, householdTask, user));
		} catch (Exception e) {
			throw new EventPublishingException("Error while publishing event", e);
		}
	}
}
