package microstamp.step1.service.impl;

import microstamp.step1.data.*;
import microstamp.step1.dto.assumption.AssumptionReadDto;
import microstamp.step1.dto.hazard.HazardReadDto;
import microstamp.step1.dto.loss.LossReadDto;
import microstamp.step1.dto.project.ProjectReadDto;
import microstamp.step1.dto.systemgoal.SystemGoalReadDto;
import microstamp.step1.dto.systemsafetyconstraint.SystemSafetyConstraintReadDto;
import microstamp.step1.exception.Step1NotFoundException;
import microstamp.step1.mapper.*;
import microstamp.step1.repository.*;
import microstamp.step1.service.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@Component
public class GuestServiceImpl implements GuestService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private SystemGoalRepository systemGoalRepository;

    @Autowired
    private AssumptionRepository assumptionRepository;

    @Autowired
    private LossRepository lossRepository;

    @Autowired
    private HazardRepository hazardRepository;

    @Autowired
    private SystemSafetyConstraintRepository systemSafetyConstraintRepository;

    public List<ProjectReadDto> findProjects() {
        return projectRepository.findProjectsForGuests().stream()
                .map(ProjectMapper::toDto)
                .sorted(Comparator.comparing(ProjectReadDto::getName))
                .toList();
    }

    public List<SystemGoalReadDto> findSystemGoalsByProjectId(UUID id){
        validateGuestProject(id);
        return systemGoalRepository.findByProjectId(id.toString()).stream()
                .map(SystemGoalMapper::toDto)
                .sorted(Comparator.comparing(SystemGoalReadDto::getName))
                .toList();
    }

    public List<AssumptionReadDto> findAssumptionsByProjectId(UUID id){
        validateGuestProject(id);
        return assumptionRepository.findByProjectId(id.toString()).stream()
                .map(AssumptionMapper::toDto)
                .sorted(Comparator.comparing(AssumptionReadDto::getName))
                .toList();
    }

    public List<LossReadDto> findLossesByProjectId(UUID id){
        validateGuestProject(id);
        return lossRepository.findByProjectId(id.toString()).stream()
                .map(LossMapper::toDto)
                .sorted(Comparator.comparing(LossReadDto::getName))
                .toList();
    }

    public List<HazardReadDto> findHazardsByProjectId(UUID id){
        validateGuestProject(id);
        return hazardRepository.findByProjectId(id.toString()).stream()
                .map(HazardMapper::toDto)
                .sorted(Comparator.comparing(HazardReadDto::getName))
                .toList();
    }

    public List<SystemSafetyConstraintReadDto> findSystemSafetyConstraintsByProjectId(UUID id){
        validateGuestProject(id);
        return systemSafetyConstraintRepository.findByProjectId(id.toString()).stream()
                .map(SystemSafetyConstraintMapper::toDto)
                .sorted(Comparator.comparing(SystemSafetyConstraintReadDto::getName))
                .toList();
    }

    private void validateGuestProject(UUID id){
        List<Project> guestsProjects = projectRepository.findProjectsForGuests();

        if (guestsProjects.stream().noneMatch(project -> project.getId().equals(id)))
            throw new Step1NotFoundException("Guest Project not found with id: " + id);
    }
}
