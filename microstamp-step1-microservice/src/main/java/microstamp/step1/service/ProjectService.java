package microstamp.step1.service;

import microstamp.step1.data.Project;
import microstamp.step1.dto.ProjectDto;
import microstamp.step1.exception.Step1NotFoundException;
import microstamp.step1.repository.ProjectRepository;
import microstamp.step1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProjectService {

    @Autowired
    private ProjectRepository ProjectRepository;

    @Autowired
    private UserRepository UserRepository;

    public List<Project> findAll(){
        return ProjectRepository.findAll();
    }

    public Project findById(Long id) throws Step1NotFoundException{
        Project Project = ProjectRepository.findById(id)
                .orElseThrow(() -> new Step1NotFoundException("Project not found with id: " + id));
        return Project;
    }

    public Project findByUrl(String url) throws Step1NotFoundException{
        Project Project = ProjectRepository.findByUrl(url)
                .orElseThrow(() -> new Step1NotFoundException("Project not found with url: " + url));
        return Project;
    }

    public List<Project> findByUserId(long id) throws Step1NotFoundException{
        List<Project> projectEntities = ProjectRepository.findProjectsByUserId(id)
                .orElseThrow(() -> new Step1NotFoundException("Projects not found for user: " + id));
        return projectEntities;
    }

    public List<Project> findGuestsProjects() throws Step1NotFoundException{
        List<Project> projectEntities = ProjectRepository.findProjectsForGuests()
                .orElseThrow(() -> new Step1NotFoundException("Projects for guests not found"));
        return projectEntities;
    }

    public Project insert(ProjectDto projectDto){
        Project Project = new Project();
        Project.setName(projectDto.getName());
        Project.setDescription(projectDto.getDescription());
        Project.setUrl(projectDto.getUrl());
        Project.setType(projectDto.getType());
        UserRepository.findById(projectDto.getUserId()).get().getProjects().add(Project);
        ProjectRepository.save(Project);
        return Project;
    }

    public void update(Long id, ProjectDto projectDto) throws Step1NotFoundException{
        ProjectRepository.findById(id)
                .map(record -> {
                    record.setName(projectDto.getName());
                    record.setDescription(projectDto.getDescription());
                    record.setUrl(projectDto.getUrl());
                    record.setType(projectDto.getType());
                    return ProjectRepository.save(record);
                }).orElseThrow(() -> new Step1NotFoundException("Project not found with id: " + id));
    }

    public void delete(Long id) throws Step1NotFoundException{
        ProjectRepository.findById(id)
                .map(record -> {
                    ProjectRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new Step1NotFoundException("Project not found with id: " + id));
    }

}
