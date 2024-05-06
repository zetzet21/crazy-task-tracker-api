package zetzet.practice.crazy.task.tracker.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Builder
public record TaskStateDto(@NonNull Long id, @NonNull String name, @NonNull Long ordinal,
                           @NonNull @JsonProperty("created_at")Instant createdAt) {
}
