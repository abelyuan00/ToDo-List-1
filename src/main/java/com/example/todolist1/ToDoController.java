package com.example.todolist1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todo")
public class ToDoController {
    private final ToDoService toDoService;

    @Autowired
    public ToDoController(ToDoService toDoService) {
        this.toDoService = toDoService;
    }
    @GetMapping
    public List<Task> getTasks(){
        return toDoService.getTasks();
    }

    @PostMapping("/addTask")
    public boolean addTask(@RequestParam String description){
        try {
            toDoService.addTask(description);
            return true;
        }
        catch (Exception e){
            System.err.println(e.getMessage());
            return false;
        }
    }

    @PostMapping("/done")
    public boolean markTaskDone(@RequestParam Long id){
        try{
            toDoService.markTaskAsDone(id);
            return true;
        }
        catch (Exception e){
            System.err.println(e.getMessage());
            return false;
        }
    }

    @PostMapping("/delete")
    public boolean deleteTask(@RequestParam Long id){
        try {
            toDoService.deleteTask(id);
            return true;
        }
        catch (Exception e){
            System.err.println(e.getMessage());
            return false;
        }
    }

}
