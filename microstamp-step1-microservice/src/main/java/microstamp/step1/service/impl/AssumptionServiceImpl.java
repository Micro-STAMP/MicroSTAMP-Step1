package microstamp.step1.service.impl;

import microstamp.step1.data.Assumption;
import microstamp.step1.dto.assumption.AssumptionInsertDto;
import microstamp.step1.dto.assumption.AssumptionReadDto;
import microstamp.step1.dto.assumption.AssumptionUpdateDto;
import microstamp.step1.exception.Step1NotFoundException;
import microstamp.step1.mapper.AssumptionMapper;
import microstamp.step1.repository.AssumptionRepository;
import microstamp.step1.service.AssumptionService;
import microstamp.step1.service.integration.MicroStampClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@Component
public class AssumptionServiceImpl implements AssumptionService {

    @Autowired
    private AssumptionRepository assumptionRepository;

    @Autowired
    private MicroStampClient microStampClient;

    public List<AssumptionReadDto> findAll() {
        return assumptionRepository.findAll().stream()
                .map(AssumptionMapper::toDto)
                .sorted(Comparator.comparing(AssumptionReadDto::getName))
                .toList();
    }

    public AssumptionReadDto findById(UUID id) throws Step1NotFoundException {
        return AssumptionMapper.toDto(assumptionRepository.findById(id)
                .orElseThrow(() -> new Step1NotFoundException("Assumption not found with id: " + id)));
    }

    public List<AssumptionReadDto> findByAnalysisId(UUID id) {
        return assumptionRepository.findByAnalysisId(id).stream()
                .map(AssumptionMapper::toDto)
                .sorted(Comparator.comparing(AssumptionReadDto::getName))
                .toList();
    }

    public AssumptionReadDto insert(AssumptionInsertDto assumptionInsertDto) throws Step1NotFoundException {
       // Project project = projectRepository.findById(assumptionInsertDto.getProjectId())
       //         .orElseThrow(() -> new Step1NotFoundException(("Project not found with id: " + assumptionInsertDto.getProjectId())));

        Assumption assumption = AssumptionMapper.toEntity(assumptionInsertDto);
        assumptionRepository.save(assumption);

        return AssumptionMapper.toDto(assumption);
    }

    public void update(UUID id, AssumptionUpdateDto assumptionUpdateDto) throws Step1NotFoundException {
        Assumption assumption = assumptionRepository.findById(id)
                .orElseThrow(() -> new Step1NotFoundException("Assumption not found with id: " + id));

        assumption.setName(assumptionUpdateDto.getName());

        assumptionRepository.save(assumption);
    }

    public void delete(UUID id) throws Step1NotFoundException {
        Assumption assumption = assumptionRepository.findById(id)
                .orElseThrow(() -> new Step1NotFoundException("Assumption not found with id: " + id));
        assumptionRepository.deleteById(assumption.getId());
    }
}