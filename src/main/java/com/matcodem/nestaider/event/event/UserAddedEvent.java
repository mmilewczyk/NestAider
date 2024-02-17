package com.matcodem.nestaider.event.event;

import org.springframework.context.ApplicationEvent;

import com.matcodem.nestaider.domain.model.HouseholdEntity;
import com.matcodem.nestaider.user.model.UserEntity;

import lombok.Getter;

@Getter
public class UserAddedEvent extends ApplicationEvent {

	private final HouseholdEntity householdEntity;
	private final UserEntity user;

	public UserAddedEvent(Object source, HouseholdEntity householdEntity, UserEntity user) {
		super(source);
		this.householdEntity = householdEntity;
		this.user = user;
	}
}
