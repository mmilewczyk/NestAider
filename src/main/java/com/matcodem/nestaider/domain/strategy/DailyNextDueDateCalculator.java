package com.matcodem.nestaider.domain.strategy;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

@Component
public class DailyNextDueDateCalculator implements NextDueDateCalculator {
	@Override
	public LocalDateTime calculateNextDueDate(LocalDateTime lastDueDate) {
		return lastDueDate.plusDays(1);
	}
}

