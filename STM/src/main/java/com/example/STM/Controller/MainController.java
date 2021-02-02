package com.example.STM.Controller;

import com.example.STM.Entity.*;
import com.example.STM.Service.TaskService;
import com.example.STM.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class MainController {
    @Autowired
    UserService userService;
    @Autowired
    TaskService taskService;

    //Zad1 - a
    @PostMapping("user/create")
    public void createUser(
            @RequestParam("name") String name,
            @RequestParam("lastName") String lastName,
            @RequestParam("email") String email,
            @RequestParam("password") String password)
    {
        userService.createUser(new User(name, lastName, email, password));
    }

    //Zad1 - b
    @GetMapping("user/all")
    public List<User> allUsers(){
        return userService.allUsers();
    }

    //Zad1 - c
    @GetMapping("user/find")
    public User findUser(
            @RequestParam String key
    ){
        Optional<User> user = userService.findByIdOrEmail(key);
        if(user.isPresent()) return user.get();
        else return null;
    }

    //Zad1 - d
    @PostMapping("user/changeStatus")
    public void changeUserStatus(
            @RequestParam long userId
    ){
        userService.changeStatus( userService.findById(userId) );
    }

    //Zad1 - e
    @DeleteMapping("user/delete")
    public void deleteUser(
            @RequestParam long userId
    ){
        userService.delete( userService.findById(userId) );
    }

    //Zad1 - f
    @PostMapping("task/create")
    public void createTask(
        @RequestParam long userId,
        @RequestParam String title,
        @RequestParam String description,
        @RequestParam Task.Type type,
        @RequestParam Task.Status status
    ){
        taskService.create( new Task(title, description, type, status, userService.findById(userId)) );
    }

    //Zad1 - g
    @GetMapping("task/byDateDesc")
    public List<Task> getTasksOrderByDateAddedDesc(){
        return taskService.getAllOrderByDateAddedDesc();
    }

    //Zad1 - h
    @GetMapping("task/find")
    public List<Task> findByTitleStatusType(
            @RequestParam String title,
            @RequestParam Task.Status status,
            @RequestParam Task.Type type
    ){
        return taskService.findByTitleStatusType(title, status, type);
    }

    //Zad1 - i
    @PostMapping("task/chagneStatus")
    public void changeTaskStatus(
            @RequestParam long taskId,
            @RequestParam Task.Status status
    ){
        taskService.changeStatus( taskService.findById(taskId), status );
    }

    //Zad1 - j
    @DeleteMapping("task/delete")
    public void deleteTask(
            @RequestParam long taskId
    ){
        taskService.delete(taskService.findById(taskId));
    }
}
