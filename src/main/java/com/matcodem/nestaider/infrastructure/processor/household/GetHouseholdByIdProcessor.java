package com.matcodem.nestaider.infrastructure.processor.household;

import org.springframework.stereotype.Service;

import com.matcodem.nestaider.application.action.household.GetHouseholdByIdAction;
import com.matcodem.nestaider.application.result.household.GetHouseholdByIdResult;
import com.matcodem.nestaider.domain.model.HouseholdEntity;
import com.matcodem.nestaider.domain.model.dto.Household;
import com.matcodem.nestaider.infrastructure.mapper.HouseholdMapper;
import com.matcodem.nestaider.infrastructure.repository.HouseholdRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class GetHouseholdByIdProcessor implements HouseholdProcessor<GetHouseholdByIdAction, GetHouseholdByIdResult> {

	private final HouseholdRepository householdRepository;

	@Override
	public GetHouseholdByIdResult execute(GetHouseholdByIdAction action) {
		HouseholdEntity householdEntity = householdRepository.findById(action.householdId())
				.orElseThrow(() -> new EntityNotFoundException("Household not found with id: " + action.householdId()));
		return new GetHouseholdByIdResult(HouseholdMapper.mapToHousehold(householdEntity));
	}
}
