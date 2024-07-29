package microstamp.step1.service.impl;

import microstamp.step1.data.Project;
import microstamp.step1.data.User;
import microstamp.step1.dto.project.ProjectInsertDto;
import microstamp.step1.dto.project.ProjectReadDto;
import microstamp.step1.dto.project.ProjectUpdateDto;
import microstamp.step1.exception.Step1NotFoundException;
import microstamp.step1.mapper.ProjectMapper;
import microstamp.step1.repository.ProjectRepository;
import microstamp.step1.repository.UserRepository;
import microstamp.step1.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@Component
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserRepository userRepository;

    public List<ProjectReadDto> findAll() {
        return projectRepository.findAll().stream()
                .map(ProjectMapper::toDto)
                .sorted(Comparator.comparing(ProjectReadDto::getName))
                .toList();
    }

    public ProjectReadDto findById(UUID id) throws Step1NotFoundException {
        return ProjectMapper.toDto(projectRepository.findById(id)
                .orElseThrow(() -> new Step1NotFoundException("Project not found with id: " + id)));
    }

    public ProjectReadDto findByUrl(String url) {
        return ProjectMapper.toDto(projectRepository.findByUrl(url));
    }

    public List<ProjectReadDto> findByUserId(UUID id) {
        return projectRepository.findProjectsByUserId(id.toString()).stream()
                .map(ProjectMapper::toDto)
                .sorted(Comparator.comparing(ProjectReadDto::getName))
                .toList();
    }

    public List<ProjectReadDto> findGuestsProjects() {
        return projectRepository.findProjectsForGuests().stream()
                .map(ProjectMapper::toDto)
                .sorted(Comparator.comparing(ProjectReadDto::getName))
                .toList();
    }

    public ProjectReadDto insert(ProjectInsertDto projectInsertDto) throws Step1NotFoundException {
        User user = userRepository.findById(projectInsertDto.getUserId())
                .orElseThrow(() -> new Step1NotFoundException("User not found with id: " + projectInsertDto.getUserId()));

        Project project = new Project();
        project.setName(projectInsertDto.getName());
        project.setDescription(projectInsertDto.getDescription());
        project.setUrl(projectInsertDto.getUrl());
        project.setType(projectInsertDto.getType());

        user.getProjects().add(project);
        projectRepository.save(project);

        return ProjectMapper.toDto(project);
    }

    public void update(UUID id, ProjectUpdateDto projectUpdateDto) throws Step1NotFoundException {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new Step1NotFoundException("Project not found with id: " + id));

        project.setName(projectUpdateDto.getName());
        project.setDescription(projectUpdateDto.getDescription());
        project.setUrl(projectUpdateDto.getUrl());
        project.setType(projectUpdateDto.getType());

        projectRepository.save(project);
    }

    public void delete(UUID id) throws Step1NotFoundException {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new Step1NotFoundException("Project not found with id: " + id));
        projectRepository.deleteById(project.getId());
    }
}
