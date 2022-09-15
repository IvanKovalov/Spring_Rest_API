package com.example.demo.controller;


import com.example.demo.DTO.TaskDTO;
import com.example.demo.DTO.TaskStatusDTO;
import com.example.demo.entity.TaskEntity;
import com.example.demo.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


@RestController
public class TaskController {
    @Autowired
    private TaskService taskService;

    private static final String BAD_REQUEST_TASK_DTO = "Bad request: Invalid TaskDTO contributed. Task DTO example {'id':1, 'name':'change smth', 'description':'ToDo'}";

    @Operation(summary = "Get all tasks")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Show all tasks",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TaskEntity.class)) }),
            @ApiResponse(responseCode = "404", description = "Tasks not found",
                    content = @Content) })
    @GetMapping("/tasks")
    Iterable<TaskEntity> all() {
        return taskService.getAllTasks();
    }

    @Operation(summary = "Get one tasks")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Show task",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TaskEntity.class)) }),
            @ApiResponse(responseCode = "404", description = "Task not found",
                    content = @Content) })
    @GetMapping("/tasks/{id}")
    public ResponseEntity getTaskById(@PathVariable int id) {
        return ResponseEntity.ok(taskService.getTaskById(id));
    }





    @Operation(summary = "Add task")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Task added",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TaskEntity.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid data supplied",
                    content = @Content),
            @ApiResponse(responseCode = "401", description = "U unauthorized, unknown user name or invalid password",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Ur user not enough rights",
                    content = @Content) })
    @PostMapping("/task")
    public ResponseEntity add(@Valid @RequestBody TaskDTO taskDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(BAD_REQUEST_TASK_DTO);
        }

        final TaskEntity updatedTask = taskService.addTask(taskDTO);

        return ResponseEntity.ok(updatedTask);
    }


  //  @PreAuthorize("hasRole('ADMIN')")


    @Operation(summary = "Delete task")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Task deleted",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TaskEntity.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "401", description = "U unauthorized, unknown user name or invalid password",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Ur user not enough rights",
                    content = @Content) })
    @DeleteMapping("/task")
    public ResponseEntity<Integer> deleteTask(@RequestParam(value = "Id") int id) {

        taskService.deleteTaskById(id);

        return ResponseEntity.ok(id);
    }

    @Operation(summary = "Update task")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Task updated",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TaskEntity.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid data supplied",
                    content = @Content),
            @ApiResponse(responseCode = "401", description = "U unauthorized, unknown user name or invalid password",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Ur user not enough rights",
                    content = @Content) })
    @PutMapping("/task")
    public ResponseEntity updateTask(@Valid @RequestBody TaskDTO taskDetails, BindingResult bindingResult)  {

        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(BAD_REQUEST_TASK_DTO);
        }
        final TaskEntity updatedTask = taskService.updateTask(taskDetails);

        return ResponseEntity.ok(updatedTask);
    }

    @Operation(summary = "Update task's status")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Task's status updated",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TaskEntity.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid data:id or status supplied. Status must be: next or cancel",
                    content = @Content),
            @ApiResponse(responseCode = "401", description = "U unauthorized, unknown user name or invalid password",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Ur user not enough rights",
                    content = @Content) })
    @PutMapping("/taskStatus")
    public ResponseEntity updateStatus(@Valid @RequestBody TaskStatusDTO taskStatusDTO, BindingResult bindingResult)  {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body("{'error':'Bad request Invalid TaskStatusDTO contributed', 'example DTO' :TaskStatus DTO example {'id':1, 'status':'next'}}");
        }
        final TaskEntity updatedTask = taskService.updateStatus(taskStatusDTO);

        return ResponseEntity.ok(updatedTask);
    }

}
