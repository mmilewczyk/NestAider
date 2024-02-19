package com.matcodem.nestaider.application.action.household;

import com.matcodem.nestaider.domain.model.Address;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateHouseholdAction(
		@NotBlank(message = "Name is required") String name,
		@NotBlank(message = "Avatar is required") String avatarPath,
		@Valid @NotNull Address address
) implements HouseholdAction {}
