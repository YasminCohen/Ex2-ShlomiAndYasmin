package Part2;

import java.util.concurrent.Callable;
/**

 The Task class represents a task that can be submitted to a CustomExecutor.

 It wraps a Callable and assigns it a priority value based on a provided TaskType.

 Tasks can be compared based on their priority value.

 @param <V> the type of the result returned by the task.
 */
public class Task<V> implements Callable<V>, Comparable<Task<V>>{
    /**

     The Callable being wrapped by this task.
     */
    private Callable<V> c;
    /**

     The priority value of this task.
     */
    private int prio;
    /**

     Creates a new Task wrapping the provided Callable and assigns it the priority value of the provided TaskType.
     @param c the callable to be wrapped
     @param taskType the type of task
     */
    private Task(Callable<V> c, TaskType taskType){
        this.c = c;
        this.prio = taskType.getPriorityValue();
    }
    /**

     Creates a new Task wrapping the provided Callable and assigns it the priority value of the provided TaskType.
     @param c the callable to be wrapped
     @param taskType the type of task
     @return a new task with the given callable and taskType priority
     */
    public static Task createTask(Callable c, TaskType taskType){
      return new Task(c, taskType);
    }
    /**

     Creates a new Task wrapping the provided Callable and assigns it the priority value of TaskType.OTHER.
     @param c the callable to be wrapped
     @return a new task with the given callable and TaskType.OTHER priority
     */
    public static Task createTask(Callable c){
        return new Task(c, TaskType.OTHER);
    }
    /**

     Returns the priority of this task
     @return the priority of this task
     */
    public int getPrio() {
        return this.prio;
    }
    /**

     Returns a string representation of this task.
     @return a string representation of this task
     */
    @Override
    public String toString() {
        return "Task{" + this.prio + "}";
    }
    /**

     Calls the wrapped callable.
     @return the result of the callable
     @throws Exception if the callable throws an exception
     */
    @Override
    public V call() throws Exception {
        return this.c.call();
    }
    /**

     Compares this task to another task based on their priority value.
     @param o the task to compare to
     @return a negative integer, zero, or a positive integer as this task has a lower priority, the
     same priority, or a higher priority than the task to be compared
     */
    @Override
    public int compareTo(Task<V> o) {
        return Integer.compare(this.prio, o.prio);
    }
}
