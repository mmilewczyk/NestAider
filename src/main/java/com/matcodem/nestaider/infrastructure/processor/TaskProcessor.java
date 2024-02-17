package com.matcodem.nestaider.infrastructure.processor;

public interface TaskProcessor<T, R> {
	R execute(T action);
}

