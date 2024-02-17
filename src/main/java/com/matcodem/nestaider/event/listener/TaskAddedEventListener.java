package com.matcodem.nestaider.event.listener;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.matcodem.nestaider.event.event.TaskAddedEvent;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class TaskAddedEventListener {

	@EventListener
	public void handleHouseholdTaskEvent(TaskAddedEvent event) {
		// TODO: Push notification to all the household's members
	}
}
