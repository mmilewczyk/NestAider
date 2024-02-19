package com.matcodem.nestaider.infrastructure.processor.task;

import org.springframework.stereotype.Service;

import com.matcodem.nestaider.domain.service.TaskProcessorExecutor;
import com.matcodem.nestaider.exception.TaskNotFoundException;
import com.matcodem.nestaider.application.action.task.UpdateTaskAction;
import com.matcodem.nestaider.domain.model.HouseholdTaskEntity;
import com.matcodem.nestaider.infrastructure.repository.TaskRepository;
import com.matcodem.nestaider.application.result.task.UpdateTaskResult;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UpdateTaskProcessor implements TaskProcessor<UpdateTaskAction, UpdateTaskResult> {

	private final TaskRepository taskRepository;

	@Override
	public UpdateTaskResult execute(UpdateTaskAction action) {
		HouseholdTaskEntity existingTask = taskRepository.findById(action.taskId())
				.orElseThrow(() -> new TaskNotFoundException(TaskProcessorExecutor.TASK_WITH_TASK_ID_HAS_NOT_BEEN_FOUND.formatted(action.taskId())));

		updateTaskFields(action, existingTask);

		taskRepository.save(existingTask);
		return new UpdateTaskResult(existingTask.getId());
	}

	private void updateTaskFields(UpdateTaskAction action, HouseholdTaskEntity existingTask) {
		if (action.name() != null && !action.name().isEmpty()) {
			existingTask.setName(action.name());
		}
		if (action.description() != null) {
			existingTask.setDescription(action.description());
		}
		if (action.dueDate() != null) {
			existingTask.setDueDate(action.dueDate());
		}
		if (action.lastDueDate() != null) {
			existingTask.setLastDueDate(action.lastDueDate());
		}
		if (action.assignToNextMember() != existingTask.isAssignToNextMember()) {
			existingTask.setAssignToNextMember(action.assignToNextMember());
		}
		if (action.frequency() != null) {
			existingTask.setFrequency(action.frequency());
		}
		if (action.assignedUser() != null) {
			existingTask.setAssignedUser(action.assignedUser());
		}
	}
}
