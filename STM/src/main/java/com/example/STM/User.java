package com.example.STM;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;
    private String name;
    private String lastName;
    private String email;
    private String password;
    private boolean status;
    private LocalDateTime registrationDateTime;
    @OneToMany(mappedBy = "owner")
    private List<Task> tasks;

    public User() {
        this.status = false;
        this.registrationDateTime = LocalDateTime.now();
    }
}
