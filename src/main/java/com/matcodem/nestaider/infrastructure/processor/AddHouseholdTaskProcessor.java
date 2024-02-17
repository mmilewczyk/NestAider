package com.matcodem.nestaider.infrastructure.processor;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.matcodem.nestaider.application.action.AddHouseholdTaskAction;
import com.matcodem.nestaider.application.result.AddHouseholdTaskResult;
import com.matcodem.nestaider.domain.model.HouseholdTaskEntity;
import com.matcodem.nestaider.domain.model.dto.HouseholdTask;
import com.matcodem.nestaider.event.event.TaskAddedEvent;
import com.matcodem.nestaider.exception.EventPublishingException;
import com.matcodem.nestaider.exception.TaskNotFoundException;
import com.matcodem.nestaider.infrastructure.mapper.HouseholdTaskMapper;
import com.matcodem.nestaider.infrastructure.repository.HouseholdRepository;
import com.matcodem.nestaider.infrastructure.repository.TaskRepository;
import com.matcodem.nestaider.user.model.UserEntity;
import com.matcodem.nestaider.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class AddHouseholdTaskProcessor implements TaskProcessor<AddHouseholdTaskAction, AddHouseholdTaskResult> {
	private final TaskRepository taskRepository;
	private final UserRepository userRepository;
	private final HouseholdRepository householdRepository;
	private final ApplicationEventPublisher eventPublisher;

	@Override
	public AddHouseholdTaskResult execute(AddHouseholdTaskAction action) {
		HouseholdTaskEntity householdTaskEntity = createHouseholdTaskFromAction(action);
		saveHouseholdTask(householdTaskEntity);
		publishTaskAddedEvent(householdTaskEntity);
		return new AddHouseholdTaskResult(HouseholdTaskMapper.mapToHouseholdTask(householdTaskEntity));
	}

	private HouseholdTaskEntity createHouseholdTaskFromAction(AddHouseholdTaskAction action) {
		HouseholdTaskEntity task = new HouseholdTaskEntity();
		task.setName(action.name());
		task.setDescription(action.description());
		task.setDueDate(action.dueDate());
		task.setAssignToNextMember(action.assignToNextMember());
		task.setFrequency(action.frequency());
		setUserAndHousehold(action, task);
		return task;
	}

	private void setUserAndHousehold(AddHouseholdTaskAction action, HouseholdTaskEntity task) {
		UserEntity user = userRepository.findById(action.assignedUserId())
				.orElseThrow(() -> new TaskNotFoundException("User with id " + action.assignedUserId() + " not found"));
		task.setAssignedUser(user);

		task.setHousehold(householdRepository.findById(action.householdId())
				.orElseThrow(() -> new TaskNotFoundException("Household with id " + action.householdId() + " not found")));
	}

	private void saveHouseholdTask(HouseholdTaskEntity householdTaskEntity) {
		try {
			taskRepository.save(householdTaskEntity);
			log.info("New household task added: {}", householdTaskEntity.getName());
		} catch (DataAccessException e) {
			log.error("Error occurred while adding household task: {}", e.getMessage());
			throw new RuntimeException("Error while adding household task", e);
		}
	}

	private void publishTaskAddedEvent(HouseholdTaskEntity householdTaskEntity) {
		HouseholdTask householdTask = HouseholdTaskMapper.mapToHouseholdTask(householdTaskEntity);
		try {
			eventPublisher.publishEvent(new TaskAddedEvent(this, householdTask));
		} catch (Exception e) {
			throw new EventPublishingException("Error while publishing event", e);
		}
	}
}
