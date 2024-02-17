package com.matcodem.nestaider.domain.model;

import java.time.LocalDateTime;

import com.matcodem.nestaider.user.model.UserEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskExecutionHistoryEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private HouseholdTaskEntity task;

	@ManyToOne
	private UserEntity user;

	private LocalDateTime executionTime;

	private String comment;
}


