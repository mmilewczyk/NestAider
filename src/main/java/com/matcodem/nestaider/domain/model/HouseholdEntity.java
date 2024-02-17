package com.matcodem.nestaider.domain.model;

import java.util.Set;

import com.matcodem.nestaider.user.model.UserEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HouseholdEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@OneToMany(mappedBy = "household", cascade = CascadeType.ALL)
	private Set<HouseholdTaskEntity> tasks;

	@OneToMany(mappedBy = "household", cascade = CascadeType.ALL)
	private Set<UserEntity> members;

	public HouseholdEntity(String name) {
		this.name = name;
	}
}
