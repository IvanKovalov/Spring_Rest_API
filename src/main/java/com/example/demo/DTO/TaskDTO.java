package com.example.demo.DTO;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class TaskDTO {


    private int id;


    @NotBlank(message = "Not Valid name")
    private String name;

    @NotNull
    private String description;


    public void setName(String name) {
        this.name = name;
    }

    @Positive
    @NotNull
    @Min(2)
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
