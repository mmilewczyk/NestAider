package com.matcodem.nestaider.event.event;

import org.springframework.context.ApplicationEvent;

import com.matcodem.nestaider.domain.model.dto.HouseholdTask;

import lombok.Getter;

@Getter
public class TaskAddedEvent extends ApplicationEvent {

	private final HouseholdTask householdTask;

	public TaskAddedEvent(Object source, HouseholdTask householdTask) {
		super(source);
		this.householdTask = householdTask;
	}
}
