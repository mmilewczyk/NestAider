package com.matcodem.nestaider.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HouseholdEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Name is required")
	private String name;

	@OneToMany(mappedBy = "household", cascade = CascadeType.ALL)
	private Set<HouseholdTaskEntity> tasks;

	@OneToMany(mappedBy = "household", cascade = CascadeType.ALL)
	private Set<UserEntity> members;

	@Column(name = "avatar_path")
	@NotBlank(message = "Avatar is required")
	private String avatarPath;

	@Embedded
	@NotNull
	private Address address;
}
