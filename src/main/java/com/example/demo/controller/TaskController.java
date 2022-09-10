package com.example.demo.controller;


import com.example.demo.DTO.TaskDTO;
import com.example.demo.DTO.TaskStatusDTO;
import com.example.demo.entity.TaskEntity;
import com.example.demo.repository.TaskRepository;
import com.example.demo.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


@RestController
public class TaskController {
    @Autowired
    private TaskService taskService;

    final static Logger logger = LoggerFactory.getLogger(TaskController.class);

    @GetMapping("/tasks")
    Iterable<TaskEntity> all() {
        return taskService.getAllTasks();
    }

    @PostMapping("/task")
    public ResponseEntity<TaskEntity> add(@Valid @RequestBody TaskDTO taskDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
           /* TaskEntity task = new TaskEntity(taskDTO.getName(), taskDTO.getDescription());*/
            return (ResponseEntity<TaskEntity>) ResponseEntity.badRequest();
        }

        final TaskEntity updatedTask = taskService.addTask(taskDTO.getName(),taskDTO.getDescription());
        return ResponseEntity.ok(updatedTask);
    }


  //  @PreAuthorize("hasRole('ADMIN')")


    @DeleteMapping("/task")
    public ResponseEntity<Integer> deleteTask(@RequestParam(value = "Id") int Id) {

        taskService.deleteTaskById(Id);
        return ResponseEntity.ok(Id);
    }

    @PutMapping("/task")
    public ResponseEntity<TaskEntity> updateTask(@Valid @RequestBody TaskDTO taskDetails, BindingResult bindingResult)  {

        final TaskEntity updatedTask = taskService.updateTask(taskDetails);
        return ResponseEntity.ok(updatedTask);
    }

    @PutMapping("/taskStatus")
    public ResponseEntity<TaskEntity> updateStatus(@Valid @RequestBody TaskStatusDTO taskStatusDTO, BindingResult bindingResult)  {

        final TaskEntity updatedTask = taskService.updateStatus(taskStatusDTO);
        return ResponseEntity.ok(updatedTask);
    }

   /* @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }*/
}
