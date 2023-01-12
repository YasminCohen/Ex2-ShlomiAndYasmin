package Part2;

import java.util.Objects;
import java.util.concurrent.FutureTask;
/**

 The DesignAdapter class is a FutureTask that also has a priority value.

 It wraps a Task and stores the priority value of that task.

 DesignAdapter objects can be compared based on their priority value.

 @param <T> the type of the result returned by the task.
 */
public class DesignAdapter<T> extends FutureTask<T> implements Comparable<DesignAdapter<T>> {

    /**

     The priority value of the task wrapped by this DesignAdapter.
     */
    private int priority;
    /**

     Creates a new DesignAdapter wrapping the provided Task and assigns it the provided priority value.
     @param task The task to be wrapped
     @param priority the priority value of the task
     */
    public DesignAdapter(Task<T> task, int priority) {
        super(task);
        this.priority = priority;
    }
    /**

     Returns the priority of the task wrapped by this DesignAdapter
     @return the priority of the task wrapped by this DesignAdapter
     */
    public int getPriority() {
        return this.priority;
    }
    /**

     Compares this DesignAdapter to another DesignAdapter based on their priority value.
     @param other the DesignAdapter to compare to
     @return a negative integer, zero, or a positive integer as this DesignAdapter has a lower priority, the
     same priority, or a higher priority than the DesignAdapter to be compared
     */
    @Override
    public int compareTo(DesignAdapter<T> other) {
        return Integer.compare(other.priority, this.priority);
    }
    /**

     Indicates whether some other object is "equal to" this one.
     @param o the reference object with which to compare.
     @return true if this object is the same as the obj
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DesignAdapter<?> that = (DesignAdapter<?>) o;
        return priority == that.priority;
    }
    /**

     Returns a hash code value for the object. This method is
     supported for the benefit of hash tables such as those provided by
     {@link java.util.HashMap}.
     @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(priority);
    }
    /**

     Returns a string representation of this DesignAdapter.
     @return a string representation of this DesignAdapter
     */
    @Override
    public String toString() {
        return "DesignAdapter{" +
                "priority=" + priority +
                '}';
    }
}
