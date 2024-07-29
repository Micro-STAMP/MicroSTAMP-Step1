package microstamp.step1.service;

import microstamp.step1.dto.project.ProjectInsertDto;
import microstamp.step1.dto.project.ProjectReadDto;
import microstamp.step1.dto.project.ProjectUpdateDto;

import java.util.List;
import java.util.UUID;

public interface ProjectService {

    List<ProjectReadDto> findAll();

    ProjectReadDto findById(UUID id);

    ProjectReadDto findByUrl(String url);

    List<ProjectReadDto> findByUserId(UUID id);

    List<ProjectReadDto> findGuestsProjects();

    ProjectReadDto insert(ProjectInsertDto projectInsertDto);

    void update(UUID id, ProjectUpdateDto projectUpdateDto);

    void delete(UUID id);
}
