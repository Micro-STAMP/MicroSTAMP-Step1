package microstamp.step1.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import microstamp.step1.data.SystemGoal;
import microstamp.step1.dto.SystemGoalDto;
import microstamp.step1.exception.Step1NotFoundException;
import microstamp.step1.service.SystemGoalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/system-goals")
@Tag(name = "SystemGoal")
public class SystemGoalController {

    @Autowired
    private SystemGoalService systemGoalService;

    @GetMapping
    public ResponseEntity<List<SystemGoal>> findAll() {
        return new ResponseEntity<>(systemGoalService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SystemGoal> findById(@PathVariable(name = "id") UUID id) throws Step1NotFoundException {
        return new ResponseEntity<>(systemGoalService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/project/{id}")
    public ResponseEntity<List<SystemGoal>> findByProjectId(@PathVariable(name = "id") UUID id) {
        return new ResponseEntity<>(systemGoalService.findByProjectId(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<SystemGoal> insert(@Valid @RequestBody SystemGoalDto systemGoalDto) throws Step1NotFoundException {
        return new ResponseEntity<>(systemGoalService.insert(systemGoalDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable(name = "id") UUID id, @Valid @RequestBody SystemGoalDto systemGoalDto) throws Step1NotFoundException {
        systemGoalService.update(id, systemGoalDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable(name = "id") UUID id) throws Step1NotFoundException {
        systemGoalService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
