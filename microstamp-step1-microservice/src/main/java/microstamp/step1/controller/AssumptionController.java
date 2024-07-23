package microstamp.step1.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import microstamp.step1.data.Assumption;
import microstamp.step1.dto.AssumptionDto;
import microstamp.step1.exception.Step1NotFoundException;
import microstamp.step1.service.AssumptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/assumptions")
@Tag(name = "Assumption")
public class AssumptionController {

    @Autowired
    private AssumptionService assumptionService;

    @GetMapping
    public ResponseEntity<List<Assumption>> findAll() {
        return new ResponseEntity<>(assumptionService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Assumption> findById(@PathVariable(name = "id") Long id) throws Step1NotFoundException {
        return new ResponseEntity<>(assumptionService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/project/{id}")
    public ResponseEntity<List<Assumption>> findByProjectId(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(assumptionService.findByProjectId(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Assumption> insert(@Valid @RequestBody AssumptionDto assumptionDto) throws Step1NotFoundException {
        return new ResponseEntity<>(assumptionService.insert(assumptionDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable(name = "id") Long id, @Valid @RequestBody AssumptionDto assumptionDto) throws Step1NotFoundException {
        assumptionService.update(id, assumptionDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable(name = "id") Long id) throws Step1NotFoundException {
        assumptionService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
