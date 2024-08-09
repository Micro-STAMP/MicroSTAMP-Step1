package microstamp.step1.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import microstamp.step1.dto.assumption.AssumptionInsertDto;
import microstamp.step1.dto.assumption.AssumptionReadDto;
import microstamp.step1.dto.assumption.AssumptionUpdateDto;
import microstamp.step1.exception.Step1NotFoundException;
import microstamp.step1.service.AssumptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/assumptions")
@Tag(name = "Assumption")
public class AssumptionController {

    @Autowired
    private AssumptionService assumptionService;

    @GetMapping
    public ResponseEntity<List<AssumptionReadDto>> findAll() {
        return new ResponseEntity<>(assumptionService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AssumptionReadDto> findById(@PathVariable(name = "id") UUID id) throws Step1NotFoundException {
        return new ResponseEntity<>(assumptionService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/analysis/{id}")
    public ResponseEntity<List<AssumptionReadDto>> findByAnalysisId(@PathVariable(name = "id") UUID id) {
        return new ResponseEntity<>(assumptionService.findByAnalysisId(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AssumptionReadDto> insert(@Valid @RequestBody AssumptionInsertDto assumptionInsertDto) throws Step1NotFoundException {
        return new ResponseEntity<>(assumptionService.insert(assumptionInsertDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable(name = "id") UUID id, @Valid @RequestBody AssumptionUpdateDto assumptionUpdateDto) throws Step1NotFoundException {
        assumptionService.update(id, assumptionUpdateDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable(name = "id") UUID id) throws Step1NotFoundException {
        assumptionService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
