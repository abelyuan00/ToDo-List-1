package com.example.todolist1;


import jakarta.persistence.*;


@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String description;
    private boolean isDone;

    public void setId(Long id) {
        this.id = id;
    }

    public Task(boolean isDone, String description) {
        this.isDone = isDone;
        this.description = description;
    }

    public Task() {

    }

    public Long getId() {
        return id;
    }
    public String getDescription() {
        return description;
    }

    public boolean getIsDone() {
        return isDone;
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }


}
