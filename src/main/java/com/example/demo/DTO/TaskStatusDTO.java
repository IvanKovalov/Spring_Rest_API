package com.example.demo.DTO;

import java.util.Objects;

public class TaskStatusDTO {
    private int id;
    private String status;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "TaskStatusDTO{" +
                "id=" + id +
                ", status='" + status + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskStatusDTO that = (TaskStatusDTO) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
