package microstamp.step1.service.impl;

import microstamp.step1.client.MicroStampClient;
import microstamp.step1.entity.Hazard;
import microstamp.step1.entity.SystemSafetyConstraint;
import microstamp.step1.dto.systemsafetyconstraint.SystemSafetyConstraintInsertDto;
import microstamp.step1.dto.systemsafetyconstraint.SystemSafetyConstraintReadDto;
import microstamp.step1.dto.systemsafetyconstraint.SystemSafetyConstraintUpdateDto;
import microstamp.step1.exception.Step1NotFoundException;
import microstamp.step1.mapper.SystemSafetyConstraintMapper;
import microstamp.step1.repository.HazardRepository;
import microstamp.step1.repository.SystemSafetyConstraintRepository;
import microstamp.step1.service.SystemSafetyConstraintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@Component
public class SystemSafetyConstraintServiceImpl implements SystemSafetyConstraintService {

    @Autowired
    private SystemSafetyConstraintRepository systemSafetyConstraintRepository;

    @Autowired
    private HazardRepository hazardRepository;

    @Autowired
    private MicroStampClient microStampClient;

    public List<SystemSafetyConstraintReadDto> findAll() {
        return systemSafetyConstraintRepository.findAll().stream()
                .map(SystemSafetyConstraintMapper::toDto)
                .sorted(Comparator.comparing(SystemSafetyConstraintReadDto::getName))
                .toList();
    }

    public SystemSafetyConstraintReadDto findById(UUID id) throws Step1NotFoundException {
        return SystemSafetyConstraintMapper.toDto(systemSafetyConstraintRepository.findById(id)
                .orElseThrow(() -> new Step1NotFoundException("SystemSafetyConstraint", id.toString())));
    }

    public List<SystemSafetyConstraintReadDto> findByAnalysisId(UUID id) {
        return systemSafetyConstraintRepository.findByAnalysisId(id).stream()
                .map(SystemSafetyConstraintMapper::toDto)
                .sorted(Comparator.comparing(SystemSafetyConstraintReadDto::getName))
                .toList();
    }

    public SystemSafetyConstraintReadDto insert(SystemSafetyConstraintInsertDto systemSafetyConstraintInsertDto) throws Step1NotFoundException {
        microStampClient.getAnalysisById(systemSafetyConstraintInsertDto.getAnalysisId());

        SystemSafetyConstraint systemSafetyConstraint = SystemSafetyConstraintMapper.toEntity(systemSafetyConstraintInsertDto);

        List<Hazard> hazardEntities = new ArrayList<>();
        if (systemSafetyConstraintInsertDto.getHazardsId() != null) {
            for (UUID id : systemSafetyConstraintInsertDto.getHazardsId())
                hazardEntities.add(hazardRepository.findById(id).orElseThrow(() -> new Step1NotFoundException("Hazard", id.toString())));
        }
        systemSafetyConstraint.setHazardEntities(hazardEntities);

        systemSafetyConstraintRepository.save(systemSafetyConstraint);

        return SystemSafetyConstraintMapper.toDto(systemSafetyConstraint);
    }

    public void update(UUID id, SystemSafetyConstraintUpdateDto systemSafetyConstraintUpdateDto) throws Step1NotFoundException {
        SystemSafetyConstraint systemSafetyConstraint = systemSafetyConstraintRepository.findById(id)
                .orElseThrow(() -> new Step1NotFoundException("SystemSafetyConstraint", id.toString()));

        systemSafetyConstraint.setName(systemSafetyConstraintUpdateDto.getName());

        List<Hazard> hazardEntities = new ArrayList<>();
        if (systemSafetyConstraintUpdateDto.getHazardsId() != null) {
            for (UUID hazardId : systemSafetyConstraintUpdateDto.getHazardsId())
                hazardEntities.add(hazardRepository.findById(hazardId)
                        .orElseThrow(() -> new Step1NotFoundException("Hazard", hazardId.toString())));
        }
        systemSafetyConstraint.setHazardEntities(hazardEntities);

        systemSafetyConstraintRepository.save(systemSafetyConstraint);
    }

    public void delete(UUID id) throws Step1NotFoundException {
        SystemSafetyConstraint systemSafetyConstraint = systemSafetyConstraintRepository.findById(id)
                .orElseThrow(() -> new Step1NotFoundException("SystemSafetyConstraint", id.toString()));
        systemSafetyConstraintRepository.deleteHazardsAssociation(systemSafetyConstraint.getId().toString());
        systemSafetyConstraintRepository.deleteById(systemSafetyConstraint.getId());
    }
}
