package zetzet.practice.crazy.task.tracker.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.Instant;


//проверить необходимсость передачи id
@Builder
public record ProjectDto(@NonNull Long id, @NonNull String name,
                         @NonNull@JsonProperty("created_at")Instant createdAt, Instant updatedAt) {
}
