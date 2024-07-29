package microstamp.step1.dto.project;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import microstamp.step1.dto.assumption.AssumptionReadDto;
import microstamp.step1.dto.hazard.HazardReadDto;
import microstamp.step1.dto.loss.LossReadDto;
import microstamp.step1.dto.systemgoal.SystemGoalReadDto;
import microstamp.step1.dto.systemsafetyconstraint.SystemSafetyConstraintReadDto;

import java.util.List;
import java.util.UUID;

@Data
public class ProjectReadDto {

    @NotNull
    private UUID id;

    @NotBlank
    private String name;

    private String description;

    private String url;

    private String type;

    private List<SystemGoalReadDto> systemGoals;

    private List<AssumptionReadDto> assumptions;

    private List<LossReadDto> losses;

    private List<HazardReadDto> hazards;

    private List<SystemSafetyConstraintReadDto> systemSafetyConstraints;

    public ProjectReadDto(UUID id, String name, String description, String url, String type, List<SystemGoalReadDto> systemGoals, List<AssumptionReadDto> assumptions, List<LossReadDto> losses, List<HazardReadDto> hazards, List<SystemSafetyConstraintReadDto> systemSafetyConstraints) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.url = url;
        this.type = type;
        this.systemGoals = systemGoals;
        this.assumptions = assumptions;
        this.losses = losses;
        this.hazards = hazards;
        this.systemSafetyConstraints = systemSafetyConstraints;
    }
}
