package com.example.demo.entity;

import com.example.demo.TaskStatus;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
public class TaskEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    private String description;

    @Enumerated(EnumType.STRING)
    private TaskStatus status = TaskStatus.Planned;

    private Date creationTime = new Date();

    private Date lastUpdate = creationTime;

    public TaskEntity() {

    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
        this.lastUpdate = creationTime;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public TaskEntity(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskEntity that = (TaskEntity) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "TaskEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
