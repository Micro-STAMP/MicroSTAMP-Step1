package microstamp.step1.service;

import microstamp.step1.dto.assumption.AssumptionInsertDto;
import microstamp.step1.dto.assumption.AssumptionReadDto;
import microstamp.step1.dto.assumption.AssumptionUpdateDto;

import java.util.List;
import java.util.UUID;

public interface AssumptionService {

    List<AssumptionReadDto> findAll();

    AssumptionReadDto findById(UUID id);

    List<AssumptionReadDto> findByAnalysisId(UUID id);

    AssumptionReadDto insert(AssumptionInsertDto assumptionInsertDto);

    void update(UUID id, AssumptionUpdateDto assumptionUpdateDto);

    void delete(UUID id);

}
