package com.example.demo;

public class ChangeStatus {
    public TaskStatus changeStatus(TaskStatus taskStatus, String expectedStatus){
        if(taskStatus.equals(TaskStatus.CANCELLED)){
            return TaskStatus.CANCELLED;
        } else if (taskStatus.equals(TaskStatus.DONE)) {
            return TaskStatus.DONE;
        }
        switch (expectedStatus){
            case ("nextStatus"):
                if(taskStatus.equals(TaskStatus.PLANNED)) {
                    return TaskStatus.WORK_IN_PROGRESS;
                }
                return TaskStatus.DONE;
            case ("cancel"):
                return TaskStatus.CANCELLED;
            default:
                return TaskStatus.PLANNED;
        }

    }
}
