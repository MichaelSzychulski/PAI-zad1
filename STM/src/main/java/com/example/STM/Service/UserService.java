package com.example.STM.Service;

import com.example.STM.Entity.User;
import com.example.STM.Repository.UserRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Data
@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    TaskService taskService;

    //Zad1 - a
    public User createUser(User user){
        userRepository.save(user);
        return user;
    }

    //Zad1 - b
    public List<User> allUsers(){
        return userRepository.findAll();
    }

    //Zad1 - c
    public Optional<User> findByIdOrEmail(String key){
        Optional<User> user;
        if(key.contains("@")) user = userRepository.findByEmail(key);
        else user = userRepository.findById(Long.valueOf(key));
        return user;
    }

    //Zad1 - d
    public boolean changeStatus(User user){
        user.setStatus(!user.isStatus());
        userRepository.save(user);
        return user.isStatus();
    }

    //Zad1 - e
    public void delete(User user){
        user.getTasks().forEach(t -> taskService.delete(t));
        userRepository.delete(user);
    }

    public User findById(long id){
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()) return user.get();
        else return null;
    }




}
