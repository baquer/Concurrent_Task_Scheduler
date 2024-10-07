package org.example;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Task implements Runnable {

    // This task has a taskId and the execution time
    public static final Logger logger = Logger.getLogger(Task.class.getName());

    private final int taskId;
    public final long executionTime;

    Task(int taskId, long executionTime) {
        this.taskId = taskId;
        this.executionTime = executionTime;
    }


    @Override
    public void run() {

        long startTime = System.currentTimeMillis();
        logger.log(Level.INFO, "Task {0} started at time {1}", new Object[] {taskId, System.currentTimeMillis()});

        try {
            Thread.sleep(executionTime);
        } catch (InterruptedException e) {
            logger.log(Level.INFO, "Task {0} gets interrupted due to this error {1}", new Object[]{taskId, e.getMessage()});
            throw new RuntimeException(e);
        }

        long endTime = System.currentTimeMillis();

        long duration = endTime - startTime;

        logger.log(Level.INFO, "Task {0} takes {1} duration time", new Object[]{taskId, duration});
    }
}
