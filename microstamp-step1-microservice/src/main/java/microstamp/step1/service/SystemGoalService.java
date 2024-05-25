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
    private SystemGoalRepository systemGoalRepository;

    @Autowired
    private ProjectRepository projectRepository;

    public List<SystemGoal> findAll() {
        return systemGoalRepository.findAll();
    }

    public SystemGoal findById(Long id) throws Step1NotFoundException {
        return systemGoalRepository.findById(id)
                .orElseThrow(() -> new Step1NotFoundException("SystemGoal not found with id: " + id));
    }

    public List<SystemGoal> findByProjectId(Long id) throws Step1NotFoundException {
        return systemGoalRepository.findByProjectId(id)
                .orElseThrow(() -> new Step1NotFoundException("SystemGoals not found with projectId: " + id));
    }

    public SystemGoal insert(SystemGoalDto systemGoalDto) {
        SystemGoal systemGoal = new SystemGoal();
        systemGoal.setName(systemGoalDto.getName());
        Project project = projectRepository.findById(systemGoalDto.getProjectId()).orElseThrow(() -> new Step1NotFoundException("Project not found with id: " + systemGoalDto.getProjectId()));
        project.getSystemGoalEntities().add(systemGoal);
        projectRepository.save(project);
        return systemGoal;
    }

    public void update(Long id, SystemGoalDto systemGoalDto) throws Step1NotFoundException {
        systemGoalRepository.findById(id)
                .map(record -> {
                    record.setName(systemGoalDto.getName());
                    return systemGoalRepository.save(record);
                }).orElseThrow(() -> new Step1NotFoundException("SystemGoal not found with id: " + id));
    }

    public void delete(Long id) throws Step1NotFoundException {
        systemGoalRepository.findById(id)
                .map(record -> {
                    systemGoalRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new Step1NotFoundException("SystemGoal not found with id: " + id));
    }

}
