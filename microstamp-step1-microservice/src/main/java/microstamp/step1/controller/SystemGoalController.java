package microstamp.step1.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import microstamp.step1.dto.systemgoal.SystemGoalInsertDto;
import microstamp.step1.dto.systemgoal.SystemGoalReadDto;
import microstamp.step1.dto.systemgoal.SystemGoalUpdateDto;
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
    public ResponseEntity<List<SystemGoalReadDto>> findAll() {
        return new ResponseEntity<>(systemGoalService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SystemGoalReadDto> findById(@PathVariable(name = "id") UUID id) throws Step1NotFoundException {
        return new ResponseEntity<>(systemGoalService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/analysis/{id}")
    public ResponseEntity<List<SystemGoalReadDto>> findByAnalysisId(@PathVariable(name = "id") UUID id) {
        return new ResponseEntity<>(systemGoalService.findByAnalysisId(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<SystemGoalReadDto> insert(@Valid @RequestBody SystemGoalInsertDto systemGoalInsertDto) throws Step1NotFoundException {
        return new ResponseEntity<>(systemGoalService.insert(systemGoalInsertDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable(name = "id") UUID id, @Valid @RequestBody SystemGoalUpdateDto systemGoalUpdateDto) throws Step1NotFoundException {
        systemGoalService.update(id, systemGoalUpdateDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable(name = "id") UUID id) throws Step1NotFoundException {
        systemGoalService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
