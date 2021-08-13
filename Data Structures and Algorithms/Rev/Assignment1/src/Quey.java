


public interface Quey<E> {
    /**
     * Returns the number of elements in the queue.
     * @return number of elements in the queue.
     */
    public int size();
    /**
     * Returns whether the queue is empty.
     * @return true if the queue is empty, false otherwise.
     */
    public boolean isEmpty();
    /**
     * Inspects the element at the front of the queue.
     * @return element at the front of the queue.
     * @exception EmptyQueException if the queue is empty.
     */
    public E front() throws EmptyQueException;
    /**
     * Inserts an element at the rear of the queue.
     * @param element new element to be inserted.
     */
    public void enqueue (E element);
    /**
     * Removes the element at the front of the queue.
     * @return element removed.
     * @exception EmptyQueException if the queue is empty.
     */
    public E dequeue() throws EmptyQueException;
}