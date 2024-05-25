package microstamp.step1.service;

import microstamp.step1.data.Assumption;
import microstamp.step1.data.Project;
import microstamp.step1.dto.AssumptionDto;
import microstamp.step1.exception.Step1NotFoundException;
import microstamp.step1.repository.AssumptionRepository;
import microstamp.step1.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AssumptionService {

    @Autowired
    private AssumptionRepository assumptionRepository;

    @Autowired
    private ProjectRepository projectRepository;

    public List<Assumption> findAll() {
        return assumptionRepository.findAll();
    }

    public Assumption findById(Long id) throws Step1NotFoundException {
        return assumptionRepository.findById(id)
                .orElseThrow(() -> new Step1NotFoundException("Assumption not found with id: " + id));
    }

    public List<Assumption> findByProjectId(Long id) throws Step1NotFoundException {
        return assumptionRepository.findByProjectId(id)
                .orElseThrow(() -> new Step1NotFoundException("Assumptions not found with projectId: " + id));
    }

    public Assumption insert(AssumptionDto assumptionDto) {
        Assumption assumption = new Assumption();
        assumption.setName(assumptionDto.getName());
        Project project = projectRepository.findById(assumptionDto.getProjectId()).orElseThrow(() -> new Step1NotFoundException(("Project not found with id: " + assumptionDto.getProjectId())));
        project.getAssumptionEntities().add(assumption);
        projectRepository.save(project);
        return assumption;
    }

    public void update(Long id, AssumptionDto assumptionDto) throws Step1NotFoundException {
        assumptionRepository.findById(id)
                .map(record -> {
                    record.setName(assumptionDto.getName());
                    return assumptionRepository.save(record);
                }).orElseThrow(() -> new Step1NotFoundException("Assumption not found with id: " + id));
    }

    public void delete(Long id) throws Step1NotFoundException {
        assumptionRepository.findById(id)
                .map(record -> {
                    assumptionRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new Step1NotFoundException("Assumption not found with id: " + id));
    }
}