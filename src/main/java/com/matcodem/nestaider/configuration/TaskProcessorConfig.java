package com.matcodem.nestaider.configuration;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.matcodem.nestaider.application.action.AddHouseholdTaskAction;
import com.matcodem.nestaider.application.action.CompleteTaskAction;
import com.matcodem.nestaider.application.action.DeleteTaskByIdAction;
import com.matcodem.nestaider.application.action.GetTaskByIdAction;
import com.matcodem.nestaider.application.action.UpdateTaskAction;
import com.matcodem.nestaider.infrastructure.processor.AddHouseholdTaskProcessor;
import com.matcodem.nestaider.infrastructure.processor.CompleteTaskProcessor;
import com.matcodem.nestaider.infrastructure.processor.DeleteTaskByIdProcessor;
import com.matcodem.nestaider.application.action.GetAllTasksAction;
import com.matcodem.nestaider.infrastructure.processor.GetAllTasksProcessor;
import com.matcodem.nestaider.infrastructure.processor.GetTaskByIdProcessor;
import com.matcodem.nestaider.infrastructure.processor.TaskProcessor;
import com.matcodem.nestaider.infrastructure.processor.UpdateTaskProcessor;

@Configuration
public class TaskProcessorConfig {

	@Bean
	public Map<Class<?>, TaskProcessor<?, ?>> processorMap(AddHouseholdTaskProcessor addHouseholdTaskProcessor,
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
