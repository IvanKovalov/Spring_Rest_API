package com.example.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChangeStatusTest {
    TaskStatus taskStatus;
    ChangeStatus changeStatus = new ChangeStatus();

    @BeforeEach
    public void createTaskStatus(){
        this.taskStatus = TaskStatus.PLANNED;
    }


    @Test
    public void workInProgressStatusTest(){
        assertEquals( TaskStatus.WORK_IN_PROGRESS,changeStatus.changeStatus(taskStatus, "nextStatus"));
    }

    @Test
    public void doneStatusTest(){
        TaskStatus taskStatusWIP = changeStatus.changeStatus(taskStatus, "nextStatus");
        assertEquals(changeStatus.changeStatus(taskStatusWIP, "nextStatus"), TaskStatus.DONE);
    }

    @Test
    public void ifAlreadyDoneStatusTest(){
        TaskStatus taskStatusWIP = changeStatus.changeStatus(taskStatus, "nextStatus");
        TaskStatus doneStatus = changeStatus.changeStatus(taskStatusWIP, "nextStatus");
        assertEquals(TaskStatus.DONE, changeStatus.changeStatus(doneStatus, "nextStatus"));
    }

    @Test
    public void cancelledStatusTest(){
        assertEquals(TaskStatus.CANCELLED, changeStatus.changeStatus(taskStatus, "cancel"));
    }

    @Test
    public void ifAlreadyCancelledStatusTest(){
        TaskStatus cancelledStatus = changeStatus.changeStatus(taskStatus, "cancel");
        assertEquals(TaskStatus.CANCELLED, changeStatus.changeStatus(cancelledStatus, "cancel"));
    }

    @Test
    public void incorrectStatusTest(){

        assertEquals(TaskStatus.PLANNED, changeStatus.changeStatus(taskStatus, "cance"));
    }

}
