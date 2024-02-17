package com.matcodem.nestaider.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.matcodem.nestaider.domain.model.TaskExecutionHistoryEntity;

@Repository
public interface TaskExecutionHistoryRepository extends JpaRepository<TaskExecutionHistoryEntity, Long> {
}

