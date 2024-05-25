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
    private SystemSafetyConstraintRepository SystemSafetyConstraintRepository;

    @Autowired
    private HazardRepository HazardRepository;

    @Autowired
    private ProjectRepository ProjectRepository;

    public List<SystemSafetyConstraint> findAll(){
        return SystemSafetyConstraintRepository.findAll();
    }

    public SystemSafetyConstraint findById(Long id) throws Step1NotFoundException {
        return SystemSafetyConstraintRepository.findById(id)
                .orElseThrow(() -> new Step1NotFoundException("SystemSafetyConstraint not found with id: " + id));
    }

    public List<SystemSafetyConstraint> findByProjectId(Long id) throws Step1NotFoundException{
        return SystemSafetyConstraintRepository.findByProjectId(id)
                .orElseThrow(() -> new Step1NotFoundException("SystemSafetyConstraint not found with projectId: " + id));
    }

    public SystemSafetyConstraint insert(SystemSafetyConstraintDto systemSafetyConstraintDto){
        SystemSafetyConstraint SystemSafetyConstraint = new SystemSafetyConstraint();
        SystemSafetyConstraint.setName(systemSafetyConstraintDto.getName());

        List<Hazard> hazardEntities = new ArrayList<>();
        if(systemSafetyConstraintDto.getHazardsId() != null) {
            for (Long id : systemSafetyConstraintDto.getHazardsId())
                hazardEntities.add(HazardRepository.findById(id).orElseThrow(() -> new Step1NotFoundException("Hazard not found with id: " + id)));
        }
        SystemSafetyConstraint.setHazardEntities(hazardEntities);

        Project Project = ProjectRepository.findById(systemSafetyConstraintDto.getProjectId()).orElseThrow(() -> new Step1NotFoundException("Project not found with id: " + systemSafetyConstraintDto.getProjectId()));
        Project.getSystemSafetyConstraintEntities().add(SystemSafetyConstraint);
        ProjectRepository.save(Project);

        return SystemSafetyConstraint;
    }

    public void update(Long id, SystemSafetyConstraintDto systemSafetyConstraintDto) throws Step1NotFoundException{

        SystemSafetyConstraintRepository.findById(id)
                .map(record -> {
                    record.setName(systemSafetyConstraintDto.getName());
                    List<Hazard> hazardEntities = new ArrayList<>();
                    if(systemSafetyConstraintDto.getHazardsId() != null) {
                        for (Long hazardId : systemSafetyConstraintDto.getHazardsId())
                            hazardEntities.add(HazardRepository.findById(hazardId).orElseThrow(() -> new Step1NotFoundException("Hazard not found with id: " + hazardId)));
                    }
                    record.setHazardEntities(hazardEntities);
                    return SystemSafetyConstraintRepository.save(record);
                }).orElseThrow(() -> new Step1NotFoundException("SystemSafetyConstraint not found with id: " + id));
    }

    public void delete(Long id) throws Step1NotFoundException{
        SystemSafetyConstraintRepository.findById(id)
                .map(record -> {
                    SystemSafetyConstraintRepository.deleteHazardsAssociated(id);
                    SystemSafetyConstraintRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new Step1NotFoundException("SystemSafetyConstraint not found with id: " + id));
    }

}
