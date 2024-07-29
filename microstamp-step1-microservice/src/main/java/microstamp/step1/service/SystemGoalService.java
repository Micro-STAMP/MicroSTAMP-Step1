package microstamp.step1.service;

import microstamp.step1.dto.systemgoal.SystemGoalInsertDto;
import microstamp.step1.dto.systemgoal.SystemGoalReadDto;
import microstamp.step1.dto.systemgoal.SystemGoalUpdateDto;

import java.util.List;
import java.util.UUID;

public interface SystemGoalService {

    List<SystemGoalReadDto> findAll();

    SystemGoalReadDto findById(UUID id);

    List<SystemGoalReadDto> findByProjectId(UUID id);

    SystemGoalReadDto insert(SystemGoalInsertDto systemGoalInsertDto);

    void update(UUID id, SystemGoalUpdateDto systemGoalUpdateDto);

    void delete(UUID id);

}
