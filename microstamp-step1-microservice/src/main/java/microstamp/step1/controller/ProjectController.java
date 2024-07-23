package microstamp.step1.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import microstamp.step1.data.Project;
import microstamp.step1.dto.ProjectDto;
import microstamp.step1.exception.Step1NotFoundException;
import microstamp.step1.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
@Tag(name = "Project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping
    public ResponseEntity<List<Project>> findAll() {
        return new ResponseEntity<>(projectService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Project> findById(@PathVariable(name = "id") Long id) throws Step1NotFoundException {
        return new ResponseEntity<>(projectService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/url/{url}")
    public ResponseEntity<Project> findByUrl(@PathVariable(name = "url") String url) {
        return new ResponseEntity<>(projectService.findByUrl(url), HttpStatus.OK);
    }

    @GetMapping(path = {"user/{id}"})
    public ResponseEntity<List<Project>> findByUserId(@PathVariable long id) {
        return new ResponseEntity<>(projectService.findByUserId(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Project> insert(@Valid @RequestBody ProjectDto projectDto) throws Step1NotFoundException {
        return new ResponseEntity<>(projectService.insert(projectDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable(name = "id") Long id, @Valid @RequestBody ProjectDto projectDto) throws Step1NotFoundException {
        projectService.update(id, projectDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable(name = "id") Long id) throws Step1NotFoundException {
        projectService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
