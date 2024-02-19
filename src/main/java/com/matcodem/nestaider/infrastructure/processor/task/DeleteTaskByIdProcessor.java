package com.matcodem.nestaider.infrastructure.processor.task;

import org.springframework.stereotype.Service;

import com.matcodem.nestaider.application.action.task.DeleteTaskByIdAction;
import com.matcodem.nestaider.infrastructure.repository.TaskRepository;
import com.matcodem.nestaider.application.result.task.DeleteTaskByIdResult;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DeleteTaskByIdProcessor implements TaskProcessor<DeleteTaskByIdAction, DeleteTaskByIdResult>{

	private final TaskRepository taskRepository;

	@Override
	public DeleteTaskByIdResult execute(DeleteTaskByIdAction action) {
		taskRepository.deleteById(action.taskId());
		return new DeleteTaskByIdResult();
	}
}
