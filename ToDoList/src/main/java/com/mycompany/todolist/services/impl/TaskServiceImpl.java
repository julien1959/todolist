package com.mycompany.todolist.services.impl;

import com.mycompany.todolist.dao.TaskRepository;
import com.mycompany.todolist.models.Task;
import com.mycompany.todolist.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public List<Task> findAll() {
        List<Task> liste = new ArrayList<>();
        taskRepository.findAll().forEach(liste::add);
        return liste;
    }

    @Override
    public Task findById(int id) {
        if (taskRepository.findById(id).isPresent()) {
            return taskRepository.findById(id).get();
        } else {
            return null;
        }
    }

    @Override
    public int create(Task task) {
        return taskRepository.save(task).getId();
    }

    @Override
    public void update(int identifiant, Task task) {
        task.setId(identifiant);
        taskRepository.save(task);
    }

    @Override
    public void deleteById(int identifiant) {
        taskRepository.deleteById(identifiant);
    }


}
