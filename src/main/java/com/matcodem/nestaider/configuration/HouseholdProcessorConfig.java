package com.matcodem.nestaider.configuration;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.matcodem.nestaider.application.action.household.CreateHouseholdAction;
import com.matcodem.nestaider.application.action.household.DeleteHouseholdAction;
import com.matcodem.nestaider.application.action.household.GetHouseholdByIdAction;
import com.matcodem.nestaider.application.action.household.UpdateHouseholdAction;
import com.matcodem.nestaider.infrastructure.processor.household.CreateHouseholdProcessor;
import com.matcodem.nestaider.infrastructure.processor.household.DeleteHouseholdProcessor;
import com.matcodem.nestaider.infrastructure.processor.household.GetHouseholdByIdProcessor;
import com.matcodem.nestaider.infrastructure.processor.household.HouseholdProcessor;
import com.matcodem.nestaider.infrastructure.processor.household.UpdateHouseholdProcessor;

@Configuration
public class HouseholdProcessorConfig {

	@Bean
	public Map<Class<?>, HouseholdProcessor<?, ?>> householdProcessorMap(CreateHouseholdProcessor createHouseholdProcessor,
	                                                                     DeleteHouseholdProcessor deleteHouseholdProcessor,
	                                                                     GetHouseholdByIdProcessor getHouseholdByIdProcessor,
	                                                                     UpdateHouseholdProcessor updateHouseholdProcessor) {
		Map<Class<?>, HouseholdProcessor<?, ?>> processorMap = new HashMap<>();
		processorMap.put(CreateHouseholdAction.class, createHouseholdProcessor);
		processorMap.put(DeleteHouseholdAction.class, deleteHouseholdProcessor);
		processorMap.put(GetHouseholdByIdAction.class, getHouseholdByIdProcessor);
		processorMap.put(UpdateHouseholdAction.class, updateHouseholdProcessor);
		return processorMap;
	}
}
