package microstamp.step1.service;

import microstamp.step1.data.Project;
import microstamp.step1.data.SystemGoal;
import microstamp.step1.dto.SystemGoalDto;
import microstamp.step1.exception.Step1NotFoundException;
import microstamp.step1.repository.ProjectRepository;
import microstamp.step1.repository.SystemGoalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class SystemGoalService {

    @Autowired
    private SystemGoalRepository systemGoalRepository;

    @Autowired
    private ProjectRepository projectRepository;

    public List<SystemGoal> findAll() {
        return systemGoalRepository.findAll();
    }

    public SystemGoal findById(UUID id) throws Step1NotFoundException {
        return systemGoalRepository.findById(id)
                .orElseThrow(() -> new Step1NotFoundException("SystemGoal not found with id: " + id));
    }

    public List<SystemGoal> findByProjectId(UUID id) {
        return systemGoalRepository.findByProjectId(id.toString());
    }

    public SystemGoal insert(SystemGoalDto systemGoalDto) throws Step1NotFoundException {
        Project project = projectRepository.findById(systemGoalDto.getProjectId())
                .orElseThrow(() -> new Step1NotFoundException("Project not found with id: " + systemGoalDto.getProjectId()));

        SystemGoal systemGoal = new SystemGoal();
        systemGoal.setName(systemGoalDto.getName());

        project.getSystemGoalEntities().add(systemGoal);
        projectRepository.save(project);

        return systemGoal;
    }

    public void update(UUID id, SystemGoalDto systemGoalDto) throws Step1NotFoundException {
        SystemGoal systemGoal = systemGoalRepository.findById(id)
                .orElseThrow(() -> new Step1NotFoundException("SystemGoal not found with id: " + id));

        systemGoal.setName(systemGoalDto.getName());

        systemGoalRepository.save(systemGoal);
    }

    public void delete(UUID id) throws Step1NotFoundException {
        SystemGoal systemGoal = systemGoalRepository.findById(id)
                .orElseThrow(() -> new Step1NotFoundException("SystemGoal not found with id: " + id));
        systemGoalRepository.deleteById(systemGoal.getId());
    }
}
