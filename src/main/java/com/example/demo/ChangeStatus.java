package com.example.demo;

public class ChangeStatus {
    public TaskStatus changeStatus(TaskStatus taskStatus, String status){
        if(taskStatus.equals(TaskStatus.Cancelled)){
            return TaskStatus.Cancelled;
        } else if (taskStatus.equals(TaskStatus.Done)) {
            return TaskStatus.Done;
        }
        switch (status){
            case ("next"):
                if(taskStatus.equals(TaskStatus.Planned)) {
                    return TaskStatus.Work_in_progress;
                }
                return TaskStatus.Done;
            case ("cancel"):
                return TaskStatus.Cancelled;
        }
        return TaskStatus.Planned;
    }
}
