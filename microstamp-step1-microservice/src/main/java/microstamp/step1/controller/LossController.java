package microstamp.step1.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import microstamp.step1.data.Loss;
import microstamp.step1.dto.loss.LossInsertDto;
import microstamp.step1.dto.loss.LossReadDto;
import microstamp.step1.dto.loss.LossUpdateDto;
import microstamp.step1.exception.Step1NotFoundException;
import microstamp.step1.service.LossService;
import microstamp.step1.service.impl.LossServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/losses")
@Tag(name = "Loss")
public class LossController {

    @Autowired
    private LossService lossService;

    @GetMapping
    public ResponseEntity<List<LossReadDto>> findAll() {
        return new ResponseEntity<>(lossService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LossReadDto> findById(@PathVariable(name = "id") UUID id) throws Step1NotFoundException {
        return new ResponseEntity<>(lossService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/project/{id}")
    public ResponseEntity<List<LossReadDto>> findByProjectId(@PathVariable(name = "id") UUID id) {
        return new ResponseEntity<>(lossService.findByProjectId(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<LossReadDto> insert(@Valid @RequestBody LossInsertDto lossInsertDto) throws Step1NotFoundException {
        return new ResponseEntity<>(lossService.insert(lossInsertDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable(name = "id") UUID id, @Valid @RequestBody LossUpdateDto lossUpdateDto) throws Step1NotFoundException {
        lossService.update(id, lossUpdateDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable(name = "id") UUID id) throws Step1NotFoundException {
        lossService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
