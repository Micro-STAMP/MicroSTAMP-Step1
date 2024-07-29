package microstamp.step1.service.impl;

import microstamp.step1.data.Project;
import microstamp.step1.data.SystemGoal;
import microstamp.step1.dto.systemgoal.SystemGoalInsertDto;
import microstamp.step1.dto.systemgoal.SystemGoalReadDto;
import microstamp.step1.dto.systemgoal.SystemGoalUpdateDto;
import microstamp.step1.exception.Step1NotFoundException;
import microstamp.step1.mapper.SystemGoalMapper;
import microstamp.step1.repository.ProjectRepository;
import microstamp.step1.repository.SystemGoalRepository;
import microstamp.step1.service.SystemGoalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@Component
public class SystemGoalServiceImpl implements SystemGoalService {

    @Autowired
    private SystemGoalRepository systemGoalRepository;

    @Autowired
    private ProjectRepository projectRepository;

    public List<SystemGoalReadDto> findAll() {
        return systemGoalRepository.findAll().stream()
                .map(SystemGoalMapper::toDto)
                .sorted(Comparator.comparing(SystemGoalReadDto::getName))
                .toList();
    }

    public SystemGoalReadDto findById(UUID id) throws Step1NotFoundException {
        return SystemGoalMapper.toDto(systemGoalRepository.findById(id)
                .orElseThrow(() -> new Step1NotFoundException("SystemGoal not found with id: " + id)));
    }

    public List<SystemGoalReadDto> findByProjectId(UUID id) {
        return systemGoalRepository.findByProjectId(id.toString()).stream()
                .map(SystemGoalMapper::toDto)
                .sorted(Comparator.comparing(SystemGoalReadDto::getName))
                .toList();
    }

    public SystemGoalReadDto insert(SystemGoalInsertDto systemGoalInsertDto) throws Step1NotFoundException {
        Project project = projectRepository.findById(systemGoalInsertDto.getProjectId())
                .orElseThrow(() -> new Step1NotFoundException("Project not found with id: " + systemGoalInsertDto.getProjectId()));

        SystemGoal systemGoal = new SystemGoal();
        systemGoal.setName(systemGoalInsertDto.getName());

        project.getSystemGoalEntities().add(systemGoal);
        projectRepository.save(project);

        return SystemGoalMapper.toDto(systemGoal);
    }

    public void update(UUID id, SystemGoalUpdateDto systemGoalUpdateDto) throws Step1NotFoundException {
        SystemGoal systemGoal = systemGoalRepository.findById(id)
                .orElseThrow(() -> new Step1NotFoundException("SystemGoal not found with id: " + id));

        systemGoal.setName(systemGoalUpdateDto.getName());

        systemGoalRepository.save(systemGoal);
    }

    public void delete(UUID id) throws Step1NotFoundException {
        SystemGoal systemGoal = systemGoalRepository.findById(id)
                .orElseThrow(() -> new Step1NotFoundException("SystemGoal not found with id: " + id));
        systemGoalRepository.deleteById(systemGoal.getId());
    }
}
