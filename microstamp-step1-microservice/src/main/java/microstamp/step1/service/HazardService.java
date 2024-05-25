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
    private HazardRepository HazardRepository;

    @Autowired
    private LossRepository LossRepository;

    @Autowired
    private ProjectRepository ProjectRepository;

    public List<Hazard> findAll(){
        return HazardRepository.findAll();
    }

    public Hazard findById(Long id) throws Step1NotFoundException {
        return HazardRepository.findById(id)
                .orElseThrow(() -> new Step1NotFoundException("Hazard not found with id: " + id));
    }

    public List<Hazard> findByProjectId(Long id) throws Step1NotFoundException{
        return HazardRepository.findByProjectId(id)
                .orElseThrow(() -> new Step1NotFoundException("Hazards not found with projectId: " + id));
    }

    public Hazard insert(HazardDto hazardDto){
        Hazard Hazard = new Hazard();
        Hazard.setName(hazardDto.getName());

        List<Loss> lossEntities = new ArrayList<>();
        if(hazardDto.getLossIds() != null) {
            for (Long id : hazardDto.getLossIds())
                lossEntities.add(LossRepository.findById(id).orElseThrow(() -> new Step1NotFoundException("Loss not found with id: " + id)));
        }
        Hazard.setLossEntities(lossEntities);

        if(hazardDto.getFatherId() != null){
            Hazard father = HazardRepository.findById(hazardDto.getFatherId()).orElseThrow(() -> new Step1NotFoundException("Hazard not found with id: " + hazardDto.getFatherId()));
            Hazard.setFather(father);
        }else{
            Hazard.setFather(null);
        }

        Project Project = ProjectRepository.findById(hazardDto.getProjectId()).orElseThrow(() -> new Step1NotFoundException("Project not found with id: " + hazardDto.getProjectId()));
        Project.getHazardEntities().add(Hazard);
        ProjectRepository.save(Project);
        return Hazard;
    }

    public void update(Long id, HazardDto hazardDto) throws Step1NotFoundException{
        HazardRepository.findById(id)
                .map(record -> {
                    record.setName(hazardDto.getName());
                    List<Loss> lossEntities = new ArrayList<>();
                    if(hazardDto.getLossIds() != null) {
                        for (Long lossId : hazardDto.getLossIds())
                            lossEntities.add(LossRepository.findById(lossId).orElseThrow(() -> new Step1NotFoundException("Loss not found with id: " + lossId)));
                    }
                    record.setLossEntities(lossEntities);
                    if(hazardDto.getFatherId() != null){
                        Hazard father = HazardRepository.findById(hazardDto.getFatherId()).orElseThrow(() -> new Step1NotFoundException("Hazard not found with id: " + hazardDto.getFatherId()));
                        record.setFather(father);
                    }else{
                        record.setFather(null);
                    }

                    return HazardRepository.save(record);
                }).orElseThrow(() -> new Step1NotFoundException("Hazard not found with id: " + id));
    }

    public void delete(Long id) throws Step1NotFoundException{
        deleteHazardAndChildren(id);
    }

    public void deleteHazardAndChildren(Long id){

        Optional<List<Hazard>> optionalChildren = HazardRepository.findHazardChildren(id);

        if (optionalChildren.isPresent()) {
            List<Hazard> children = optionalChildren.get();
            for (Hazard h : children) {
                deleteHazardAndChildren(h.getId());
            }
        }
        HazardRepository.deleteLossesAssociated(id);
        HazardRepository.deleteById(id);
    }

}
