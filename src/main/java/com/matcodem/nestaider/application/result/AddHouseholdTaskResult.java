package com.matcodem.nestaider.application.result;

import com.matcodem.nestaider.domain.model.dto.HouseholdTask;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AddHouseholdTaskResult implements TaskResult {
	private HouseholdTask householdTask;
}
