package com.example.demo.controller;


import com.example.demo.DTO.TaskDTO;
import com.example.demo.DTO.TaskStatusDTO;
import com.example.demo.entity.TaskEntity;
import com.example.demo.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;


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
    public ResponseEntity add(@Valid @RequestBody TaskDTO taskDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body("Bad request");
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
    public ResponseEntity updateTask(@Valid @RequestBody TaskDTO taskDetails, BindingResult bindingResult)  {

        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body("Bad request");
        }
        final TaskEntity updatedTask = taskService.updateTask(taskDetails);
        return ResponseEntity.ok(updatedTask);
    }

    @PutMapping("/taskStatus")
    public ResponseEntity updateStatus(@Valid @RequestBody TaskStatusDTO taskStatusDTO, BindingResult bindingResult)  {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body("Bad request");
        }
        final TaskEntity updatedTask = taskService.updateStatus(taskStatusDTO);
        return ResponseEntity.ok(updatedTask);
    }

}
