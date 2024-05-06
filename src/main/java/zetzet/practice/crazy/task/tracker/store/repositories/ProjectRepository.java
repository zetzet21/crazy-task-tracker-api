package zetzet.practice.crazy.task.tracker.store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import zetzet.practice.crazy.task.tracker.store.entities.ProjectEntity;

public interface ProjectRepository extends JpaRepository<ProjectEntity, Long> {
}
