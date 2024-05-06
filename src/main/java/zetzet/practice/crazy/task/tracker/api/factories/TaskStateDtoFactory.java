package zetzet.practice.crazy.task.tracker.api.factories;

import org.springframework.stereotype.Component;
import zetzet.practice.crazy.task.tracker.api.dto.TaskStateDto;
import zetzet.practice.crazy.task.tracker.store.entities.TaskStateEntity;

@Component
public class TaskStateDtoFactory {

    public TaskStateDto makeTaskDto(TaskStateEntity entity){
        return TaskStateDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .ordinal(entity.getOrdinal())
                .createdAt(entity.getCreatedAt())
                .build();
    }
}
