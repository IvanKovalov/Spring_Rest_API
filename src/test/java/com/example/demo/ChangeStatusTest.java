package com.example.demo;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChangeStatusTest {
    TaskStatus taskStatus;
    ChangeStatus changeStatus = new ChangeStatus();

    @BeforeEach
    public void createTaskStatus(){
        this.taskStatus = TaskStatus.Planned;
    }


    @Test
    public void workInProgressStatusTest(){
        assertEquals( TaskStatus.Work_in_progress ,changeStatus.changeStatus(taskStatus, "next"));
    }

    @Test
    public void doneStatusTest(){
        TaskStatus taskStatusWIP = changeStatus.changeStatus(taskStatus, "next");
        assertEquals(changeStatus.changeStatus(taskStatusWIP, "next"), TaskStatus.Done);
    }

    @Test
    public void ifAlreadyDoneStatusTest(){
        TaskStatus taskStatusWIP = changeStatus.changeStatus(taskStatus, "next");
        TaskStatus doneStatus = changeStatus.changeStatus(taskStatusWIP, "next");
        assertEquals(TaskStatus.Done, changeStatus.changeStatus(doneStatus, "next"));
    }

    @Test
    public void cancelledStatusTest(){
        assertEquals(TaskStatus.Cancelled, changeStatus.changeStatus(taskStatus, "cancel"));
    }

    @Test
    public void ifAlreadyCancelledStatusTest(){
        TaskStatus cancelledStatus = changeStatus.changeStatus(taskStatus, "cancel");
        assertEquals(TaskStatus.Cancelled, changeStatus.changeStatus(cancelledStatus, "cancel"));
    }

    @Test
    public void incorrectStatusTest(){

        assertEquals(TaskStatus.Planned, changeStatus.changeStatus(taskStatus, "cance"));
    }

}
