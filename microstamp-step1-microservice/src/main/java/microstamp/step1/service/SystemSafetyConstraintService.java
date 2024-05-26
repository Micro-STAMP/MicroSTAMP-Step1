package microstamp.step1.service;

import microstamp.step1.data.Hazard;
import microstamp.step1.data.Project;
import microstamp.step1.data.SystemSafetyConstraint;
import microstamp.step1.dto.SystemSafetyConstraintDto;
import microstamp.step1.exception.Step1NotFoundException;
import microstamp.step1.repository.HazardRepository;
import microstamp.step1.repository.ProjectRepository;
import microstamp.step1.repository.SystemSafetyConstraintRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SystemSafetyConstraintService {

    @Autowired
    private SystemSafetyConstraintRepository systemSafetyConstraintRepository;

    @Autowired
    private HazardRepository hazardRepository;

    @Autowired
    private ProjectRepository projectRepository;

    public List<SystemSafetyConstraint> findAll() {
        return systemSafetyConstraintRepository.findAll();
    }

    public SystemSafetyConstraint findById(Long id) throws Step1NotFoundException {
        return systemSafetyConstraintRepository.findById(id)
                .orElseThrow(() -> new Step1NotFoundException("SystemSafetyConstraint not found with id: " + id));
    }

    public List<SystemSafetyConstraint> findByProjectId(Long id) {
        return systemSafetyConstraintRepository.findByProjectId(id);
    }

    public SystemSafetyConstraint insert(SystemSafetyConstraintDto systemSafetyConstraintDto) throws Step1NotFoundException {
        SystemSafetyConstraint systemSafetyConstraint = new SystemSafetyConstraint();
        systemSafetyConstraint.setName(systemSafetyConstraintDto.getName());

        List<Hazard> hazardEntities = new ArrayList<>();
        if (systemSafetyConstraintDto.getHazardsId() != null) {
            for (Long id : systemSafetyConstraintDto.getHazardsId())
                hazardEntities.add(hazardRepository.findById(id).orElseThrow(() -> new Step1NotFoundException("Hazard not found with id: " + id)));
        }
        systemSafetyConstraint.setHazardEntities(hazardEntities);

        Project project = projectRepository.findById(systemSafetyConstraintDto.getProjectId()).orElseThrow(() -> new Step1NotFoundException("Project not found with id: " + systemSafetyConstraintDto.getProjectId()));
        project.getSystemSafetyConstraintEntities().add(systemSafetyConstraint);
        projectRepository.save(project);

        return systemSafetyConstraint;
    }

    public void update(Long id, SystemSafetyConstraintDto systemSafetyConstraintDto) throws Step1NotFoundException {

        systemSafetyConstraintRepository.findById(id)
                .map(record -> {
                    record.setName(systemSafetyConstraintDto.getName());
                    List<Hazard> hazardEntities = new ArrayList<>();
                    if (systemSafetyConstraintDto.getHazardsId() != null) {
                        for (Long hazardId : systemSafetyConstraintDto.getHazardsId())
                            hazardEntities.add(hazardRepository.findById(hazardId).orElseThrow(() -> new Step1NotFoundException("Hazard not found with id: " + hazardId)));
                    }
                    record.setHazardEntities(hazardEntities);
                    return systemSafetyConstraintRepository.save(record);
                }).orElseThrow(() -> new Step1NotFoundException("SystemSafetyConstraint not found with id: " + id));
    }

    public void delete(Long id) throws Step1NotFoundException {
        systemSafetyConstraintRepository.findById(id)
                .map(record -> {
                    systemSafetyConstraintRepository.deleteHazardsAssociation(id);
                    systemSafetyConstraintRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new Step1NotFoundException("SystemSafetyConstraint not found with id: " + id));
    }

}
