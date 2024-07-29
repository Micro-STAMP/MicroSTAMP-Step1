package microstamp.step1.mapper;

import microstamp.step1.data.Project;
import microstamp.step1.dto.assumption.AssumptionReadDto;
import microstamp.step1.dto.hazard.HazardReadDto;
import microstamp.step1.dto.loss.LossReadDto;
import microstamp.step1.dto.project.ProjectReadDto;
import microstamp.step1.dto.systemgoal.SystemGoalReadDto;
import microstamp.step1.dto.systemsafetyconstraint.SystemSafetyConstraintReadDto;

import java.util.Comparator;

public class ProjectMapper {

    public static ProjectReadDto toDto(Project project){
        return new ProjectReadDto(project.getId(),
                project.getName(),
                project.getDescription(),
                project.getUrl(),
                project.getType(),
                project.getSystemGoalEntities() != null
                    ? project.getSystemGoalEntities().stream()
                        .map(SystemGoalMapper::toDto)
                        .sorted(Comparator.comparing(SystemGoalReadDto::getName))
                        .toList()
                    : null,
                project.getAssumptionEntities() != null
                    ? project.getAssumptionEntities().stream()
                        .map(AssumptionMapper::toDto)
                        .sorted(Comparator.comparing(AssumptionReadDto::getName))
                        .toList()
                    : null,
                project.getLossEntities() != null
                    ? project.getLossEntities().stream()
                        .map(LossMapper::toDto)
                        .sorted(Comparator.comparing(LossReadDto::getName))
                        .toList()
                    : null,
                project.getHazardEntities() != null
                    ? project.getHazardEntities().stream()
                        .map(HazardMapper::toDto)
                        .sorted(Comparator.comparing(HazardReadDto::getName))
                        .toList()
                    : null,
                project.getSystemSafetyConstraintEntities() != null
                    ? project.getSystemSafetyConstraintEntities().stream()
                        .map(SystemSafetyConstraintMapper::toDto)
                        .sorted(Comparator.comparing(SystemSafetyConstraintReadDto::getName))
                        .toList()
                    : null);
    }
}
