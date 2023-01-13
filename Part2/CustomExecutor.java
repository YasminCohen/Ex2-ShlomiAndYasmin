package Part2;

import java.util.concurrent.*;
/**

 CustomExecutor is a custom implementation of ThreadPoolExecutor that uses a PriorityBlockingQueue for task prioritization.

 It also has several additional methods for submitting tasks and gracefully shutting down the executor.
 */
public class CustomExecutor extends ThreadPoolExecutor {

    /**

     Creates a new CustomExecutor with the number of threads equal to half the number of available processors,
     the maximum number of threads equal to one less than the number of available processors,
     a keep-alive time of 300 milliseconds, and a PriorityBlockingQueue for task prioritization.
     The maxPrio is initialized to 0.
     */

    private int[] arrayOfPriorty;

    public CustomExecutor() {
        super(Runtime.getRuntime().availableProcessors() / 2,
                Runtime.getRuntime().availableProcessors() - 1,
                300, TimeUnit.MILLISECONDS,
                new PriorityBlockingQueue<>());
        arrayOfPriorty = new int[11];
    }
    /**

     Submits a task to the executor with a given priority.
     The maxPrio is updated to the minimum of the current maxPrio and the task's priority.
     The task is wrapped in a DesignAdapter, which is then executed by the super class.
     @param task The task to be submitted with a given priority
     @return A Future representing the task
     */
    public <T> Future<T> submit(Task<T> task) {
        DesignAdapter<T> adpt = new DesignAdapter<>(task, task.getPrio());
        arrayOfPriorty[task.getPrio()]++;
        super.execute(adpt);
        return adpt;
    }
    /**

     Submits a task to the executor with a given TaskType.
     The task is wrapped in a Task and then submitted.
     @param task The task to be submitted
     @param type The TaskType of the task
     @return A Future representing the task
     */
    public <V> Future<V> submit(Callable<V> task, TaskType type) {
        return this.submit(Task.createTask(task, type));
    }
    /**
     Submits a task to the executor without a TaskType.
     The task is wrapped in a Task and then submitted.
     @param task The task to be submitted
     @return A Future representing the task
     */
    public <V> Future<V> submit(Callable<V> task) {
        return this.submit(Task.createTask(task));
    }
    /**
     *
     * @param t the thread that will run task {@code r}
     * @param r the task that will be executed
     */
    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        arrayOfPriorty[((DesignAdapter) r).getPriority()]--;
        }

    /**
     Gracefully terminates the executor by shutting it down,
     waiting for tasks to complete for 800 milliseconds,
     and then shutting down now if tasks have not completed.
     */
    public void gracefullyTerminate() {
        this.shutdown();
        try {
            if (!this.awaitTermination(800, TimeUnit.MILLISECONDS)) {
                this.shutdownNow();
            }
        } catch (InterruptedException e) {
            this.shutdownNow();
        }
    }
    /**

     Returns the current maximum priority of tasks submitted to this executor.
     @return the current maximum priority of tasks.
     */
    public int getCurrentMax() { //O(1)
        int i = 1;
        while(i<=10){
            if (this.arrayOfPriorty[i] >= 1)
                return i;
            i++;
        }
            return 0;
        }
    /**
     *
     Returns a hash code value for the object.
     This method is supported for the benefit of hash tables such as those provided by HashMap.
     @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        return super.hashCode();
    }
    /**

     Indicates whether some other object is "equal to" this one.
     @param obj the reference object with which to compare.
     @return true if this object is the same as the obj argument; false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
    /**

     Returns a string representation of the object.
     The output will include the current maximum priority of tasks submitted to this executor.
     @return a string representation of the object.
     */
    @Override
    public String toString() {
        return "CustomExecutor { " +
                "currentMaxPrio: " + getCurrentMax() +
                " }";
    }
}



