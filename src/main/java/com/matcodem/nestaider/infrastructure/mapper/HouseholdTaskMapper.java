package com.matcodem.nestaider.infrastructure.mapper;

import com.matcodem.nestaider.domain.model.HouseholdTaskEntity;
import com.matcodem.nestaider.domain.model.dto.HouseholdTask;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class HouseholdTaskMapper {

	public static HouseholdTask mapToHouseholdTask(HouseholdTaskEntity entity) {
		return HouseholdTask.builder()
				.id(entity.getId())
				.name(entity.getName())
				.description(entity.getDescription())
				.completed(entity.isCompleted())
				.dueDate(entity.getDueDate())
				.lastDueDate(entity.getLastDueDate())
				.assignToNextMember(entity.isAssignToNextMember())
				.frequency(entity.getFrequency())
				.householdId(entity.getHousehold() != null ? entity.getHousehold().getId() : null)
				.assignedUserId(entity.getAssignedUser() != null ? entity.getAssignedUser().getId() : null)
				.build();
	}
}
