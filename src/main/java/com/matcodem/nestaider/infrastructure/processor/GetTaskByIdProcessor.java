package com.matcodem.nestaider.infrastructure.processor;

import org.springframework.stereotype.Service;

import com.matcodem.nestaider.domain.service.TaskProcessorExecutor;
import com.matcodem.nestaider.exception.TaskNotFoundException;
import com.matcodem.nestaider.application.action.GetTaskByIdAction;
import com.matcodem.nestaider.infrastructure.mapper.HouseholdTaskMapper;
import com.matcodem.nestaider.domain.model.HouseholdTaskEntity;
import com.matcodem.nestaider.infrastructure.repository.TaskRepository;
import com.matcodem.nestaider.application.result.GetTaskByIdResult;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GetTaskByIdProcessor implements TaskProcessor<GetTaskByIdAction, GetTaskByIdResult> {

	private final TaskRepository taskRepository;

	@Override
	public GetTaskByIdResult execute(GetTaskByIdAction action) {
		HouseholdTaskEntity householdTaskEntity = taskRepository.findById(action.taskId())
				.orElseThrow(() -> new TaskNotFoundException(TaskProcessorExecutor.TASK_WITH_TASK_ID_HAS_NOT_BEEN_FOUND.formatted(action.taskId())));
		return new GetTaskByIdResult(HouseholdTaskMapper.mapToHouseholdTask(householdTaskEntity));
	}
}
