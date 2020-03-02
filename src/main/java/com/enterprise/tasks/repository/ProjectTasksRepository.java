package com.enterprise.tasks.repository;

import com.enterprise.tasks.dto.ProjectTasks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjectTasksRepository extends JpaRepository<ProjectTasks, Long> {
    Optional<ProjectTasks> findById(Long id);
}
