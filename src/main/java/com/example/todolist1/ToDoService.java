package com.example.todolist1;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class ToDoService {

    private final TaskRepository taskRepository;

    public ToDoService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getTasks(){
        return taskRepository.findAll();
    }

    public boolean addTask(String description){
        try {
            taskRepository.save(new Task(false, description));
            return true;
        }
        catch (Exception e){
            System.err.printf("Error: %s%n", e.getMessage());
            return false;
        }

    }

    public boolean deleteTask(Long id){
        try{
            taskRepository.deleteById(id);
            return true;
        }
        catch (Exception e){
            System.err.printf("Error: %s%n", e.getMessage());
            return false;
        }
    }

    public boolean markTaskAsDone(Long id) {
        try {
            Task task = taskRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid task id") );
            task.setIsDone(true);
            taskRepository.save(task);
            return true;
        }
        catch(Exception e){
            System.err.printf("Error: %s%n", e.getMessage());
            return false;
        }
    }
}
