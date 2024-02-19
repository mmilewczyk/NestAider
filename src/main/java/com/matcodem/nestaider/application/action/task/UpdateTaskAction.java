package com.matcodem.nestaider.application.action.task;

import java.time.LocalDateTime;

import com.matcodem.nestaider.domain.model.FrequencyType;
import com.matcodem.nestaider.domain.model.UserEntity;

public record UpdateTaskAction(
		Long taskId,
		String name,
		String description,
		LocalDateTime dueDate,
		LocalDateTime lastDueDate,
		boolean assignToNextMember,
		FrequencyType frequency,
		UserEntity assignedUser) implements TaskAction {
}

