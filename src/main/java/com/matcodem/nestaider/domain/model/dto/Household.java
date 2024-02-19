package com.matcodem.nestaider.domain.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

import com.matcodem.nestaider.domain.model.Address;
import com.matcodem.nestaider.domain.model.UserEntity;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Household {

	private Long id;
	private String name;
	private Set<HouseholdTask> tasks;
	private Set<UserEntity> members;
	private String avatarPath;
	private Address address;
}