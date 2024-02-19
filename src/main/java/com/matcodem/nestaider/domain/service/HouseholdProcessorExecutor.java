package com.matcodem.nestaider.domain.service;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.matcodem.nestaider.application.action.household.HouseholdAction;
import com.matcodem.nestaider.application.result.household.HouseholdResult;
import com.matcodem.nestaider.infrastructure.processor.household.HouseholdProcessor;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HouseholdProcessorExecutor {

	public static final String HOUSEHOLD_WITH_HOUSEHOLD_ID_HAS_NOT_BEEN_FOUND = "Household with householdId %s has not been found";

	/** Map storing processors for different action types. */
	private final Map<Class<?>, HouseholdProcessor<?, ?>> householdProcessorMap;

	/**
	 * Executes an action on a household and returns the result.
	 *
	 * @param action The action to be executed.
	 * @param <A>    The type of action.
	 * @param <R>    The type of result.
	 * @return The result of the executed action.
	 * @throws IllegalArgumentException If there is no processor for the given action type.
	 */
	@Transactional
	public <A extends HouseholdAction, R extends HouseholdResult> R processHouseholdAction(A action) {
		@SuppressWarnings("unchecked")
		HouseholdProcessor<A, R> processor = (HouseholdProcessor<A, R>) householdProcessorMap.get(action.getClass());
		if (processor == null) {
			throw new IllegalArgumentException("No processor found for action type: " + action.getClass().getSimpleName());
		}
		return processor.execute(action);
	}
}
