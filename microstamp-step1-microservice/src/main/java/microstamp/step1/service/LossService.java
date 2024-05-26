package microstamp.step1.service;

import microstamp.step1.data.Loss;
import microstamp.step1.data.Project;
import microstamp.step1.dto.LossDto;
import microstamp.step1.exception.Step1NotFoundException;
import microstamp.step1.repository.LossRepository;
import microstamp.step1.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LossService {

    @Autowired
    private LossRepository lossRepository;

    @Autowired
    private ProjectRepository projectRepository;

    public List<Loss> findAll() {
        return lossRepository.findAll();
    }

    public Loss findById(Long id) throws Step1NotFoundException {
        return lossRepository.findById(id)
                .orElseThrow(() -> new Step1NotFoundException("Loss not found with id: " + id));
    }

    public List<Loss> findByProjectId(Long id) {
        return lossRepository.findByProjectId(id);
    }

    public Loss insert(LossDto lossDto) throws Step1NotFoundException {
        Loss loss = new Loss();
        loss.setName(lossDto.getName());
        Project project = projectRepository.findById(lossDto.getProjectId()).orElseThrow(() -> new Step1NotFoundException("Project not found with id: " + lossDto.getProjectId()));
        project.getLossEntities().add(loss);
        projectRepository.save(project);
        return loss;
    }

    public void update(Long id, LossDto lossDto) throws Step1NotFoundException {
        lossRepository.findById(id)
                .map(record -> {
                    record.setName(lossDto.getName());
                    return lossRepository.save(record);
                }).orElseThrow(() -> new Step1NotFoundException("Loss not found with id: " + id));
    }

    public void delete(Long id) throws Step1NotFoundException {
        lossRepository.findById(id)
                .map(record -> {
                    lossRepository.deleteHazardsAssociation(id);
                    lossRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new Step1NotFoundException("Loss not found with id: " + id));
    }

}
