package com.matcodem.nestaider.infrastructure.processor.task;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.matcodem.nestaider.application.action.task.GetAllTasksAction;
import com.matcodem.nestaider.infrastructure.mapper.HouseholdTaskMapper;
import com.matcodem.nestaider.domain.model.HouseholdTaskEntity;
import com.matcodem.nestaider.domain.model.dto.HouseholdTask;
import com.matcodem.nestaider.infrastructure.repository.TaskRepository;
import com.matcodem.nestaider.application.result.task.GetAllTasksResult;

import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GetAllTasksProcessor implements TaskProcessor<GetAllTasksAction, GetAllTasksResult> {

	private final TaskRepository taskRepository;

	@Override
	public GetAllTasksResult execute(GetAllTasksAction action) {
		Sort sort = createSort(action.sortOrder(), action.sortBy());
		PageRequest pageable = PageRequest.of(action.page(), action.size(), sort);
		Specification<HouseholdTaskEntity> spec = createSpecification(action.taskFilter());
		Page<HouseholdTaskEntity> page = taskRepository.findAll(spec, pageable);
		List<HouseholdTask> householdTasks = page.getContent().stream()
				.filter(Objects::nonNull)
				.map(HouseholdTaskMapper::mapToHouseholdTask)
				.toList();
		return new GetAllTasksResult(new PageImpl<>(householdTasks, pageable, page.getTotalElements()));
	}

	private Sort createSort(String sortOrder, String sortBy) {
		Sort.Direction direction = sortOrder.equals(Sort.Direction.ASC.name().toLowerCase()) ? Sort.Direction.ASC : Sort.Direction.DESC;
		return Sort.by(direction, sortBy);
	}

	private Specification<HouseholdTaskEntity> createSpecification(GetAllTasksAction.TaskFilter taskFilter) {
		return (root, query, cb) -> {
			List<Predicate> predicates = new ArrayList<>();
			if (taskFilter.name() != null) {
				predicates.add(cb.like(root.get("name"), "%" + taskFilter.name() + "%"));
			}
			if (taskFilter.dueDate() != null) {
				predicates.add(cb.equal(root.get("dueDate"), taskFilter.dueDate()));
			}
			if (taskFilter.completed() != null) {
				predicates.add(cb.equal(root.get("completed"), taskFilter.completed()));
			}
			return cb.and(predicates.toArray(new Predicate[0]));
		};
	}
}
