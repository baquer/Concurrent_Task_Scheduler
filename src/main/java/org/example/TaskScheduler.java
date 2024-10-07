package org.example;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TaskScheduler {

    private static final Logger logger = Logger.getLogger(TaskScheduler.class.getName());
    private final ExecutorService executor;

    public TaskScheduler(int threadPoolSize) {
        executor = Executors.newFixedThreadPool(threadPoolSize);
    }

    public void scheduleTask(Task task, ReentrantLock reentrantLock) {
        executor.execute(() -> {
            try {
                reentrantLock.lock();
                task.run();
            } catch(Exception exception) {
                logger.log(Level.SEVERE, "Execution got interrupted due to this error {0}", exception.getMessage());
            } finally {
                reentrantLock.unlock();
                logger.log(Level.INFO, "Lock release by task {0}", new Object[]{task.getClass().getName()});
            }
        });
    }

    public void shutdown() {
        executor.shutdown();
    }
}
