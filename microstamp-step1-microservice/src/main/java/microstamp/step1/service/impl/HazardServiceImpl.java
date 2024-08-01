package microstamp.step1.service.impl;

import microstamp.step1.data.Hazard;
import microstamp.step1.data.Loss;
import microstamp.step1.data.Project;
import microstamp.step1.dto.hazard.HazardInsertDto;
import microstamp.step1.dto.hazard.HazardReadDto;
import microstamp.step1.dto.hazard.HazardUpdateDto;
import microstamp.step1.exception.Step1NotFoundException;
import microstamp.step1.exception.Step1OrphanException;
import microstamp.step1.exception.Step1SelfParentingHazardException;
import microstamp.step1.mapper.HazardMapper;
import microstamp.step1.repository.HazardRepository;
import microstamp.step1.repository.LossRepository;
import microstamp.step1.repository.ProjectRepository;
import microstamp.step1.service.HazardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@Component
public class HazardServiceImpl implements HazardService {

    @Autowired
    private HazardRepository hazardRepository;

    @Autowired
    private LossRepository lossRepository;

    @Autowired
    private ProjectRepository projectRepository;

    public List<HazardReadDto> findAll() {
        return hazardRepository.findAll().stream()
                .map(HazardMapper::toDto)
                .sorted(Comparator.comparing(HazardReadDto::getName))
                .toList();
    }

    public HazardReadDto findById(UUID id) throws Step1NotFoundException {
        return HazardMapper.toDto(hazardRepository.findById(id)
                .orElseThrow(() -> new Step1NotFoundException("Hazard not found with id: " + id)));
    }

    public List<HazardReadDto> findByProjectId(UUID id) {
        return hazardRepository.findByProjectId(id.toString()).stream()
                .map(HazardMapper::toDto)
                .sorted(Comparator.comparing(HazardReadDto::getName))
                .toList();
    }

    public HazardReadDto insert(HazardInsertDto hazardInsertDto) throws Step1NotFoundException {
        Project project = projectRepository.findById(hazardInsertDto.getProjectId())
                .orElseThrow(() -> new Step1NotFoundException("Project not found with id: " + hazardInsertDto.getProjectId()));

        Hazard hazard = new Hazard();
        hazard.setName(hazardInsertDto.getName());

        List<Loss> lossEntities = new ArrayList<>();
        if (hazardInsertDto.getLossIds() != null) {
            for (UUID id : hazardInsertDto.getLossIds())
                lossEntities.add(lossRepository.findById(id)
                        .orElseThrow(() -> new Step1NotFoundException("Loss not found with id: " + id)));
        }
        hazard.setLossEntities(lossEntities);

        if (hazardInsertDto.getFatherId() != null) {
            Hazard father = hazardRepository.findById(hazardInsertDto.getFatherId())
                    .orElseThrow(() -> new Step1NotFoundException("Hazard not found with id: " + hazardInsertDto.getFatherId()));
            hazard.setFather(father);
        } else {
            hazard.setFather(null);
        }

        project.getHazardEntities().add(hazard);
        projectRepository.save(project);
        return HazardMapper.toDto(hazard);
    }

    public void update(UUID id, HazardUpdateDto hazardUpdateDto) throws Step1NotFoundException {
        Hazard hazard = hazardRepository.findById(id)
                .orElseThrow(() -> new Step1NotFoundException("Hazard not found with id: " + id));

        hazard.setName(hazardUpdateDto.getName());
        List<Loss> lossEntities = new ArrayList<>();
        if (hazardUpdateDto.getLossIds() != null) {
            for (UUID lossId : hazardUpdateDto.getLossIds())
                lossEntities.add(lossRepository.findById(lossId)
                        .orElseThrow(() -> new Step1NotFoundException("Loss not found with id: " + lossId)));
        }

        hazard.setLossEntities(lossEntities);
        if (hazardUpdateDto.getFatherId() != null) {

            if (hazardUpdateDto.getFatherId().equals(id))
                throw new Step1SelfParentingHazardException();

            Hazard father = hazardRepository.findById(hazardUpdateDto.getFatherId())
                    .orElseThrow(() -> new Step1NotFoundException("Hazard not found with id: " + hazardUpdateDto.getFatherId()));

            List<HazardReadDto> children = getHazardChildren(id);

            if(children.stream().anyMatch(child -> child.getId().equals(hazardUpdateDto.getFatherId())))
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

    private void deleteHazardAndChildren(UUID id) {

        for (Hazard h : hazardRepository.findHazardChildren(id.toString()))
            deleteHazardAndChildren(h.getId());

        hazardRepository.deleteLossesAssociation(id.toString());
        hazardRepository.deleteSystemSafetyConstraintsAssociation(id.toString());
        hazardRepository.deleteById(id);
    }

    public List<HazardReadDto> getHazardChildren(UUID id) throws Step1NotFoundException {
        Hazard hazard = hazardRepository.findById(id)
                .orElseThrow(() -> new Step1NotFoundException("Hazard not found with id: " + id));

        List<Hazard> children = new ArrayList<>();
        getChildren(hazard, children);

        return children.stream()
                .map(HazardMapper::toDto)
                .sorted(Comparator.comparing(HazardReadDto::getName))
                .toList();
    }

    private void getChildren(Hazard parent, List<Hazard> children) {
        List<Hazard> directChildren = hazardRepository.findHazardChildren(parent.getId().toString());
        for (Hazard child : directChildren) {
            children.add(child);
            getChildren(child, children);
        }
    }
}