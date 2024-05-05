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
    private LossRepository LossRepository;

    @Autowired
    private ProjectRepository ProjectRepository;

    public List<Loss> findAll(){
        return LossRepository.findAll();
    }

    public Loss findById(Long id) throws Step1NotFoundException {
        Loss Loss = LossRepository.findById(id)
                .orElseThrow(() -> new Step1NotFoundException("Loss not found with id: " + id));
        return Loss;
    }

    public List<Loss> findByProjectId(Long id) throws Step1NotFoundException{
        List<Loss> lossEntities = LossRepository.findByProjectId(id)
                .orElseThrow(() -> new Step1NotFoundException("Losses not found with projectId: " + id));
        return lossEntities;
    }

    public Loss insert(LossDto lossDto){
        Loss Loss = new Loss();
        Loss.setName(lossDto.getName());
        Project Project = ProjectRepository.findById(lossDto.getProjectId()).get();
        Project.getLossEntities().add(Loss);
        ProjectRepository.save(Project);
        return Loss;
    }

    public void update(Long id, LossDto lossDto) throws Step1NotFoundException{
        LossRepository.findById(id)
                .map(record -> {
                    record.setName(lossDto.getName());
                    return LossRepository.save(record);
                }).orElseThrow(() -> new Step1NotFoundException("Loss not found with id: " + id));
    }

    public void delete(Long id) throws Step1NotFoundException{
        LossRepository.findById(id)
                .map(record -> {
                    LossRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new Step1NotFoundException("Loss not found with id: " + id));
    }

}
