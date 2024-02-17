package com.matcodem.nestaider.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.HashMap;
import java.util.Map;

import com.matcodem.nestaider.domain.model.FrequencyType;
import com.matcodem.nestaider.domain.strategy.DailyNextDueDateCalculator;
import com.matcodem.nestaider.domain.strategy.MonthlyNextDueDateCalculator;
import com.matcodem.nestaider.domain.strategy.NextDueDateCalculator;
import com.matcodem.nestaider.domain.strategy.WeeklyNextDueDateCalculator;

@Configuration
public class TaskCalculatorConfig {

	@Bean
	public Map<FrequencyType, NextDueDateCalculator> nextDueDateCalculators(
			DailyNextDueDateCalculator dailyCalculator,
			WeeklyNextDueDateCalculator weeklyCalculator,
			MonthlyNextDueDateCalculator monthlyCalculator) {

		Map<FrequencyType, NextDueDateCalculator> calculators = new HashMap<>();
		calculators.put(FrequencyType.DAILY, dailyCalculator);
		calculators.put(FrequencyType.WEEKLY, weeklyCalculator);
		calculators.put(FrequencyType.MONTHLY, monthlyCalculator);

		return calculators;
	}
}
