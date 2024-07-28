package microstamp.step1.service;

import microstamp.step1.data.*;
import microstamp.step1.exception.Step1NotFoundException;
import microstamp.step1.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class GuestService {

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

    public List<Project> findProjects() {
        return projectRepository.findProjectsForGuests();
    }

    public List<SystemGoal> findSystemGoalsByProjectId(UUID id){
        validateGuestProject(id);
        return systemGoalRepository.findByProjectId(id.toString());
    }

    public List<Assumption> findAssumptionsByProjectId(UUID id){
        validateGuestProject(id);
        return assumptionRepository.findByProjectId(id.toString());
    }

    public List<Loss> findLossesByProjectId(UUID id){
        validateGuestProject(id);
        return lossRepository.findByProjectId(id.toString());
    }

    public List<Hazard> findHazardsByProjectId(UUID id){
        validateGuestProject(id);
        return hazardRepository.findByProjectId(id.toString());
    }

    public List<SystemSafetyConstraint> findSystemSafetyConstraintsByProjectId(UUID id){
        validateGuestProject(id);
        return systemSafetyConstraintRepository.findByProjectId(id.toString());
    }

    private void validateGuestProject(UUID id){
        List<Project> guestsProjects = projectRepository.findProjectsForGuests();

        if (guestsProjects.stream().noneMatch(project -> project.getId().equals(id)))
            throw new Step1NotFoundException("Guest Project not found with id: " + id);
    }
}
