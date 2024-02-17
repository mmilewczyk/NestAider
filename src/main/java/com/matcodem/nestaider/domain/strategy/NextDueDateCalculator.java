package com.matcodem.nestaider.domain.strategy;

import java.time.LocalDateTime;

import org.springframework.stereotype.Repository;

@Repository
@FunctionalInterface
public interface NextDueDateCalculator {
	LocalDateTime calculateNextDueDate(LocalDateTime lastDueDate);
}
