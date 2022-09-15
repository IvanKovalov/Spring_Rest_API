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

    static final String RECEIVED_ID = "Received task's id";


    public TaskEntity updateTask(TaskDTO taskDTO) {

        logger.info(RECEIVED_ID);
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

    public void deleteTaskById (int id) {

        logger.info(RECEIVED_ID);
        repository.deleteById(id);
        logger.info("Deleted task by id {}", id);

    }

    public TaskEntity updateStatus(TaskStatusDTO taskStatusDTO){
        logger.info("Received taskStatus dto");
        TaskEntity task = repository.findById(taskStatusDTO.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Task not found on :: "+ taskStatusDTO.getId()));
        logger.info("Take taskEntity from db");
        task.setStatus(changeStatus.changeStatus(task.getStatus(),taskStatusDTO.getStatus()));
        task.setLastUpdate(new Date());
        logger.info("Updated task's status and lastUpdate");
        return repository.save(task);

    }

    public TaskEntity getTaskById (int id) {

        logger.info(RECEIVED_ID);
        TaskEntity getTask = repository.getReferenceById(id);
        logger.info("Get task by id {}", id);
        return getTask;
    }

}
