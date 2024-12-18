// HMIterator.java
import java.util.Iterator;
import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * HMIterator class provides an implementation of the Iterator interface
 * to iterate over the key-value pairs stored in a custom MyHashMap.
 * This iterator traverses the GenericQueues stored in the ArrayList map.
 */
public class HMIterator<T> implements Iterator<T> {
    // The map (ArrayList of GenericQueues) being iterated over
    private ArrayList<GenericQueue<T>> map;
    // Current index of the ArrayList being iterated over
    private int index;
    // Iterator for the current GenericQueue being iterated over
    private Iterator<T> currentIterator;

    /**
     * Constructor initializes the iterator with the map to iterate over.
     * 
     * @param map The ArrayList of GenericQueues representing the internal structure of MyHashMap.
     */
    public HMIterator(ArrayList<GenericQueue<T>> map) {
        this.map = map;
        this.index = 0;
        this.currentIterator = null;
        advanceToNextNonEmptyQueue();  // Move to the first non-empty queue
    }

    /**
     * Advances the iterator to the next non-empty GenericQueue in the map.
     * This method ensures the iterator skips over empty queues.
     */
    private void advanceToNextNonEmptyQueue() {
        while (index < map.size()) {
            GenericQueue<T> queue = map.get(index);
            if (queue != null && queue.getHead() != null) {
                currentIterator = queue.iterator();  // Set the iterator for the current queue
                return;
            }
            index++;  // Move to the next queue if the current one is empty
        }
        currentIterator = null;  // Set to null if no non-empty queues are found
    }

    /**
     * Checks if there is another element in the map to iterate over.
     * 
     * @return true if there is a next element, false otherwise.
     */
    @Override
    public boolean hasNext() {
        // If current iterator is exhausted, move to the next non-empty queue
        while ((currentIterator == null || !currentIterator.hasNext()) && index < map.size()) {
            index++;
            advanceToNextNonEmptyQueue();
        }
        return currentIterator != null && currentIterator.hasNext();  // Return true if there is a next element
    }

    /**
     * Returns the next element in the map and advances the iterator.
     * 
     * @return The next element in the map.
     * @throws NoSuchElementException if there are no more elements to iterate over.
     */
    @Override
    public T next() {
        if (!hasNext()) {
            // Throw an exception if there are no more elements
            throw new NoSuchElementException("No more elements to iterate.");
        }
        return currentIterator.next();  // Return the next element from the current queue
    }
}
