package com.matcodem.nestaider.infrastructure.processor.household;

import org.springframework.stereotype.Service;

import com.matcodem.nestaider.application.action.household.DeleteHouseholdAction;
import com.matcodem.nestaider.application.result.household.DeleteHouseholdResult;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class DeleteHouseholdProcessor implements HouseholdProcessor<DeleteHouseholdAction, DeleteHouseholdResult> {

	@Override
	public DeleteHouseholdResult execute(DeleteHouseholdAction action) {
		return null;
	}
}
