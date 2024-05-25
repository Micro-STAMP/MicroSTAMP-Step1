package microstamp.step1.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import microstamp.step1.service.HazardService;
import microstamp.step1.data.Hazard;
import microstamp.step1.dto.HazardDto;
import microstamp.step1.exception.Step1NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hazards")
@Tag(name = "Hazard")
public class HazardController {

    @Autowired
    private HazardService hazardService;

    @GetMapping
    public ResponseEntity<List<Hazard>> findAll(){
        return new ResponseEntity<>(hazardService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Hazard> findById(@PathVariable(name = "id") Long id) throws Step1NotFoundException {
        return new ResponseEntity<>(hazardService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/findByProjectId/{id}")
    public ResponseEntity<List<Hazard>> findByProjectId(@PathVariable(name = "id") Long id) throws Step1NotFoundException {
        return new ResponseEntity<>(hazardService.findByProjectId(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Hazard> insert(@RequestBody HazardDto hazardDto){
        return new ResponseEntity<>(hazardService.insert(hazardDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable(name = "id") Long id, @RequestBody HazardDto hazardDto) throws Step1NotFoundException{
        hazardService.update(id, hazardDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable(name = "id") Long id) throws Step1NotFoundException{
        hazardService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
