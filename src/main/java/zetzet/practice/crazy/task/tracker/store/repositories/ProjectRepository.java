package zetzet.practice.crazy.task.tracker.store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import zetzet.practice.crazy.task.tracker.store.entities.ProjectEntity;

import java.util.Optional;
import java.util.stream.Stream;

public interface ProjectRepository extends JpaRepository<ProjectEntity, Long> {

    Optional<ProjectEntity> findByName(Object name);

    Stream<ProjectEntity> streamAll();

    Stream<ProjectEntity> streamAllByNameStartsWithIgnoreCase(String prefixName);
}
