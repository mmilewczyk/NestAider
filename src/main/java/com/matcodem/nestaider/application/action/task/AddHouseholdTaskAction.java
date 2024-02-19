package com.matcodem.nestaider.application.action.task;

import java.time.LocalDateTime;

import com.matcodem.nestaider.domain.model.FrequencyType;

public record AddHouseholdTaskAction(String name,
                                     String description,
                                     LocalDateTime dueDate,
                                     boolean assignToNextMember,
                                     FrequencyType frequency,
                                     Long householdId,
                                     Long assignedUserId) implements TaskAction {
}
