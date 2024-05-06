package zetzet.practice.crazy.task.tracker.store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import zetzet.practice.crazy.task.tracker.store.entities.TaskEntity;

public interface TaskRepository extends JpaRepository<TaskEntity, Long> {
}
