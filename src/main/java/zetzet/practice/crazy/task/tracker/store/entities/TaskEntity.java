package zetzet.practice.crazy.task.tracker.store.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "task")
public class TaskEntity {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;

    @Getter
    @Setter
    @Column(unique = true)
    String name;

    @Builder.Default
    @Getter
    @Setter
    Instant createdAt = Instant.now();

    @Getter
    @Setter
    String description;
}
