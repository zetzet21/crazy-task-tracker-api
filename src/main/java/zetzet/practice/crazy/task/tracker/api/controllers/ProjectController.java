package zetzet.practice.crazy.task.tracker.api.controllers;

import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;
import zetzet.practice.crazy.task.tracker.api.dto.ProjectDto;
import zetzet.practice.crazy.task.tracker.api.factories.ProjectDtoFactory;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Transactional
@RestController
public class ProjectController {

    ProjectDtoFactory projectDtoFactory;

    public static final String CREATE_PROJECT = "/api/projects";

    @PostMapping(CREATE_PROJECT)
    public ProjectDto createProject(){


        //TODO: uncommitte and insert entity in method
        //return projectDtoFactory.makeProjectDto();
        return null;
    }
}
