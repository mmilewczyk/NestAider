package com.matcodem.nestaider.infrastructure.processor.household;

import org.springframework.stereotype.Service;

import com.matcodem.nestaider.application.action.household.UpdateHouseholdAction;
import com.matcodem.nestaider.application.result.household.UpdateHouseholdResult;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class UpdateHouseholdProcessor implements HouseholdProcessor<UpdateHouseholdAction, UpdateHouseholdResult> {
	@Override
	public UpdateHouseholdResult execute(UpdateHouseholdAction action) {
		return null;
	}
}
