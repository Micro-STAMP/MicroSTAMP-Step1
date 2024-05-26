package microstamp.step1.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import microstamp.step1.data.SystemSafetyConstraint;
import microstamp.step1.dto.SystemSafetyConstraintDto;
import microstamp.step1.exception.Step1NotFoundException;
import microstamp.step1.service.SystemSafetyConstraintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/systemsafetyconstraints")
@Tag(name = "SystemSafetyConstraint")
public class SystemSafetyConstraintController {

    @Autowired
    private SystemSafetyConstraintService systemSafetyConstraintService;

    @GetMapping
    public ResponseEntity<List<SystemSafetyConstraint>> findAll() {
        return new ResponseEntity<>(systemSafetyConstraintService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SystemSafetyConstraint> findById(@PathVariable(name = "id") Long id) throws Step1NotFoundException {
        return new ResponseEntity<>(systemSafetyConstraintService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/project/{id}")
    public ResponseEntity<List<SystemSafetyConstraint>> findByProjectId(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(systemSafetyConstraintService.findByProjectId(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<SystemSafetyConstraint> insert(@RequestBody SystemSafetyConstraintDto systemSafetyConstraintDto) throws Step1NotFoundException {
        return new ResponseEntity<>(systemSafetyConstraintService.insert(systemSafetyConstraintDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable(name = "id") Long id, @RequestBody SystemSafetyConstraintDto systemSafetyConstraintDto) throws Step1NotFoundException {
        systemSafetyConstraintService.update(id, systemSafetyConstraintDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable(name = "id") Long id) throws Step1NotFoundException {
        systemSafetyConstraintService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
