package com.matcodem.nestaider.domain.model;

public enum FrequencyType {
	DAILY("Daily"),
	WEEKLY("Weekly"),
	BIWEEKLY("Biweekly"),
	MONTHLY("Monthly"),
	QUARTERLY("Quarterly"),
	YEARLY("Yearly"),
	ONCE("Once");

	private final String displayName;

	FrequencyType(String displayName) {
		this.displayName = displayName;
	}

	public String getDisplayName() {
		return displayName;
	}
}

