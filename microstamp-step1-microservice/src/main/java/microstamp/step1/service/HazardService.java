package microstamp.step1.service;

import microstamp.step1.dto.hazard.HazardInsertDto;
import microstamp.step1.dto.hazard.HazardReadDto;
import microstamp.step1.dto.hazard.HazardUpdateDto;

import java.util.List;
import java.util.UUID;

public interface HazardService {

    List<HazardReadDto> findAll();

    HazardReadDto findById(UUID id);

    List<HazardReadDto> findByAnalysisId(UUID id);

    HazardReadDto insert(HazardInsertDto hazardInsertDto);

    void update(UUID id, HazardUpdateDto hazardUpdateDto);

    void delete(UUID id);

    List<HazardReadDto> getHazardChildren(UUID id);
}
