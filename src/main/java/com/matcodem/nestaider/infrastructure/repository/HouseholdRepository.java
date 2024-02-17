package com.matcodem.nestaider.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.matcodem.nestaider.domain.model.HouseholdEntity;

public interface HouseholdRepository extends JpaRepository<HouseholdEntity, Long> {
}
