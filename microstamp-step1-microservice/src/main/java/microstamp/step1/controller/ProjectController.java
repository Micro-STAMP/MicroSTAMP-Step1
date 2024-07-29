package microstamp.step1.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import microstamp.step1.data.Project;
import microstamp.step1.dto.project.ProjectInsertDto;
import microstamp.step1.dto.project.ProjectReadDto;
import microstamp.step1.dto.project.ProjectUpdateDto;
import microstamp.step1.exception.Step1NotFoundException;
import microstamp.step1.service.ProjectService;
import microstamp.step1.service.impl.ProjectServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/projects")
@Tag(name = "Project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping
    public ResponseEntity<List<ProjectReadDto>> findAll() {
        return new ResponseEntity<>(projectService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectReadDto> findById(@PathVariable(name = "id") UUID id) throws Step1NotFoundException {
        return new ResponseEntity<>(projectService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/url/{url}")
    public ResponseEntity<ProjectReadDto> findByUrl(@PathVariable(name = "url") String url) {
        return new ResponseEntity<>(projectService.findByUrl(url), HttpStatus.OK);
    }

    @GetMapping(path = {"user/{id}"})
    public ResponseEntity<List<ProjectReadDto>> findByUserId(@PathVariable UUID id) {
        return new ResponseEntity<>(projectService.findByUserId(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProjectReadDto> insert(@Valid @RequestBody ProjectInsertDto projectInsertDto) throws Step1NotFoundException {
        return new ResponseEntity<>(projectService.insert(projectInsertDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable(name = "id") UUID id, @Valid @RequestBody ProjectUpdateDto projectUpdateDto) throws Step1NotFoundException {
        projectService.update(id, projectUpdateDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable(name = "id") UUID id) throws Step1NotFoundException {
        projectService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
