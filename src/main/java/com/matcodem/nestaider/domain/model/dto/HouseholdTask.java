package com.matcodem.nestaider.domain.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

import com.matcodem.nestaider.domain.model.FrequencyType;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HouseholdTask {
	private Long id;
	private String name;
	private String description;
	private boolean completed;
	private LocalDateTime dueDate;
	private LocalDateTime lastDueDate;
	private boolean assignToNextMember;
	private FrequencyType frequency;
	private Long householdId;
	private Long assignedUserId;
}

