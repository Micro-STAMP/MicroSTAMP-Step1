package microstamp.step1.service;

import microstamp.step1.data.Assumption;
import microstamp.step1.data.Project;
import microstamp.step1.dto.AssumptionDto;
import microstamp.step1.exception.Step1NotFoundException;
import microstamp.step1.repository.AssumptionRepository;
import microstamp.step1.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class AssumptionService {

    @Autowired
    private AssumptionRepository assumptionRepository;

    @Autowired
    private ProjectRepository projectRepository;

    public List<Assumption> findAll() {
        return assumptionRepository.findAll();
    }

    public Assumption findById(UUID id) throws Step1NotFoundException {
        return assumptionRepository.findById(id)
                .orElseThrow(() -> new Step1NotFoundException("Assumption not found with id: " + id));
    }

    public List<Assumption> findByProjectId(UUID id) {
        return assumptionRepository.findByProjectId(id.toString());
    }

    public Assumption insert(AssumptionDto assumptionDto) throws Step1NotFoundException {
        Project project = projectRepository.findById(assumptionDto.getProjectId())
                .orElseThrow(() -> new Step1NotFoundException(("Project not found with id: " + assumptionDto.getProjectId())));

        Assumption assumption = new Assumption();
        assumption.setName(assumptionDto.getName());

        project.getAssumptionEntities().add(assumption);
        projectRepository.save(project);

        return assumption;
    }

    public void update(UUID id, AssumptionDto assumptionDto) throws Step1NotFoundException {
        Assumption assumption = assumptionRepository.findById(id)
                .orElseThrow(() -> new Step1NotFoundException("Assumption not found with id: " + id));

        assumption.setName(assumptionDto.getName());

        assumptionRepository.save(assumption);
    }

    public void delete(UUID id) throws Step1NotFoundException {
        Assumption assumption = assumptionRepository.findById(id)
                .orElseThrow(() -> new Step1NotFoundException("Assumption not found with id: " + id));
        assumptionRepository.deleteById(assumption.getId());
    }
}