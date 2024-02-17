package com.matcodem.nestaider.domain.service;

import static com.matcodem.nestaider.domain.service.TaskProcessorExecutor.TASK_WITH_TASK_ID_HAS_NOT_BEEN_FOUND;

import java.time.LocalDateTime;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.matcodem.nestaider.domain.model.HouseholdTaskEntity;
import com.matcodem.nestaider.domain.model.TaskExecutionHistoryEntity;
import com.matcodem.nestaider.domain.model.dto.HouseholdTask;
import com.matcodem.nestaider.exception.TaskNotFoundException;
import com.matcodem.nestaider.infrastructure.repository.TaskExecutionHistoryRepository;
import com.matcodem.nestaider.infrastructure.repository.TaskRepository;
import com.matcodem.nestaider.user.model.UserEntity;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Service responsible for managing task execution history.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class TaskExecutionHistoryService {

	private final TaskExecutionHistoryRepository historyRepository;
	private final TaskRepository taskRepository;

	/**
	 * Adds a new entry to the task execution history.
	 *
	 * @param task    The household task for which the execution history is added.
	 * @param user    The user who executed the task.
	 * @param comment Additional comment for the task execution.
	 */
	public void addTaskExecution(HouseholdTask task, UserEntity user, String comment) {
		TaskExecutionHistoryEntity historyEntry = new TaskExecutionHistoryEntity();
		HouseholdTaskEntity householdTaskEntity = taskRepository.findById(task.getId())
				.orElseThrow(() -> new TaskNotFoundException(TASK_WITH_TASK_ID_HAS_NOT_BEEN_FOUND.formatted(task.getId())));
		historyEntry.setTask(householdTaskEntity);
		historyEntry.setUser(user);
		historyEntry.setExecutionTime(LocalDateTime.now());
		historyEntry.setComment(comment);

		try {
			historyRepository.save(historyEntry);
			log.info("Task execution history added for task {}.", task.getId());
		} catch (DataAccessException e) {
			log.error("Error occurred while saving task execution history for task {}: {}", task.getId(), e.getMessage());
		}
	}
}
