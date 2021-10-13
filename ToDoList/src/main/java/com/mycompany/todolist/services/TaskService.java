package com.mycompany.todolist.services;

import com.mycompany.todolist.models.Task;

import java.util.List;

public interface TaskService {

    List<Task> findAll();

    Task findById(int id);

    int create(Task task);

    void update(int identifiant, Task task);

    void deleteById(int identifiant);
}
