package com.example.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

 class ChangeStatusTest {
    TaskStatus taskStatus;
    ChangeStatus changeStatus = new ChangeStatus();

    @BeforeEach
    public void createTaskStatus(){
        this.taskStatus = TaskStatus.PLANNED;
    }


    @Test
    void workInProgressStatusTest(){
        assertEquals( TaskStatus.WORK_IN_PROGRESS,changeStatus.changeStatus(taskStatus, "nextStatus"));
    }

    @Test
    void doneStatusTest(){
        TaskStatus taskStatusWIP = changeStatus.changeStatus(taskStatus, "nextStatus");
        assertEquals(TaskStatus.DONE, changeStatus.changeStatus(taskStatusWIP, "nextStatus"));
    }

    @Test
     void ifAlreadyDoneStatusTest(){
        TaskStatus taskStatusWIP = changeStatus.changeStatus(taskStatus, "nextStatus");
        TaskStatus doneStatus = changeStatus.changeStatus(taskStatusWIP, "nextStatus");
        assertEquals(TaskStatus.DONE, changeStatus.changeStatus(doneStatus, "nextStatus"));
    }

    @Test
    void cancelledStatusTest(){
        assertEquals(TaskStatus.CANCELLED, changeStatus.changeStatus(taskStatus, "cancel"));
    }

    @Test
     void ifAlreadyCancelledStatusTest(){
        TaskStatus cancelledStatus = changeStatus.changeStatus(taskStatus, "cancel");
        assertEquals(TaskStatus.CANCELLED, changeStatus.changeStatus(cancelledStatus, "cancel"));
    }

    @Test
    void incorrectStatusTest(){

        assertEquals(TaskStatus.PLANNED, changeStatus.changeStatus(taskStatus, "cance"));
    }

}
