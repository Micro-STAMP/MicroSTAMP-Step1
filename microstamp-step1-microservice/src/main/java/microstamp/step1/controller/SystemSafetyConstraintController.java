package microstamp.step1.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import microstamp.step1.data.SystemSafetyConstraint;
import microstamp.step1.dto.systemsafetyconstraint.SystemSafetyConstraintInsertDto;
import microstamp.step1.dto.systemsafetyconstraint.SystemSafetyConstraintReadDto;
import microstamp.step1.dto.systemsafetyconstraint.SystemSafetyConstraintUpdateDto;
import microstamp.step1.exception.Step1NotFoundException;
import microstamp.step1.service.SystemSafetyConstraintService;
import microstamp.step1.service.impl.SystemSafetyConstraintServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/system-safety-constraints")
@Tag(name = "SystemSafetyConstraint")
public class SystemSafetyConstraintController {

    @Autowired
    private SystemSafetyConstraintService systemSafetyConstraintService;

    @GetMapping
    public ResponseEntity<List<SystemSafetyConstraintReadDto>> findAll() {
        return new ResponseEntity<>(systemSafetyConstraintService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SystemSafetyConstraintReadDto> findById(@PathVariable(name = "id") UUID id) throws Step1NotFoundException {
        return new ResponseEntity<>(systemSafetyConstraintService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/project/{id}")
    public ResponseEntity<List<SystemSafetyConstraintReadDto>> findByProjectId(@PathVariable(name = "id") UUID id) {
        return new ResponseEntity<>(systemSafetyConstraintService.findByProjectId(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<SystemSafetyConstraintReadDto> insert(@Valid @RequestBody SystemSafetyConstraintInsertDto systemSafetyConstraintInsertDto) throws Step1NotFoundException {
        return new ResponseEntity<>(systemSafetyConstraintService.insert(systemSafetyConstraintInsertDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable(name = "id") UUID id, @Valid @RequestBody SystemSafetyConstraintUpdateDto systemSafetyConstraintUpdateDto) throws Step1NotFoundException {
        systemSafetyConstraintService.update(id, systemSafetyConstraintUpdateDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable(name = "id") UUID id) throws Step1NotFoundException {
        systemSafetyConstraintService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
