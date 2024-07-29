package microstamp.step1.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import microstamp.step1.data.*;
import microstamp.step1.dto.assumption.AssumptionReadDto;
import microstamp.step1.dto.hazard.HazardReadDto;
import microstamp.step1.dto.loss.LossReadDto;
import microstamp.step1.dto.project.ProjectReadDto;
import microstamp.step1.dto.systemgoal.SystemGoalReadDto;
import microstamp.step1.dto.systemsafetyconstraint.SystemSafetyConstraintReadDto;
import microstamp.step1.service.GuestService;
import microstamp.step1.service.impl.GuestServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/guests")
@Tag(name = "Guest")
public class GuestController {

    @Autowired
    private GuestService guestService;

    @GetMapping("/projects")
    public ResponseEntity<List<ProjectReadDto>> findGuestsProjects() {
        return new ResponseEntity<>(guestService.findProjects(), HttpStatus.OK);
    }

    @GetMapping("/system-goals/project/{id}")
    public ResponseEntity<List<SystemGoalReadDto>> findGuestsSystemGoals(@PathVariable(name = "id") UUID id) {
        return new ResponseEntity<>(guestService.findSystemGoalsByProjectId(id), HttpStatus.OK);
    }

    @GetMapping("/assumptions/project/{id}")
    public ResponseEntity<List<AssumptionReadDto>> findGuestsAssumptions(@PathVariable(name = "id") UUID id) {
        return new ResponseEntity<>(guestService.findAssumptionsByProjectId(id), HttpStatus.OK);
    }

    @GetMapping("/losses/project/{id}")
    public ResponseEntity<List<LossReadDto>> findGuestsLosses(@PathVariable(name = "id") UUID id) {
        return new ResponseEntity<>(guestService.findLossesByProjectId(id), HttpStatus.OK);
    }

    @GetMapping("/hazards/project/{id}")
    public ResponseEntity<List<HazardReadDto>> findGuestsHazards(@PathVariable(name = "id") UUID id) {
        return new ResponseEntity<>(guestService.findHazardsByProjectId(id), HttpStatus.OK);
    }

    @GetMapping("/system-safety-constraints/project/{id}")
    public ResponseEntity<List<SystemSafetyConstraintReadDto>> findGuestsSystemSafetyConstraints(@PathVariable(name = "id") UUID id) {
        return new ResponseEntity<>(guestService.findSystemSafetyConstraintsByProjectId(id), HttpStatus.OK);
    }

}
