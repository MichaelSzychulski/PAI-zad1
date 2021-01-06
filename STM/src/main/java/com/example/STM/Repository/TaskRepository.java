package com.example.STM.Repository;

import com.example.STM.Entity.Task;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TaskRepository extends CrudRepository<Task, Long> {
    public List<Task> findAllByOrderByDateAddedDesc();
    public List<Task> findByTitle(String title);
    public List<Task> findByStatus(Task.Status status);
    public List<Task> findByType(Task.Type type);
}
