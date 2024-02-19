package com.matcodem.nestaider.configuration;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.matcodem.nestaider.application.action.task.AddHouseholdTaskAction;
import com.matcodem.nestaider.application.action.task.CompleteTaskAction;
import com.matcodem.nestaider.application.action.task.DeleteTaskByIdAction;
import com.matcodem.nestaider.application.action.task.GetTaskByIdAction;
import com.matcodem.nestaider.application.action.task.UpdateTaskAction;
import com.matcodem.nestaider.infrastructure.processor.task.AddHouseholdTaskProcessor;
import com.matcodem.nestaider.infrastructure.processor.task.CompleteTaskProcessor;
import com.matcodem.nestaider.infrastructure.processor.task.DeleteTaskByIdProcessor;
import com.matcodem.nestaider.application.action.task.GetAllTasksAction;
import com.matcodem.nestaider.infrastructure.processor.task.GetAllTasksProcessor;
import com.matcodem.nestaider.infrastructure.processor.task.GetTaskByIdProcessor;
import com.matcodem.nestaider.infrastructure.processor.task.TaskProcessor;
import com.matcodem.nestaider.infrastructure.processor.task.UpdateTaskProcessor;

@Configuration
public class TaskProcessorConfig {

	@Bean
	public Map<Class<?>, TaskProcessor<?, ?>> taskProcessorMap(AddHouseholdTaskProcessor addHouseholdTaskProcessor,
	                                                           CompleteTaskProcessor completeTaskProcessor,
	                                                           DeleteTaskByIdProcessor deleteTaskByIdProcessor,
	                                                           GetAllTasksProcessor getAllTasksProcessor,
	                                                           GetTaskByIdProcessor getTaskByIdProcessor,
	                                                           UpdateTaskProcessor updateTaskProcessor) {
		Map<Class<?>, TaskProcessor<?, ?>> processorMap = new HashMap<>();
		processorMap.put(AddHouseholdTaskAction.class, addHouseholdTaskProcessor);
		processorMap.put(CompleteTaskAction.class, completeTaskProcessor);
		processorMap.put(DeleteTaskByIdAction.class, deleteTaskByIdProcessor);
		processorMap.put(GetAllTasksAction.class, getAllTasksProcessor);
		processorMap.put(GetTaskByIdAction.class, getTaskByIdProcessor);
		processorMap.put(UpdateTaskAction.class, updateTaskProcessor);
		return processorMap;
	}
}
