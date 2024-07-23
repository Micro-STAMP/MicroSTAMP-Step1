package microstamp.step1.service;

import microstamp.step1.data.Hazard;
import microstamp.step1.data.Loss;
import microstamp.step1.data.Project;
import microstamp.step1.dto.HazardDto;
import microstamp.step1.exception.Step1NotFoundException;
import microstamp.step1.exception.Step1SelfParentingHazardException;
import microstamp.step1.repository.HazardRepository;
import microstamp.step1.repository.LossRepository;
import microstamp.step1.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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

    public List<Hazard> findByProjectId(Long id) {
        return hazardRepository.findByProjectId(id);
    }

    public Hazard insert(HazardDto hazardDto) throws Step1NotFoundException {
        Project project = projectRepository.findById(hazardDto.getProjectId())
                .orElseThrow(() -> new Step1NotFoundException("Project not found with id: " + hazardDto.getProjectId()));

        Hazard hazard = new Hazard();
        hazard.setName(hazardDto.getName());

        List<Loss> lossEntities = new ArrayList<>();
        if (hazardDto.getLossIds() != null) {
            for (Long id : hazardDto.getLossIds())
                lossEntities.add(lossRepository.findById(id)
                        .orElseThrow(() -> new Step1NotFoundException("Loss not found with id: " + id)));
        }
        hazard.setLossEntities(lossEntities);

        if (hazardDto.getFatherId() != null) {
            Hazard father = hazardRepository.findById(hazardDto.getFatherId())
                    .orElseThrow(() -> new Step1NotFoundException("Hazard not found with id: " + hazardDto.getFatherId()));
            hazard.setFather(father);
        } else {
            hazard.setFather(null);
        }

        project.getHazardEntities().add(hazard);
        projectRepository.save(project);
        return hazard;
    }

    public void update(Long id, HazardDto hazardDto) throws Step1NotFoundException {
        Hazard hazard = hazardRepository.findById(id)
                .orElseThrow(() -> new Step1NotFoundException("Hazard not found with id: " + id));

        hazard.setName(hazardDto.getName());
        List<Loss> lossEntities = new ArrayList<>();
        if (hazardDto.getLossIds() != null) {
            for (Long lossId : hazardDto.getLossIds())
                lossEntities.add(lossRepository.findById(lossId)
                        .orElseThrow(() -> new Step1NotFoundException("Loss not found with id: " + lossId)));
        }

        hazard.setLossEntities(lossEntities);
        if (hazardDto.getFatherId() != null) {

            if (hazardDto.getFatherId().equals(id))
                throw new Step1SelfParentingHazardException();

            Hazard father = hazardRepository.findById(hazardDto.getFatherId())
                    .orElseThrow(() -> new Step1NotFoundException("Hazard not found with id: " + hazardDto.getFatherId()));
            hazard.setFather(father);
        } else {
            hazard.setFather(null);
        }

        hazardRepository.save(hazard);
    }

    public void delete(Long id) throws Step1NotFoundException {
        deleteHazardAndChildren(id);
    }

    public void deleteHazardAndChildren(Long id) {

        for (Hazard h : hazardRepository.findHazardChildren(id))
            deleteHazardAndChildren(h.getId());

        hazardRepository.deleteLossesAssociation(id);
        hazardRepository.deleteSystemSafetyConstraintsAssociation(id);
        hazardRepository.deleteById(id);
    }

}
