package zetzet.practice.crazy.task.tracker.api.controllers;

import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;
import zetzet.practice.crazy.task.tracker.api.dto.AskDto;
import zetzet.practice.crazy.task.tracker.api.dto.ProjectDto;
import zetzet.practice.crazy.task.tracker.api.exceptions.BadRequestException;
import zetzet.practice.crazy.task.tracker.api.exceptions.NotFoundException;
import zetzet.practice.crazy.task.tracker.api.factories.ProjectDtoFactory;
import zetzet.practice.crazy.task.tracker.store.entities.ProjectEntity;
import zetzet.practice.crazy.task.tracker.store.repositories.ProjectRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Transactional
@RestController
@RequestMapping("/api")
public class ProjectController {

    ProjectDtoFactory projectDtoFactory;

    ProjectRepository projectRepository;

    public static final String CREATE_PROJECT = "/projects";
    public static final String EDIT_PROJECT = "/projects/{project_id}";
    public static final String FETCH_PROJECT = "/projects   ";
    public static final String DELETE_PROJECT = "/projects/{project_id}";

    public static final String CREATE_OR_UPDATE_PROJECT = "/projects";

/*
    @PostMapping(CREATE_PROJECT)
    public ProjectDto createProject(@RequestParam String name){

        if(name.trim().isEmpty()) {
            throw  new RuntimeException("Name can't be empty");
        }

        projectRepository
                .findByName(name)
                .ifPresent(project ->{

                    throw new BadRequestException(String.format("Project \"%s\" already exists.", name));
                } );

        ProjectEntity project = projectRepository.saveAndFlush(
                ProjectEntity.builder()
                        .name(name)
                        .build()
        );

        return projectDtoFactory.makeProjectDto(project);
    }
*/

    @PostMapping(CREATE_OR_UPDATE_PROJECT)
    public ProjectDto createOrUpdateProject(
            @RequestParam(value = "project_id", required = false) Optional<Long> optionalProjectId ,
            @RequestParam(value = "project_name", required = false) Optional<String> optionalProjectName
            //Any other params can be update or replace
    ) {

        optionalProjectName = optionalProjectName.filter(projectName -> !projectName.trim().isEmpty());

        boolean isCreate = !optionalProjectId.isPresent();

        if (isCreate && optionalProjectName.isPresent()) {
            throw  new BadRequestException("Project name can't be empty.");
        }

        final ProjectEntity project = optionalProjectId
                .map(this::getProjectOrThrowException)
                .orElseGet(() -> ProjectEntity
                        .builder()
                        .build());


        optionalProjectName
                .ifPresent(projectName -> {
                    projectRepository
                            .findByName(projectName)
                            .filter(anotherProject ->  !Objects.equals(anotherProject.getId(), project.getId()))
                            .ifPresent(anotherProject ->{
                                throw new BadRequestException(String.format("Project \"%s\" already exists.", projectName));
                            } );

                    project.setName(projectName);
                });

        final ProjectEntity savedProject = projectRepository.saveAndFlush(project);

        return projectDtoFactory.makeProjectDto(savedProject);
    }

    @GetMapping(FETCH_PROJECT)
    public List<ProjectDto> fetchProject(
            @RequestParam(value = "prefix_name", required = false) Optional<String> optionalPrefixName){

        optionalPrefixName = optionalPrefixName.filter(prefixName -> prefixName.trim().isEmpty());

        Stream<ProjectEntity> projectStream = optionalPrefixName
                .map(projectRepository::streamAllByNameStartsWithIgnoreCase)
                .orElseGet(projectRepository::streamAll);
/*
                            **Явная реализация проверки поиска по потоку по префиксам**

        if(optionalPrefixName.isPresent()) {
            projectStream = projectRepository.streamAllByNameStartsWithIgnoreCase(optionalPrefixName.get());
        } else {
            projectStream = projectRepository.streamAll();
        }
*/

        return projectStream
                .map(projectDtoFactory::makeProjectDto)
                .collect(Collectors.toList());
    }

/*
    @PatchMapping(EDIT_PROJECT)
    public ProjectDto editProject(@PathVariable("project_id") Long projectId,
                                  @RequestParam String name){

        if(name.trim().isEmpty()) {
            throw  new RuntimeException("Name can't be empty");
        }

        ProjectEntity project = getProjectOrThrowException(projectId);

        projectRepository
                .findByName(name)
                .filter(anotherProject ->  !Objects.equals(anotherProject.getId(), projectId))
                .ifPresent(anotherProject ->{
                    throw new BadRequestException(String.format("Project \"%s\" already exists.", name));
                } );


        project.setName(name);

        project = projectRepository.saveAndFlush(project);

        return projectDtoFactory.makeProjectDto(project);
    }
*/


    @DeleteMapping(DELETE_PROJECT)
    public AskDto deleteProject(@PathVariable("project_id") Long projectId){

        ProjectEntity project = getProjectOrThrowException(projectId);
        projectRepository.deleteById(projectId);

        return AskDto.makeDefault(true);
    }

    private ProjectEntity getProjectOrThrowException(Long projectId) {
        return projectRepository
                .findById(projectId)
                .orElseThrow(() ->
                        new NotFoundException(
                                String.format(
                                        "Project with \"%s\" doesn't exist.",
                                        projectId)));
    }
}
