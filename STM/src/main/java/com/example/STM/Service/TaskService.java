package com.example.STM.Service;

import com.example.STM.Entity.Task;
import com.example.STM.Repository.TaskRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Data
public class TaskService {
    @Autowired
    TaskRepository taskRepository;

    public Task findById(long id){
        Optional<Task> task = taskRepository.findById(id);
        if(task.isPresent()) return task.get();
        else return null;
    }

    //Zad1 - j?
    public void delete(Task task){
        taskRepository.delete(task);
    }

    //Zad1 - f
    public void create(Task task){
        taskRepository.save(task);
    }

    //Zad1 - g
    public List<Task> getAllOrderByDateAddedDesc(){
        return taskRepository.findAllByOrderByDateAddedDesc();
    }

    //Zad1 - h
    public List<Task> findByTitleStatusType(String title, Task.Status status, Task.Type type){
        if(title != null) return taskRepository.findByTitle(title);
        else if(status != null) return taskRepository.findByStatus(status);
        else if(type != null) return taskRepository.findByType(type);
        else return null;
    }

    //Zad1 - i
    public void changeStatus(Task task, Task.Status status){
        if(task != null){
            if(task.getStatus() != status) {
                task.setStatus(status);
                taskRepository.save(task);
            }
        }
    }


}
