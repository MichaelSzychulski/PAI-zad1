package com.example.STM.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long taskId;
    private String title;
    private String description;
    private LocalDateTime dateAdded = LocalDateTime.now();
    private Type type;
    private Status status;
    @ManyToOne
    @JoinColumn(name="owner_id", nullable=false)
    @JsonIgnore
    private User owner;

    public Task(String title, String description, Type type, Status status, User owner) {
        this.title = title;
        this.description = description;
        this.type = type;
        this.status = status;
        this.owner = owner;
    }

    public enum Type{
        TASK,
        BUG,
        FEATURE
    }

    public enum Status{
        NEW,
        IN_PROGRESS,
        DONE
    }
}
