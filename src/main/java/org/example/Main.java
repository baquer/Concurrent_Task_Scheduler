package org.example;

import java.util.concurrent.locks.ReentrantLock;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        TaskScheduler taskScheduler = new TaskScheduler(10);
        ReentrantLock reentrantLock = new ReentrantLock();
        for(int i = 0; i < 20; i++) {
            Task task = new Task(i, i * 1000);
            taskScheduler.scheduleTask(task, reentrantLock);
        }
        taskScheduler.shutdown();
    }
}