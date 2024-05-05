package microstamp.step1.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import microstamp.step1.service.*;
import microstamp.step1.data.*;
import microstamp.step1.exception.Step1NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/guests-request")
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
    public ResponseEntity<List<Project>> findGuestsProjects(){
        return new ResponseEntity<>(projectService.findGuestsProjects(), HttpStatus.OK);
    }

    @GetMapping("/systemgoals/{id}")
    public ResponseEntity<List<SystemGoal>> findGuestsSystemGoals(@PathVariable(name = "id") Long id) throws Step1NotFoundException {
        return new ResponseEntity<>(systemGoalService.findByProjectId(id), HttpStatus.OK);
    }

    @GetMapping("/assumptions/{id}")
    public ResponseEntity<List<Assumption>> findGuestsAssumptions(@PathVariable(name = "id") Long id) throws Step1NotFoundException {
        return new ResponseEntity<>(assumptionService.findByProjectId(id), HttpStatus.OK);
    }

    @GetMapping("/losses/{id}")
    public ResponseEntity<List<Loss>> findGuestsLosses(@PathVariable(name = "id") Long id) throws Step1NotFoundException {
        return new ResponseEntity<>(lossService.findByProjectId(id), HttpStatus.OK);
    }

    @GetMapping("/hazards/{id}")
    public ResponseEntity<List<Hazard>> findGuestsHazards(@PathVariable(name = "id") Long id) throws Step1NotFoundException {
        return new ResponseEntity<>(hazardService.findByProjectId(id), HttpStatus.OK);
    }

    @GetMapping("/systemsafetyconstraints/{id}")
    public ResponseEntity<List<SystemSafetyConstraint>> findGuestsSystemSafetyConstraints(@PathVariable(name = "id") Long id) throws Step1NotFoundException {
        return new ResponseEntity<>(systemSafetyConstraintService.findByProjectId(id), HttpStatus.OK);
    }

}
