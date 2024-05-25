package microstamp.step1.service;

import microstamp.step1.data.Hazard;
import microstamp.step1.data.Loss;
import microstamp.step1.data.Project;
import microstamp.step1.dto.HazardDto;
import microstamp.step1.exception.Step1NotFoundException;
import microstamp.step1.repository.HazardRepository;
import microstamp.step1.repository.LossRepository;
import microstamp.step1.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class HazardService {

    @Autowired
    private HazardRepository hazardRepository;

    @Autowired
    private LossRepository lossRepository;

    @Autowired
    private ProjectRepository projectRepository;

    public List<Hazard> findAll() {
        return hazardRepository.findAll();
    }

    public Hazard findById(Long id) throws Step1NotFoundException {
        return hazardRepository.findById(id)
                .orElseThrow(() -> new Step1NotFoundException("Hazard not found with id: " + id));
    }

    public List<Hazard> findByProjectId(Long id) throws Step1NotFoundException {
        return hazardRepository.findByProjectId(id)
                .orElseThrow(() -> new Step1NotFoundException("Hazards not found with projectId: " + id));
    }

    public Hazard insert(HazardDto hazardDto) {
        Hazard hazard = new Hazard();
        hazard.setName(hazardDto.getName());

        List<Loss> lossEntities = new ArrayList<>();
        if (hazardDto.getLossIds() != null) {
            for (Long id : hazardDto.getLossIds())
                lossEntities.add(lossRepository.findById(id).orElseThrow(() -> new Step1NotFoundException("Loss not found with id: " + id)));
        }
        hazard.setLossEntities(lossEntities);

        if (hazardDto.getFatherId() != null) {
            Hazard father = hazardRepository.findById(hazardDto.getFatherId()).orElseThrow(() -> new Step1NotFoundException("Hazard not found with id: " + hazardDto.getFatherId()));
            hazard.setFather(father);
        } else {
            hazard.setFather(null);
        }

        Project project = projectRepository.findById(hazardDto.getProjectId()).orElseThrow(() -> new Step1NotFoundException("Project not found with id: " + hazardDto.getProjectId()));
        project.getHazardEntities().add(hazard);
        projectRepository.save(project);
        return hazard;
    }

    public void update(Long id, HazardDto hazardDto) throws Step1NotFoundException {
        hazardRepository.findById(id)
                .map(record -> {
                    record.setName(hazardDto.getName());
                    List<Loss> lossEntities = new ArrayList<>();
                    if (hazardDto.getLossIds() != null) {
                        for (Long lossId : hazardDto.getLossIds())
                            lossEntities.add(lossRepository.findById(lossId).orElseThrow(() -> new Step1NotFoundException("Loss not found with id: " + lossId)));
                    }
                    record.setLossEntities(lossEntities);
                    if (hazardDto.getFatherId() != null) {
                        Hazard father = hazardRepository.findById(hazardDto.getFatherId()).orElseThrow(() -> new Step1NotFoundException("Hazard not found with id: " + hazardDto.getFatherId()));
                        record.setFather(father);
                    } else {
                        record.setFather(null);
                    }

                    return hazardRepository.save(record);
                }).orElseThrow(() -> new Step1NotFoundException("Hazard not found with id: " + id));
    }

    public void delete(Long id) throws Step1NotFoundException {
        deleteHazardAndChildren(id);
    }

    public void deleteHazardAndChildren(Long id) {

        Optional<List<Hazard>> optionalChildren = hazardRepository.findHazardChildren(id);

        if (optionalChildren.isPresent()) {
            List<Hazard> children = optionalChildren.get();
            for (Hazard h : children) {
                deleteHazardAndChildren(h.getId());
            }
        }
        hazardRepository.deleteLossesAssociated(id);
        hazardRepository.deleteById(id);
    }

}
