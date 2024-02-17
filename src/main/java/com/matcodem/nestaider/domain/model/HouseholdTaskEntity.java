package com.matcodem.nestaider.domain.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.matcodem.nestaider.user.model.UserEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
public class HouseholdTaskEntity implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private String description;

	private boolean completed;

	private LocalDateTime dueDate;

	private LocalDateTime lastDueDate;

	private boolean assignToNextMember;

	@Enumerated(EnumType.STRING)
	private FrequencyType frequency;

	@ManyToOne
	@JoinColumn(name = "household_id")
	private HouseholdEntity household;

	@ManyToOne
	private UserEntity assignedUser;
}
