package microstamp.step1.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import microstamp.step1.data.*;
import microstamp.step1.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/guests")
@Tag(name = "Guest")
public class GuestController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private SystemGoalService systemGoalService;

    @Autowired
    private AssumptionService assumptionService;

    @Autowired
    private LossService lossService;

    @Autowired
    private HazardService hazardService;

    @Autowired
    private SystemSafetyConstraintService systemSafetyConstraintService;

    @GetMapping("/projects")
    public ResponseEntity<List<Project>> findGuestsProjects() {
        return new ResponseEntity<>(projectService.findGuestsProjects(), HttpStatus.OK);
    }

    @GetMapping("/systemgoals/project/{id}")
    public ResponseEntity<List<SystemGoal>> findGuestsSystemGoals(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(systemGoalService.findByProjectId(id), HttpStatus.OK);
    }

    @GetMapping("/assumptions/project/{id}")
    public ResponseEntity<List<Assumption>> findGuestsAssumptions(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(assumptionService.findByProjectId(id), HttpStatus.OK);
    }

    @GetMapping("/losses/project/{id}")
    public ResponseEntity<List<Loss>> findGuestsLosses(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(lossService.findByProjectId(id), HttpStatus.OK);
    }

    @GetMapping("/hazards/project/{id}")
    public ResponseEntity<List<Hazard>> findGuestsHazards(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(hazardService.findByProjectId(id), HttpStatus.OK);
    }

    @GetMapping("/systemsafetyconstraints/project/{id}")
    public ResponseEntity<List<SystemSafetyConstraint>> findGuestsSystemSafetyConstraints(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(systemSafetyConstraintService.findByProjectId(id), HttpStatus.OK);
    }

}
