package com.matcodem.nestaider.infrastructure.processor.household;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.matcodem.nestaider.application.action.household.CreateHouseholdAction;
import com.matcodem.nestaider.application.result.household.CreateHouseholdResult;
import com.matcodem.nestaider.domain.model.HouseholdEntity;
import com.matcodem.nestaider.domain.model.UserEntity;
import com.matcodem.nestaider.infrastructure.repository.HouseholdRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreateHouseholdProcessor implements HouseholdProcessor<CreateHouseholdAction, CreateHouseholdResult> {

	private final HouseholdRepository householdRepository;

	@Transactional
	@Override
	public CreateHouseholdResult execute(CreateHouseholdAction action) {
		HouseholdEntity household = new HouseholdEntity();
		household.setName(action.name());
		household.setAvatarPath(action.avatarPath());
		household.setAddress(action.address());

		// TODO: Get current user
//		UserEntity creator = new UserEntity();
//		household.getMembers().add(creator);

		HouseholdEntity savedHousehold = householdRepository.save(household);

		log.info("Created household with id {}", savedHousehold.getId());

		return new CreateHouseholdResult(savedHousehold.getId());
	}
}
