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
        Hazard Hazard = HazardRepository.findById(id)
                .orElseThrow(() -> new Step1NotFoundException("Hazard not found with id: " + id));
        return Hazard;
    }

    public List<Hazard> findByProjectId(Long id) throws Step1NotFoundException{
        List<Hazard> hazardEntities = HazardRepository.findByProjectId(id)
                .orElseThrow(() -> new Step1NotFoundException("Hazards not found with projectId: " + id));
        return hazardEntities;
    }

    public Hazard insert(HazardDto hazardDto){
        Hazard Hazard = new Hazard();
        Hazard.setName(hazardDto.getName());

        List<Loss> lossEntities = new ArrayList<>();
        if(hazardDto.getLossIds() != null) {
            for (Long id : hazardDto.getLossIds())
                lossEntities.add(LossRepository.findById(id).get());
        }
        Hazard.setLossEntities(lossEntities);
        try{
            Hazard father = HazardRepository.findById(hazardDto.getFatherId()).get();
            Hazard.setFather(father);
        }catch (Exception ex){
            Hazard.setFather(null);
        }
        Project Project = ProjectRepository.findById(hazardDto.getProjectId()).get();
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
                            lossEntities.add(LossRepository.findById(lossId).get());
                    }
                    record.setLossEntities(lossEntities);
                    try{
                        Hazard father = HazardRepository.findById(hazardDto.getFatherId()).get();
                        record.setFather(father);
                    }catch(Exception ex){
                        record.setFather(null);
                    }
                    return HazardRepository.save(record);
                }).orElseThrow(() -> new Step1NotFoundException("Hazard not found with id: " + id));
    }

    public void delete(Long id) throws Step1NotFoundException{
        deleteHazardAndChildren(id);
    }

    public void deleteHazardAndChildren(Long id){
        List<Hazard> children = HazardRepository.findHazardChildren(id).get();
        for(Hazard h : children){
            deleteHazardAndChildren(h.getId());
        }
        HazardRepository.deleteLossesAssociated(id);
        HazardRepository.deleteById(id);
    }

}
