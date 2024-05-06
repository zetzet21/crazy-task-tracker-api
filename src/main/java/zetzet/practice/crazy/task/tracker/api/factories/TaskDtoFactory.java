package zetzet.practice.crazy.task.tracker.api.factories;

import org.springframework.stereotype.Component;
import zetzet.practice.crazy.task.tracker.api.dto.TaskDto;
import zetzet.practice.crazy.task.tracker.store.entities.TaskEntity;

@Component
public class TaskDtoFactory {

    public TaskDto makeTaskStateDto(TaskEntity entity){
        return TaskDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .createdAt(entity.getCreatedAt())
                .description(entity.getDescription())
                .build();
    }
}
