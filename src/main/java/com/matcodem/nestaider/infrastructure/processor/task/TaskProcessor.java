package com.matcodem.nestaider.infrastructure.processor.task;

public interface TaskProcessor<T, R> {
	R execute(T action);
}

