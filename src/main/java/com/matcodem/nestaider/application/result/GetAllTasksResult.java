package com.matcodem.nestaider.application.result;

import org.springframework.data.domain.Page;

import com.matcodem.nestaider.domain.model.dto.HouseholdTask;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GetAllTasksResult implements TaskResult {
	private Page<HouseholdTask> householdTasks;
}
