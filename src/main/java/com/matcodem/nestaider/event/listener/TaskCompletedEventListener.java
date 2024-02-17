package com.matcodem.nestaider.event.listener;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.matcodem.nestaider.domain.model.FrequencyType;
import com.matcodem.nestaider.domain.model.HouseholdTaskEntity;
import com.matcodem.nestaider.domain.model.dto.HouseholdTask;
import com.matcodem.nestaider.domain.service.TaskExecutionHistoryService;
import com.matcodem.nestaider.domain.strategy.NextDueDateCalculator;
import com.matcodem.nestaider.event.event.TaskCompletedEvent;
import com.matcodem.nestaider.infrastructure.repository.TaskRepository;
import com.matcodem.nestaider.user.model.UserEntity;
import com.matcodem.nestaider.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Listener responsible for handling {@link TaskCompletedEvent}.
 * This listener updates the status of the completed task in the repository
 * and adds a new entry to the task execution history.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class TaskCompletedEventListener {

	private final TaskExecutionHistoryService historyService;

	private final TaskRepository taskRepository;
	private final UserRepository userRepository;

	private final Map<FrequencyType, NextDueDateCalculator> nextDueDateCalculators;

	@EventListener
	public void handleTaskCompletedEvent(TaskCompletedEvent event) {
		HouseholdTask completedTask = event.getCompletedTask();
		UserEntity user = event.getUser();

		addTaskExecutionHistory(completedTask, user);
		createNextTask(completedTask, user);
	}

	private void addTaskExecutionHistory(HouseholdTask task, UserEntity user) {
		historyService.addTaskExecution(task, user, "Task completed");
		log.info("Task execution history added for task {}.", task.getId());
	}

	private void createNextTask(HouseholdTask completedTask, UserEntity user) {
		FrequencyType frequency = completedTask.getFrequency();
		if (frequency == null) {
			log.warn("Task does not have a frequency specified. No new task will be created.");
			return;
		}

		if (frequency == FrequencyType.ONCE) {
			log.info("Task is one-time only. No new task will be created.");
			return;
		}

		NextDueDateCalculator calculator = nextDueDateCalculators.get(frequency);
		if (calculator == null) {
			log.error("No calculator found for frequency type: {}", frequency);
			return;
		}

		LocalDateTime nextDueDate = calculator.calculateNextDueDate(completedTask.getLastDueDate());
		HouseholdTaskEntity nextTask = createNextTaskInstance(completedTask, nextDueDate, user);
		taskRepository.save(nextTask);
	}

	private HouseholdTaskEntity createNextTaskInstance(HouseholdTask completedTask, LocalDateTime nextDueDate, UserEntity user) {
		HouseholdTaskEntity nextTask = new HouseholdTaskEntity();
		nextTask.setName(completedTask.getName());
		nextTask.setFrequency(completedTask.getFrequency());
		nextTask.setDueDate(nextDueDate);

		if (completedTask.isAssignToNextMember()) {
			assignTaskToNextMember(nextTask);
		} else {
			nextTask.setAssignedUser(user);
		}

		return nextTask;
	}

	private void assignTaskToNextMember(HouseholdTaskEntity task) {
		List<UserEntity> householdMembers = userRepository.findAll();
		int lastIndex = householdMembers.indexOf(task.getAssignedUser());
		int nextIndex = (lastIndex + 1) % householdMembers.size();
		UserEntity nextUser = householdMembers.get(nextIndex);
		task.setAssignedUser(nextUser);
	}
}
