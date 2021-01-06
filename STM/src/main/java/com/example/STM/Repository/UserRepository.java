package com.example.STM.Repository;

import com.example.STM.Entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    public List<User> findAll();
    public Optional<User> findByEmail(String email);
}
