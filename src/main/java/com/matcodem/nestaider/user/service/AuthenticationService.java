package com.matcodem.nestaider.user.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.matcodem.nestaider.user.model.UserEntity;
import com.matcodem.nestaider.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

	private final UserRepository userRepository;

	public UserEntity getCurrentUser() {
		String username = getCurrentUsername();
		return userRepository.findByUsername(username);
	}

	public String getCurrentUsername() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null && authentication.isAuthenticated()) {
			Object principal = authentication.getPrincipal();
			if (principal instanceof UserDetails) {
				return ((UserDetails) principal).getUsername();
			} else if (principal instanceof String) {
				return (String) principal;
			}
		}
		return null;
	}

}