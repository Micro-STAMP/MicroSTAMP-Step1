package microstamp.step1.service;

import microstamp.step1.data.Loss;
import microstamp.step1.data.Project;
import microstamp.step1.dto.LossDto;
import microstamp.step1.exception.Step1NotFoundException;
import microstamp.step1.repository.LossRepository;
import microstamp.step1.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class LossService {

    @Autowired
    private LossRepository lossRepository;

    @Autowired
    private ProjectRepository projectRepository;

    public List<Loss> findAll() {
        return lossRepository.findAll();
    }

    public Loss findById(UUID id) throws Step1NotFoundException {
        return lossRepository.findById(id)
                .orElseThrow(() -> new Step1NotFoundException("Loss not found with id: " + id));
    }

    public List<Loss> findByProjectId(UUID id) {
        return lossRepository.findByProjectId(id.toString());
    }

    public Loss insert(LossDto lossDto) throws Step1NotFoundException {
        Project project = projectRepository.findById(lossDto.getProjectId())
                .orElseThrow(() -> new Step1NotFoundException("Project not found with id: " + lossDto.getProjectId()));

        Loss loss = new Loss();
        loss.setName(lossDto.getName());

        project.getLossEntities().add(loss);
        projectRepository.save(project);

        return loss;
    }

    public void update(UUID id, LossDto lossDto) throws Step1NotFoundException {
        Loss loss = lossRepository.findById(id)
                .orElseThrow(() -> new Step1NotFoundException("Loss not found with id: " + id));

        loss.setName(lossDto.getName());

        lossRepository.save(loss);
    }

    public void delete(UUID id) throws Step1NotFoundException {
        Loss loss = lossRepository.findById(id)
                .orElseThrow(() -> new Step1NotFoundException("Loss not found with id: " + id));
        lossRepository.deleteHazardsAssociation(id.toString());
        lossRepository.deleteById(loss.getId());
    }
}
