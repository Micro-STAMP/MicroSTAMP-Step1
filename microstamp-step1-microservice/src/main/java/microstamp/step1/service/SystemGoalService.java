package microstamp.step1.service;

import microstamp.step1.data.Project;
import microstamp.step1.data.SystemGoal;
import microstamp.step1.dto.SystemGoalDto;
import microstamp.step1.exception.Step1NotFoundException;
import microstamp.step1.repository.ProjectRepository;
import microstamp.step1.repository.SystemGoalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SystemGoalService {

    @Autowired
    private SystemGoalRepository SystemGoalRepository;

    @Autowired
    private ProjectRepository ProjectRepository;

    public List<SystemGoal> findAll(){
        return SystemGoalRepository.findAll();
    }

    public SystemGoal findById(Long id) throws Step1NotFoundException{
        return SystemGoalRepository.findById(id)
                .orElseThrow(() -> new Step1NotFoundException("SystemGoal not found with id: " + id));
    }

    public List<SystemGoal> findByProjectId(Long id) throws Step1NotFoundException{
        return SystemGoalRepository.findByProjectId(id)
                .orElseThrow(() -> new Step1NotFoundException("SystemGoals not found with projectId: " + id));
    }

    public SystemGoal insert(SystemGoalDto systemGoalDto){
        SystemGoal SystemGoal = new SystemGoal();
        SystemGoal.setName(systemGoalDto.getName());
        Project Project = ProjectRepository.findById(systemGoalDto.getProjectId()).orElseThrow(() -> new Step1NotFoundException("Project not found with id: " + systemGoalDto.getProjectId()));
        Project.getSystemGoalEntities().add(SystemGoal);
        ProjectRepository.save(Project);
        return SystemGoal;
    }

    public void update(Long id, SystemGoalDto systemGoalDto) throws Step1NotFoundException{
        SystemGoalRepository.findById(id)
                .map(record -> {
                    record.setName(systemGoalDto.getName());
                    return SystemGoalRepository.save(record);
                }).orElseThrow(() -> new Step1NotFoundException("SystemGoal not found with id: " + id));
    }

    public void delete(Long id) throws Step1NotFoundException{
        SystemGoalRepository.findById(id)
                .map(record -> {
                    SystemGoalRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new Step1NotFoundException("SystemGoal not found with id: " + id));
    }

}
