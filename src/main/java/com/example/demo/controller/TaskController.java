package com.example.demo.controller;


import com.example.demo.entity.TaskEntity;
import com.example.demo.repository.TaskRepository;
import com.example.demo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
public class TaskController {
    @Autowired
    private TaskService taskService;

    @Autowired
    private TaskRepository repository;



   /* void TaskController(TaskRepository repository) {
        this.repository = repository;
    }*/


    @GetMapping("/tasks")
    Iterable<TaskEntity> all() {
        return taskService.getAllTasks();
    }

    @PostMapping("/task")
    public void add( @RequestParam(value = "name") String name , @RequestParam(value = "description") String description) {

        taskService.addTask(name,description);
    }


    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/demo")
    public String demo(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }

    @DeleteMapping("/task")
    public ResponseEntity<Integer> deleteTask(@RequestParam(value = "Id") int Id) {

        taskService.deleteTaskById(Id);
        return ResponseEntity.ok(Id);
    }

    @PutMapping("/tasks/{id}")
    public ResponseEntity<TaskEntity> updateTask( @RequestBody TaskEntity taskDetails)  {

        final TaskEntity updatedTask = taskService.updateTask(taskDetails);
        return ResponseEntity.ok(updatedTask);
    }
}
