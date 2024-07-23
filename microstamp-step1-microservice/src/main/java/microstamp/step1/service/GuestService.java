package microstamp.step1.service;

import microstamp.step1.data.*;
import microstamp.step1.exception.Step1NotFoundException;
import microstamp.step1.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

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

    public List<SystemGoal> findSystemGoalsByProjectId(long id){
        validateGuestProject(id);
        return systemGoalRepository.findByProjectId(id);
    }

    public List<Assumption> findAssumptionsByProjectId(long id){
        validateGuestProject(id);
        return assumptionRepository.findByProjectId(id);
    }

    public List<Loss> findLossesByProjectId(long id){
        validateGuestProject(id);
        return lossRepository.findByProjectId(id);
    }

    public List<Hazard> findHazardsByProjectId(long id){
        validateGuestProject(id);
        return hazardRepository.findByProjectId(id);
    }

    public List<SystemSafetyConstraint> findSystemSafetyConstraintsByProjectId(long id){
        validateGuestProject(id);
        return systemSafetyConstraintRepository.findByProjectId(id);
    }

    private void validateGuestProject(long id){
        List<Project> guestsProjects = projectRepository.findProjectsForGuests();

        if (guestsProjects.stream().noneMatch(project -> project.getId() == id))
            throw new Step1NotFoundException("Guest Project not found with id: " + id);
    }
}
