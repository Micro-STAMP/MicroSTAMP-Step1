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
    private GuestService guestService;

    @GetMapping("/projects")
    public ResponseEntity<List<Project>> findGuestsProjects() {
        return new ResponseEntity<>(guestService.findProjects(), HttpStatus.OK);
    }

    @GetMapping("/system-goals/project/{id}")
    public ResponseEntity<List<SystemGoal>> findGuestsSystemGoals(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(guestService.findSystemGoalsByProjectId(id), HttpStatus.OK);
    }

    @GetMapping("/assumptions/project/{id}")
    public ResponseEntity<List<Assumption>> findGuestsAssumptions(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(guestService.findAssumptionsByProjectId(id), HttpStatus.OK);
    }

    @GetMapping("/losses/project/{id}")
    public ResponseEntity<List<Loss>> findGuestsLosses(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(guestService.findLossesByProjectId(id), HttpStatus.OK);
    }

    @GetMapping("/hazards/project/{id}")
    public ResponseEntity<List<Hazard>> findGuestsHazards(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(guestService.findHazardsByProjectId(id), HttpStatus.OK);
    }

    @GetMapping("/system-safety-constraints/project/{id}")
    public ResponseEntity<List<SystemSafetyConstraint>> findGuestsSystemSafetyConstraints(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(guestService.findSystemSafetyConstraintsByProjectId(id), HttpStatus.OK);
    }

}
