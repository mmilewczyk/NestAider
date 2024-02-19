package com.matcodem.nestaider.application.action.task;

import java.time.LocalDateTime;

public record GetAllTasksAction(int page,
                                int size,
                                String sortBy,
                                String sortOrder,
                                TaskFilter taskFilter) implements TaskAction {

	public record TaskFilter(String name,
	                  LocalDateTime dueDate,
	                  Boolean completed) {
	}
}
