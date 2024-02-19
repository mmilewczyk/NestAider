package com.matcodem.nestaider.application.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.matcodem.nestaider.application.action.household.CreateHouseholdAction;
import com.matcodem.nestaider.application.action.household.DeleteHouseholdAction;
import com.matcodem.nestaider.application.action.household.GetHouseholdByIdAction;
import com.matcodem.nestaider.application.action.household.UpdateHouseholdAction;
import com.matcodem.nestaider.application.result.household.CreateHouseholdResult;
import com.matcodem.nestaider.application.result.household.DeleteHouseholdResult;
import com.matcodem.nestaider.application.result.household.GetHouseholdByIdResult;
import com.matcodem.nestaider.application.result.household.UpdateHouseholdResult;
import com.matcodem.nestaider.domain.service.HouseholdProcessorExecutor;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/households")
public class HouseholdController {

	private final HouseholdProcessorExecutor householdProcessorExecutor;

	@GetMapping("/{householdId}")
	public ResponseEntity<GetHouseholdByIdResult> getHousehold(@PathVariable Long householdId) {
		GetHouseholdByIdAction action = new GetHouseholdByIdAction(householdId);
		GetHouseholdByIdResult result = householdProcessorExecutor.processHouseholdAction(action);
		return ResponseEntity.ok(result);
	}

	@PostMapping
	public ResponseEntity<CreateHouseholdResult> createHousehold(@RequestBody @Valid CreateHouseholdAction action) {
		CreateHouseholdResult result = householdProcessorExecutor.processHouseholdAction(action);
		return ResponseEntity.status(HttpStatus.CREATED).body(result);
	}

	@PutMapping("/{householdId}")
	public ResponseEntity<UpdateHouseholdResult> updateHousehold(@PathVariable Long householdId, @RequestBody UpdateHouseholdAction action) {
		UpdateHouseholdResult result = householdProcessorExecutor.processHouseholdAction(action);
		return ResponseEntity.ok(result);
	}

	@DeleteMapping("/{householdId}")
	public ResponseEntity<DeleteHouseholdResult> deleteHousehold(@PathVariable Long householdId) {
		DeleteHouseholdAction action = new DeleteHouseholdAction(householdId);
		DeleteHouseholdResult result = householdProcessorExecutor.processHouseholdAction(action);
		return ResponseEntity.ok(result);
	}
}

