# TaskScheduler

`TaskScheduler` is a utility class that allows you to schedule and run tasks concurrently using a fixed thread pool, while also providing mechanisms for thread synchronization through locking. It ensures that tasks can be run safely when accessing shared resources, using a `ReentrantLock` to manage concurrent access.

## Features

- **Concurrent Task Execution**: Uses a fixed thread pool to execute tasks concurrently.
- **Thread Synchronization**: Provides a locking mechanism (`ReentrantLock`) to control access to shared resources between multiple tasks.
- **Graceful Shutdown**: Allows for proper shutdown of the thread pool.

## Requirements

- **Java** 8 or later

## Getting Started

### Installation

Clone or download the repository containing the `TaskScheduler` class, then add it to your Java project.

### Usage

1. **Instantiate `TaskScheduler`**:
   You can create an instance of `TaskScheduler` by specifying the number of threads in the pool.

    ```java
    TaskScheduler scheduler = new TaskScheduler(4);  // Creates a pool with 4 threads
    ```

2. **Scheduling Tasks**:
   You can schedule tasks by calling the `scheduleTask()` method and passing a `Task` object that implements `Runnable`.

    ```java
    Task task1 = () -> {
        // Task logic here
        System.out.println("Task 1 is running");
    };
    
    Task task2 = () -> {
        // Task logic here
        System.out.println("Task 2 is running");
    };

    scheduler.scheduleTask(task1);
    scheduler.scheduleTask(task2);
    ```

3. **Thread Synchronization**:
   The `TaskScheduler` uses a `ReentrantLock` to synchronize the execution of tasks. Only one task at a time can execute within the locked section, ensuring thread safety when accessing shared resources.

    ```java
    executor.execute(() -> {
        lock.lock();  // Acquire lock
        try {
            task.run();
        } finally {
            lock.unlock();  // Release lock
        }
    });
    ```

4. **Shutdown**:
   After all tasks are scheduled, call the `shutdown()` method to properly shut down the thread pool.

    ```java
    scheduler.shutdown();
    ```

### Example

Here’s a complete example of how to use `TaskScheduler` to schedule and execute multiple tasks concurrently:

```java
public class Main {
    public static void main(String[] args) {
        TaskScheduler scheduler = new TaskScheduler(4);

        Task task1 = () -> {
            System.out.println("Task 1 is running");
        };

        Task task2 = () -> {
            System.out.println("Task 2 is running");
        };

        scheduler.scheduleTask(task1);
        scheduler.scheduleTask(task2);

        scheduler.shutdown();
    }
}
```
