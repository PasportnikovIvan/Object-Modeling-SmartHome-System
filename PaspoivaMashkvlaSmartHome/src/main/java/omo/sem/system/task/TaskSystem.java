package omo.sem.system.task;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Class representing task system.
 */
public class TaskSystem {
    private final Queue<Task> tasks = new LinkedList<>();

    /**
     * Add task to the queue.
     * @param task task to add
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Returns task from the queue.
     * @return task
     */
    public Task getTask() {
        return tasks.poll();
    }

    /**
     * Checks if queue is empty.
     * @return true if empty
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }
}
