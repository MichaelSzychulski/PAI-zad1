package com.example.STM;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long taskId;
    private String title;
    private String description;
    private LocalDateTime dateAdded;
    private Type type;
    private Status status;
    @ManyToOne
    @JoinColumn(name="owner_id", nullable=false)
    private User owner;

    public Task() {
        this.dateAdded = LocalDateTime.now();
    }

    private enum Type{
        TASK,
        BUG,
        FEATURE
    }

    private enum Status{
        NEW,
        IN_PROGRESS,
        DONE
    }
}
