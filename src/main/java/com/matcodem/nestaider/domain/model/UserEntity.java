package com.matcodem.nestaider.domain.model;

import jakarta.persistence.*;
import lombok.Getter;

import com.matcodem.nestaider.domain.model.HouseholdEntity;

@Entity
@Getter
public class UserEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String username;

	private String email;


	@ManyToOne
	@JoinColumn(name = "household_id")
	private HouseholdEntity household;

	public UserEntity() {
	}

	public UserEntity(String username, String email) {
		this.username = username;
		this.email = email;
	}
}
