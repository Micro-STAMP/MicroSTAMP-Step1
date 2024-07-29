package microstamp.step1.service;

import microstamp.step1.dto.assumption.AssumptionReadDto;
import microstamp.step1.dto.hazard.HazardReadDto;
import microstamp.step1.dto.loss.LossReadDto;
import microstamp.step1.dto.project.ProjectReadDto;
import microstamp.step1.dto.systemgoal.SystemGoalReadDto;
import microstamp.step1.dto.systemsafetyconstraint.SystemSafetyConstraintReadDto;

import java.util.List;
import java.util.UUID;

public interface GuestService {

    List<ProjectReadDto> findProjects();

    List<SystemGoalReadDto> findSystemGoalsByProjectId(UUID id);

    List<AssumptionReadDto> findAssumptionsByProjectId(UUID id);

    List<LossReadDto> findLossesByProjectId(UUID id);

    List<HazardReadDto> findHazardsByProjectId(UUID id);

    List<SystemSafetyConstraintReadDto> findSystemSafetyConstraintsByProjectId(UUID id);

}
