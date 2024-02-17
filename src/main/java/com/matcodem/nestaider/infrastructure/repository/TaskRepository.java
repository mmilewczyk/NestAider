package com.matcodem.nestaider.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.matcodem.nestaider.domain.model.HouseholdTaskEntity;

@Repository
public interface TaskRepository extends JpaRepository<HouseholdTaskEntity, Long>, JpaSpecificationExecutor<HouseholdTaskEntity> {
}
