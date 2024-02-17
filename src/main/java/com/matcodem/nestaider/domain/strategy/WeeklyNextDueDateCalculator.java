package com.matcodem.nestaider.domain.strategy;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

@Component
public class WeeklyNextDueDateCalculator implements NextDueDateCalculator {
	@Override
	public LocalDateTime calculateNextDueDate(LocalDateTime lastDueDate) {
		return lastDueDate.plusWeeks(1);
	}
}

