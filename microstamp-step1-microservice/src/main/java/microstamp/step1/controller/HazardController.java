package microstamp.step1.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import microstamp.step1.data.Hazard;
import microstamp.step1.dto.hazard.HazardInsertDto;
import microstamp.step1.dto.hazard.HazardReadDto;
import microstamp.step1.dto.hazard.HazardUpdateDto;
import microstamp.step1.exception.Step1NotFoundException;
import microstamp.step1.service.HazardService;
import microstamp.step1.service.impl.HazardServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/hazards")
@Tag(name = "Hazard")
public class HazardController {

    @Autowired
    private HazardService hazardService;

    @GetMapping
    public ResponseEntity<List<HazardReadDto>> findAll() {
        return new ResponseEntity<>(hazardService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HazardReadDto> findById(@PathVariable(name = "id") UUID id) throws Step1NotFoundException {
        return new ResponseEntity<>(hazardService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/project/{id}")
    public ResponseEntity<List<HazardReadDto>> findByProjectId(@PathVariable(name = "id") UUID id) {
        return new ResponseEntity<>(hazardService.findByProjectId(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<HazardReadDto> insert(@Valid @RequestBody HazardInsertDto hazardInsertDto) throws Step1NotFoundException {
        return new ResponseEntity<>(hazardService.insert(hazardInsertDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable(name = "id") UUID id, @Valid @RequestBody HazardUpdateDto hazardUpdateDto) throws Step1NotFoundException {
        hazardService.update(id, hazardUpdateDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable(name = "id") UUID id) throws Step1NotFoundException {
        hazardService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(path = {"{id}/children"})
    public ResponseEntity<List<HazardReadDto>> getComponentChildren(@PathVariable UUID id) throws Step1NotFoundException {
        List<HazardReadDto> list = hazardService.getHazardChildren(id);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

}
