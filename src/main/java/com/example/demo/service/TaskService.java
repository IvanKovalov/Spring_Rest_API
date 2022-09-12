package com.example.demo.service;

import com.example.demo.ChangeStatus;
import com.example.demo.DTO.TaskDTO;
import com.example.demo.DTO.TaskStatusDTO;
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

    static final ChangeStatus changeStatus = new ChangeStatus();
    static final Logger logger = LoggerFactory.getLogger(TaskService.class);


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

    public TaskEntity addTask(TaskDTO taskDTO){

        TaskEntity task = new TaskEntity(taskDTO.getName(), taskDTO.getDescription());
        logger.info("Created new task with name {} and description {}", taskDTO.getName() , taskDTO.getDescription());
        TaskEntity addedTask = repository.save(task);
        logger.info("Saved new task with name {} in DB", taskDTO.getName());

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
        logger.info("Received taskStatus dto");
        TaskEntity task = repository.findById(taskStatusDTO.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Task not found on :: "+ taskStatusDTO.getId()));
        logger.info("Take taskEntity from db");
        task.setStatus(changeStatus.changeStatus(task.getStatus(),taskStatusDTO.getStatus()));
        task.setLastUpdate(new Date());
        logger.info("Updated task's status and lastUpdate");
        TaskEntity updatedTask = repository.save(task);

        return  updatedTask;

    }

    public TaskEntity getTaskById (int Id) {

        logger.info("Received task's id");
        TaskEntity getTask = repository.getReferenceById(Id);
        logger.info("Get task by id {}", Id);
        return getTask;
    }

}
