package com.example.demo.DTO;


import javax.validation.constraints.*;

public class TaskDTO {


    private int id;


    @NotBlank(message = "Not Valid name")
    private String name;

    @NotBlank(message = "Task's description must be not blank")
    @Size(max = 250, message = "Task's size must be not null")
    private String description;


    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    @NotBlank
    public String getName() {
        return name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @NotBlank
    public String getDescription() {
        return description;
    }
}
