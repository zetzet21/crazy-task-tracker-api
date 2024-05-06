package zetzet.practice.crazy.task.tracker.api.factories;

import org.springframework.stereotype.Component;
import zetzet.practice.crazy.task.tracker.api.dto.ProjectDto;
import zetzet.practice.crazy.task.tracker.store.entities.ProjectEntity;

@Component
public class ProjectDtoFactory {

    public ProjectDto makeProjectDto(ProjectEntity entity){
        return ProjectDto.builder().
                id(entity.getId())
                .name(entity.getName())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}
