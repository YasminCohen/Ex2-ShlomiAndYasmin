In the first part of the assignment we were asked to compare the running times of methods 4,3,2 and explain in detail why the running times are different or equal.<br />
For this we took a large amount of text files and ran them in three ways.<br />
The first method that does not use a thread or a threadPool took the most time, because it did all the calculations in one thread.<br />
The second method that uses normal threads took more or less the same time as the third method that uses a threadpool.<br />
But on average the threadpool runs a little faster than the method with the threads.<br />
This happened because every time creating normal threads takes more time than creating a threadpool.<br />
In the second part we built a class which is a type of threadpool with priority.<br />
And we also built a Task class that is adapted to threadpool with priority. .<br />
CustomExecutor: .<br />
This is a custom implementation of the ThreadPoolExecutor class in Java that uses a PriorityBlockingQueue for task prioritization. The class is called CustomExecutor.<br />

The CustomExecutor class provides several additional methods for submitting tasks and gracefully shutting down the executor.<br />

Creating a CustomExecutor<br />
To create a new CustomExecutor, you can use the default constructor:<br />

CustomExecutor executor = new CustomExecutor();<br />
This constructor creates a new CustomExecutor with the number of threads equal to half the number of available processors, the maximum number of threads equal to one less than the number of available processors, a keep-alive time of 300 milliseconds, and a PriorityBlockingQueue for task prioritization. The maxPrio is initialized to 0.<br />

Submitting a task<br />
To submit a task to the executor, you can use one of the following methods:<br />

Future<T> submit(Task<T> task);<br />
Future<V> submit(Callable<V> task, TaskType type);<br />
Future<V> submit(Callable<V> task);<br />

The first method submits a task to the executor with a given priority. The maxPrio is updated to the minimum of the current maxPrio and the task's priority. The task is wrapped in a DesignAdapter, which is then executed by the super class.<br />

The second method submits a task to the executor with a given TaskType. The task is wrapped in a Task and then submitted.<br />

The third method submits a task to the executor without a TaskType. The task is wrapped in a Task and then submitted.<br />
 beforeExecute() This method executed before a task have been executed. .<br />
 
Gracefully Terminating the Executor<br />
To gracefully terminate the executor, you can use the following method:<br />

void gracefullyTerminate();<br />
This method gracefully terminates the executor by shutting it down, waiting for tasks to complete for 800 milliseconds, and then shutting down now if tasks have not completed.<br />

int getCurrentMax();<br /> Returns the current maximum priority of tasks submitted to this executor.<br />





