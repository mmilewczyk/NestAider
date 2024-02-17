package com.matcodem.nestaider.event.listener;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.matcodem.nestaider.event.event.UserAddedEvent;

@Component
public class UserAddedEventListener {

	@EventListener
	public void handleUserAddedEvent(UserAddedEvent event) {
	}
}
