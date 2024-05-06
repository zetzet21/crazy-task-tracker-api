package zetzet.practice.crazy.task.tracker.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Builder
public record TaskDto(@NonNull Long id, @NonNull String name,
                      @NonNull @JsonProperty("created_at")Instant createdAt, @NonNull String description) {
}
