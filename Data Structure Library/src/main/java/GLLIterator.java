// GLLIterator.java
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * GLLIterator class provides an implementation of the Iterator interface
 * to iterate over a generic linked list from head to tail.
 */
public class GLLIterator<T> implements Iterator<T> {
    // Reference to the current node in the list being iterated over
    private GenericList<T>.Node<T> current;

    /**
     * Constructor initializes the iterator with the head of the list.
     * 
     * @param head The head node of the list to start iterating from.
     */
    public GLLIterator(GenericList<T>.Node<T> head) {
        this.current = head;
    }

    /**
     * Checks if there is another element in the list.
     * 
     * @return true if there is a next element, false otherwise.
     */
    @Override
    public boolean hasNext() {
        return current != null;  // Returns true if the current node is not null
    }

    /**
     * Returns the next element in the list and advances the iterator.
     * 
     * @return The next element in the list.
     * @throws NoSuchElementException if there are no more elements to iterate over.
     */
    @Override
    public T next() {
        if (!hasNext()) {
            // Throw an exception if there is no next element
            throw new NoSuchElementException("No more elements to iterate.");
        }
        T data = current.data;    // Retrieve the data from the current node
        current = current.next;   // Move to the next node in the list
        return data;              // Return the current node's data
    }
}
