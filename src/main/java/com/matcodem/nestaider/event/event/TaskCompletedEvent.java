package com.matcodem.nestaider.event.event;

import org.springframework.context.ApplicationEvent;

import com.matcodem.nestaider.event.listener.TaskCompletedEventListener;
import com.matcodem.nestaider.domain.model.dto.HouseholdTask;
import com.matcodem.nestaider.user.model.UserEntity;

import lombok.Getter;

/**
 * Event triggered when a task is completed.
 * This event is fired whenever a household task is completed.
 * Listeners can handle this event to perform additional actions, such as updating task statistics or sending notifications.
 * See {@link TaskCompletedEventListener} for handling this event.
 */
@Getter
public class TaskCompletedEvent extends ApplicationEvent {
	/**
	 * The completed task.
	 */
	private final HouseholdTask completedTask;

	/**
	 * The user who completed the task.
	 */
	private final UserEntity user;

	/**
	 * Constructs a new TaskCompletedEvent.
	 *
	 * @param source        The object on which the event initially occurred.
	 * @param completedTask The completed task.
	 * @param user          The user who completed the task.
	 */
	public TaskCompletedEvent(Object source, HouseholdTask completedTask, UserEntity user) {
		super(source);
		this.completedTask = completedTask;
		this.user = user;
	}
}



