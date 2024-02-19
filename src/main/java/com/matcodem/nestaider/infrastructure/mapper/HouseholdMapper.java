package com.matcodem.nestaider.infrastructure.mapper;

import java.util.HashSet;
import java.util.stream.Collectors;

import com.matcodem.nestaider.domain.model.HouseholdEntity;
import com.matcodem.nestaider.domain.model.UserEntity;
import com.matcodem.nestaider.domain.model.dto.Household;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class HouseholdMapper {

	public static Household mapToHousehold(HouseholdEntity entity) {
		if (entity == null) {
			return null;
		}
		return Household.builder()
				.id(entity.getId())
				.name(entity.getName())
				.tasks(entity.getTasks().stream()
						.map(HouseholdTaskMapper::mapToHouseholdTask)
						.collect(Collectors.toSet()))
				// TODO: IMPLEMENT
				.members(new HashSet<>(entity.getMembers()))
				.avatarPath(entity.getAvatarPath())
				.address(entity.getAddress())
				.build();
	}
}
