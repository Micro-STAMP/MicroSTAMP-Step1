package microstamp.step1.service.impl;

import microstamp.step1.data.Hazard;
import microstamp.step1.data.Project;
import microstamp.step1.data.SystemSafetyConstraint;
import microstamp.step1.dto.systemsafetyconstraint.SystemSafetyConstraintInsertDto;
import microstamp.step1.dto.systemsafetyconstraint.SystemSafetyConstraintReadDto;
import microstamp.step1.dto.systemsafetyconstraint.SystemSafetyConstraintUpdateDto;
import microstamp.step1.exception.Step1NotFoundException;
import microstamp.step1.mapper.SystemSafetyConstraintMapper;
import microstamp.step1.repository.HazardRepository;
import microstamp.step1.repository.ProjectRepository;
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
    private ProjectRepository projectRepository;

    public List<SystemSafetyConstraintReadDto> findAll() {
        return systemSafetyConstraintRepository.findAll().stream()
                .map(SystemSafetyConstraintMapper::toDto)
                .sorted(Comparator.comparing(SystemSafetyConstraintReadDto::getName))
                .toList();
    }

    public SystemSafetyConstraintReadDto findById(UUID id) throws Step1NotFoundException {
        return SystemSafetyConstraintMapper.toDto(systemSafetyConstraintRepository.findById(id)
                .orElseThrow(() -> new Step1NotFoundException("SystemSafetyConstraint not found with id: " + id)));
    }

    public List<SystemSafetyConstraintReadDto> findByProjectId(UUID id) {
        return systemSafetyConstraintRepository.findByProjectId(id.toString()).stream()
                .map(SystemSafetyConstraintMapper::toDto)
                .sorted(Comparator.comparing(SystemSafetyConstraintReadDto::getName))
                .toList();
    }

    public SystemSafetyConstraintReadDto insert(SystemSafetyConstraintInsertDto systemSafetyConstraintInsertDto) throws Step1NotFoundException {
        Project project = projectRepository.findById(systemSafetyConstraintInsertDto.getProjectId())
                .orElseThrow(() -> new Step1NotFoundException("Project not found with id: " + systemSafetyConstraintInsertDto.getProjectId()));

        SystemSafetyConstraint systemSafetyConstraint = new SystemSafetyConstraint();
        systemSafetyConstraint.setName(systemSafetyConstraintInsertDto.getName());

        List<Hazard> hazardEntities = new ArrayList<>();
        if (systemSafetyConstraintInsertDto.getHazardsId() != null) {
            for (UUID id : systemSafetyConstraintInsertDto.getHazardsId())
                hazardEntities.add(hazardRepository.findById(id).orElseThrow(() -> new Step1NotFoundException("Hazard not found with id: " + id)));
        }
        systemSafetyConstraint.setHazardEntities(hazardEntities);

        project.getSystemSafetyConstraintEntities().add(systemSafetyConstraint);
        projectRepository.save(project);

        return SystemSafetyConstraintMapper.toDto(systemSafetyConstraint);
    }

    public void update(UUID id, SystemSafetyConstraintUpdateDto systemSafetyConstraintUpdateDto) throws Step1NotFoundException {
        SystemSafetyConstraint systemSafetyConstraint = systemSafetyConstraintRepository.findById(id)
                .orElseThrow(() -> new Step1NotFoundException("SystemSafetyConstraint not found with id: " + id));

        systemSafetyConstraint.setName(systemSafetyConstraintUpdateDto.getName());

        List<Hazard> hazardEntities = new ArrayList<>();
        if (systemSafetyConstraintUpdateDto.getHazardsId() != null) {
            for (UUID hazardId : systemSafetyConstraintUpdateDto.getHazardsId())
                hazardEntities.add(hazardRepository.findById(hazardId)
                        .orElseThrow(() -> new Step1NotFoundException("Hazard not found with id: " + hazardId)));
        }
        systemSafetyConstraint.setHazardEntities(hazardEntities);

        systemSafetyConstraintRepository.save(systemSafetyConstraint);
    }

    public void delete(UUID id) throws Step1NotFoundException {
        SystemSafetyConstraint systemSafetyConstraint = systemSafetyConstraintRepository.findById(id)
                .orElseThrow(() -> new Step1NotFoundException("SystemSafetyConstraint not found with id: " + id));
        systemSafetyConstraintRepository.deleteHazardsAssociation(systemSafetyConstraint.getId().toString());
        systemSafetyConstraintRepository.deleteById(systemSafetyConstraint.getId());
    }
}
