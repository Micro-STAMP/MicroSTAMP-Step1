package microstamp.step1.mapper;

import microstamp.step1.data.SystemSafetyConstraint;
import microstamp.step1.dto.hazard.HazardReadDto;
import microstamp.step1.dto.systemsafetyconstraint.SystemSafetyConstraintInsertDto;
import microstamp.step1.dto.systemsafetyconstraint.SystemSafetyConstraintReadDto;

import java.util.Comparator;

public class SystemSafetyConstraintMapper {

    public static SystemSafetyConstraintReadDto toDto(SystemSafetyConstraint systemSafetyConstraint){
        return new SystemSafetyConstraintReadDto(systemSafetyConstraint.getId(),
                systemSafetyConstraint.getName(),
                systemSafetyConstraint.getHazardEntities() != null
                    ? systemSafetyConstraint.getHazardEntities().stream()
                        .map(HazardMapper::toDto)
                        .sorted(Comparator.comparing(HazardReadDto::getName))
                        .toList()
                    : null);
    }

    public static SystemSafetyConstraint toEntity(SystemSafetyConstraintInsertDto systemSafetyConstraintInsertDto){
        return new SystemSafetyConstraint(systemSafetyConstraintInsertDto.getName(),
                systemSafetyConstraintInsertDto.getAnalysisId());
    }
}
