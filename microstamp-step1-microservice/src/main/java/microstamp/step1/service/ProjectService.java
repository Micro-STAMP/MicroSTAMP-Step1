package microstamp.step1.service;

import microstamp.step1.data.Project;
import microstamp.step1.data.User;
import microstamp.step1.dto.ProjectDto;
import microstamp.step1.exception.Step1NotFoundException;
import microstamp.step1.repository.ProjectRepository;
import microstamp.step1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    public Project findById(Long id) throws Step1NotFoundException {
        return projectRepository.findById(id)
                .orElseThrow(() -> new Step1NotFoundException("Project not found with id: " + id));
    }

    public Project findByUrl(String url) {
        return projectRepository.findByUrl(url);
    }

    public List<Project> findByUserId(long id) {
        return projectRepository.findProjectsByUserId(id);
    }

    public List<Project> findGuestsProjects() {
        return projectRepository.findProjectsForGuests();
    }

    public Project insert(ProjectDto projectDto) throws Step1NotFoundException {
        User user = userRepository.findById(projectDto.getUserId())
                .orElseThrow(() -> new Step1NotFoundException("User not found with id: " + projectDto.getUserId()));

        Project project = new Project();
        project.setName(projectDto.getName());
        project.setDescription(projectDto.getDescription());
        project.setUrl(projectDto.getUrl());
        project.setType(projectDto.getType());

        user.getProjects().add(project);
        projectRepository.save(project);

        return project;
    }

    public void update(Long id, ProjectDto projectDto) throws Step1NotFoundException {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new Step1NotFoundException("Project not found with id: " + id));

        project.setName(projectDto.getName());
        project.setDescription(projectDto.getDescription());
        project.setUrl(projectDto.getUrl());
        project.setType(projectDto.getType());

        projectRepository.save(project);
    }

    public void delete(Long id) throws Step1NotFoundException {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new Step1NotFoundException("Project not found with id: " + id));
        projectRepository.deleteById(project.getId());
    }
}
