package microstamp.step1.service;

import microstamp.step1.data.SystemSafetyConstraint;
import microstamp.step1.dto.systemsafetyconstraint.SystemSafetyConstraintInsertDto;
import microstamp.step1.dto.systemsafetyconstraint.SystemSafetyConstraintReadDto;
import microstamp.step1.dto.systemsafetyconstraint.SystemSafetyConstraintUpdateDto;

import java.util.List;
import java.util.UUID;

public interface SystemSafetyConstraintService {

    List<SystemSafetyConstraintReadDto> findAll();

    SystemSafetyConstraintReadDto findById(UUID id);

    List<SystemSafetyConstraintReadDto> findByProjectId(UUID id);

    SystemSafetyConstraintReadDto insert(SystemSafetyConstraintInsertDto systemSafetyConstraintInsertDto);

    void update(UUID id, SystemSafetyConstraintUpdateDto systemSafetyConstraintUpdateDto);

    void delete(UUID id);
}
