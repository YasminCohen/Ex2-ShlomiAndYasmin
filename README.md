README
This is a custom implementation of the ThreadPoolExecutor class in Java that uses a PriorityBlockingQueue for task prioritization. The class is called CustomExecutor.

The CustomExecutor class provides several additional methods for submitting tasks and gracefully shutting down the executor.

Creating a CustomExecutor
To create a new CustomExecutor, you can use the default constructor:

CustomExecutor executor = new CustomExecutor();
This constructor creates a new CustomExecutor with the number of threads equal to half the number of available processors, the maximum number of threads equal to one less than the number of available processors, a keep-alive time of 300 milliseconds, and a PriorityBlockingQueue for task prioritization. The maxPrio is initialized to 0.

Submitting a task
To submit a task to the executor, you can use one of the following methods:

Future<T> submit(Task<T> task);
Future<V> submit(Callable<V> task, TaskType type);
Future<V> submit(Callable<V> task);

The first method submits a task to the executor with a given priority. The maxPrio is updated to the minimum of the current maxPrio and the task's priority. The task is wrapped in a DesignAdapter, which is then executed by the super class.

The second method submits a task to the executor with a given TaskType. The task is wrapped in a Task and then submitted.

The third method submits a task to the executor without a TaskType. The task is wrapped in a Task and then submitted.

Gracefully Terminating the Executor
To gracefully terminate the executor, you can use the following method:

void gracefullyTerminate();
This method gracefully terminates the executor by shutting it down, waiting for tasks to complete for 800 milliseconds, and then shutting down now if tasks have not completed.

Additional Methods
int getCurrentMax(); Returns the current maximum priority of tasks submitted to this executor.

int hashCode(); Returns a hash code value for the object.

boolean equals(Object obj); Indicates whether some other object is "equal to




