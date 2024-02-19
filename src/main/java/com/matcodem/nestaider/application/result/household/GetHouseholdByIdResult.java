package com.matcodem.nestaider.application.result.household;

import com.matcodem.nestaider.domain.model.dto.Household;

public record GetHouseholdByIdResult(Household household) implements HouseholdResult {
}
