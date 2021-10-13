package com.mycompany.todolist.controllers;

import com.mycompany.todolist.exceptions.ResourceNotFoundException;
import com.mycompany.todolist.models.Task;
import com.mycompany.todolist.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@CrossOrigin("http://localhost:4200")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @GetMapping
    public List<Task> findAll() {
        return taskService.findAll();
    }

    @GetMapping("/{id}")
    public Task findById(@PathVariable("id") int identifiant) {
        Task reponse = taskService.findById(identifiant);
        if (reponse == null) {
            throw new ResourceNotFoundException();
        }
        return reponse;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public int create(@RequestBody Task task) {
        return taskService.create(task);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable("id") int identifiant, @RequestBody Task task) {
        if (taskService.findById(identifiant) == null) {
            throw new ResourceNotFoundException();
        } else {
            taskService.update(identifiant, task);
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") int identifiant) {
        if (taskService.findById(identifiant) == null) {
            throw new ResourceNotFoundException();
        } else {
            taskService.deleteById(identifiant);
        }
    }
}
