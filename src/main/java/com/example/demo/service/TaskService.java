package com.example.demo.service;

import com.example.demo.ChangeStatus;
import com.example.demo.DTO.TaskDTO;
import com.example.demo.DTO.TaskStatusDTO;
import com.example.demo.TaskStatus;
import com.example.demo.entity.TaskEntity;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TaskService {

    @Autowired
    TaskRepository repository;

    final static ChangeStatus changeStatus = new ChangeStatus();
    final static Logger logger = LoggerFactory.getLogger(TaskService.class);


    public TaskEntity updateTask(TaskDTO taskDTO) {

        logger.info("Received task's id");
        TaskEntity task = repository.findById(taskDTO.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Task not found on :: "+ taskDTO.getId()));
        logger.info("Found task by id {}", taskDTO.getId());

        task.setName(taskDTO.getName());
        logger.info("Updated task's name by id {}", task.getId());
        task.setDescription(taskDTO.getDescription());
        logger.info("Updated task's description by id {}",  task.getId());
        task.setLastUpdate(new Date());
        logger.info("Updated task's lastUpdate by id {}",  task.getId());
        TaskEntity updatedTask = repository.save(task);
        logger.info("Updated task by id {}",  task.getId());

        return  updatedTask;

    }

    public TaskEntity addTask(String name, String description){

        TaskEntity task = new TaskEntity(name, description);
        logger.info("Created new task with name {} and description {}", name , description);
        TaskEntity addedTask = repository.save(task);
        logger.info("Saved new task with name {} in DB", name);
        return addedTask;
    }

    public Iterable<TaskEntity> getAllTasks () {
        logger.info("List all tasks");
        return repository.findAll();
    }

    public void deleteTaskById (int Id) {

        logger.info("Received task's id");
        repository.deleteById(Id);
        logger.info("Deleted task by id {}", Id);

    }

    public TaskEntity updateStatus(TaskStatusDTO taskStatusDTO){
        TaskEntity task = repository.findById(taskStatusDTO.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Task not found on :: "+ taskStatusDTO.getId()));
        task.setStatus(changeStatus.changeStatus(task.getStatus(),taskStatusDTO.getStatus()));

        task.setLastUpdate(new Date());
        TaskEntity updatedTask = repository.save(task);
        return  updatedTask;

    }

}
