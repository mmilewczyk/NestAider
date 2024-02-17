package com.matcodem.nestaider.domain.service;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.matcodem.nestaider.infrastructure.processor.TaskProcessor;
import com.matcodem.nestaider.application.action.TaskAction;
import com.matcodem.nestaider.application.result.TaskResult;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Service for managing various operations on tasks.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class TaskProcessorExecutor {

	public static final String TASK_WITH_TASK_ID_HAS_NOT_BEEN_FOUND = "Task with taskId %s has not been found";

	/** Map storing processors for different action types. */
	private final Map<Class<?>, TaskProcessor<?, ?>> processorMap;

	/**
	 * Executes an action on a task and returns the result.
	 *
	 * @param action The action to be executed.
	 * @param <A>    The type of action.
	 * @param <R>    The type of result.
	 * @return The result of the executed action.
	 * @throws IllegalArgumentException If there is no processor for the given action type.
	 */
	@Transactional
	public <A extends TaskAction, R extends TaskResult> R processTaskAction(A action) {
		@SuppressWarnings("unchecked")
		TaskProcessor<A, R> processor = (TaskProcessor<A, R>) processorMap.get(action.getClass());
		if (processor == null) {
			throw new IllegalArgumentException("No processor found for action type: " + action.getClass().getSimpleName());
		}
		return processor.execute(action);
	}
}
