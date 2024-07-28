package microstamp.step1.service;

import microstamp.step1.data.Hazard;
import microstamp.step1.data.Loss;
import microstamp.step1.data.Project;
import microstamp.step1.dto.HazardDto;
import microstamp.step1.exception.Step1NotFoundException;
import microstamp.step1.exception.Step1OrphanException;
import microstamp.step1.exception.Step1SelfParentingHazardException;
import microstamp.step1.repository.HazardRepository;
import microstamp.step1.repository.LossRepository;
import microstamp.step1.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

    public Hazard findById(UUID id) throws Step1NotFoundException {
        return hazardRepository.findById(id)
                .orElseThrow(() -> new Step1NotFoundException("Hazard not found with id: " + id));
    }

    public List<Hazard> findByProjectId(UUID id) {
        return hazardRepository.findByProjectId(id.toString());
    }

    public Hazard insert(HazardDto hazardDto) throws Step1NotFoundException {
        Project project = projectRepository.findById(hazardDto.getProjectId())
                .orElseThrow(() -> new Step1NotFoundException("Project not found with id: " + hazardDto.getProjectId()));

        Hazard hazard = new Hazard();
        hazard.setName(hazardDto.getName());

        List<Loss> lossEntities = new ArrayList<>();
        if (hazardDto.getLossIds() != null) {
            for (UUID id : hazardDto.getLossIds())
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

    public void update(UUID id, HazardDto hazardDto) throws Step1NotFoundException {
        Hazard hazard = hazardRepository.findById(id)
                .orElseThrow(() -> new Step1NotFoundException("Hazard not found with id: " + id));

        hazard.setName(hazardDto.getName());
        List<Loss> lossEntities = new ArrayList<>();
        if (hazardDto.getLossIds() != null) {
            for (UUID lossId : hazardDto.getLossIds())
                lossEntities.add(lossRepository.findById(lossId)
                        .orElseThrow(() -> new Step1NotFoundException("Loss not found with id: " + lossId)));
        }

        hazard.setLossEntities(lossEntities);
        if (hazardDto.getFatherId() != null) {

            if (hazardDto.getFatherId().equals(id))
                throw new Step1SelfParentingHazardException();

            Hazard father = hazardRepository.findById(hazardDto.getFatherId())
                    .orElseThrow(() -> new Step1NotFoundException("Hazard not found with id: " + hazardDto.getFatherId()));

            List<Hazard> children = getHazardChildren(id);
            if(children.contains(father))
                throw new Step1OrphanException();

            hazard.setFather(father);
        } else {
            hazard.setFather(null);
        }

        hazardRepository.save(hazard);
    }

    public void delete(UUID id) throws Step1NotFoundException {
        deleteHazardAndChildren(id);
    }

    public void deleteHazardAndChildren(UUID id) {

        for (Hazard h : hazardRepository.findHazardChildren(id.toString()))
            deleteHazardAndChildren(h.getId());

        hazardRepository.deleteLossesAssociation(id.toString());
        hazardRepository.deleteSystemSafetyConstraintsAssociation(id.toString());
        hazardRepository.deleteById(id);
    }

    public List<Hazard> getHazardChildren(UUID id) throws Step1NotFoundException {
        Hazard hazard = hazardRepository.findById(id)
                .orElseThrow(() -> new Step1NotFoundException("Hazard not found with id: " + id));

        List<Hazard> children = new ArrayList<>();
        getChildren(hazard, children);

        return children;
    }

    private void getChildren(Hazard parent, List<Hazard> children) {
        List<Hazard> directChildren = hazardRepository.findHazardChildren(parent.getId().toString());
        for (Hazard child : directChildren) {
            children.add(child);
            getChildren(child, children);
        }
    }
}
