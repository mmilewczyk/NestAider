package com.matcodem.nestaider.infrastructure.processor.household;

public interface HouseholdProcessor<T, R> {
	R execute(T action);
}
