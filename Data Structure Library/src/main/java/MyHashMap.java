// MyHashMap.java
import java.util.ArrayList;
import java.util.Iterator;

/**
 * MyHashMap class represents a simplified implementation of a hash map data structure.
 * This class stores key-value pairs using an ArrayList of GenericQueue objects to handle collisions.
 * The key is hashed to determine the index in the map, and collisions are resolved by adding
 * entries to a queue (GenericQueue) at that index.
 */
public class MyHashMap<T> implements Iterable<T> {
    // ArrayList of GenericQueues used to store key-value pairs and handle collisions
    private ArrayList<GenericQueue<T>> map;
    // Number of key-value pairs stored in the hash map
    private int size;

    /**
     * Constructor initializes the hash map with a default size of 10.
     * The first key-value pair is added using the put method.
     * 
     * @param key The key of the first key-value pair.
     * @param value The value associated with the key.
     */
    public MyHashMap(String key, T value) {
        map = new ArrayList<>(10);
        // Initialize the ArrayList with null values
        for (int i = 0; i < 10; i++) {
            map.add(null);
        }
        size = 0;
        put(key, value);  // Add the first key-value pair
    }

    /**
     * Adds or updates a key-value pair in the hash map.
     * If the key already exists, the value is updated. If there is a collision (same index),
     * the key-value pair is added to the queue at that index.
     * 
     * @param key The key associated with the value.
     * @param value The value to be stored.
     * @throws IllegalArgumentException if the key is null.
     */
    public void put(String key, T value) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null.");
        }
        int hashCode = key.hashCode();         // Generate the hash code for the key
        int hashValue = hashCode & 9;          // Use bitwise AND to limit the index range (0-9)
        GenericQueue<T> queue = map.get(hashValue);

        if (queue == null) {
            // If no queue exists at the index, create a new queue with the key, value, and hash code
            queue = new GenericQueue<>(key, value, hashCode);
            map.set(hashValue, queue);
            size++;  // Increment the size of the map
        } else {
            // If a queue exists, check if the key already exists in the queue
            GenericList<T>.Node<T> current = queue.getHead();
            boolean found = false;
            while (current != null) {
                if (current.key != null && current.key.equals(key)) {
                    // Update the value if the key is found
                    current.data = value;
                    found = true;
                    break;
                }
                current = current.next;
            }
            if (!found) {
                // If the key is not found, add a new node with the key, value, and hash code
                queue.add(key, value, hashCode);
                size++;  // Increment the size of the map
            }
        }
    }

    /**
     * Checks if the hash map contains the given key.
     * 
     * @param key The key to check for in the map.
     * @return true if the key exists, false otherwise.
     */
    public boolean contains(String key) {
        if (key == null) {
            return false;
        }
        int hashCode = key.hashCode();
        int hashValue = hashCode & 9;
        GenericQueue<T> queue = map.get(hashValue);

        if (queue == null) {
            return false;  // Return false if no queue exists at the index
        }
        // Traverse the queue to check if the key exists
        GenericList<T>.Node<T> current = queue.getHead();
        while (current != null) {
            if (current.key != null && current.key.equals(key)) {
                return true;  // Return true if the key is found
            }
            current = current.next;
        }
        return false;  // Return false if the key is not found
    }

    /**
     * Retrieves the value associated with the given key.
     * 
     * @param key The key to retrieve the value for.
     * @return The value associated with the key, or null if the key does not exist.
     */
    public T get(String key) {
        if (key == null) {
            return null;
        }
        int hashCode = key.hashCode();
        int hashValue = hashCode & 9;
        GenericQueue<T> queue = map.get(hashValue);

        if (queue == null) {
            return null;  // Return null if no queue exists at the index
        }
        // Traverse the queue to find the value associated with the key
        GenericList<T>.Node<T> current = queue.getHead();
        while (current != null) {
            if (current.key != null && current.key.equals(key)) {
                return current.data;  // Return the value if the key is found
            }
            current = current.next;
        }
        return null;  // Return null if the key is not found
    }

    /**
     * Returns the number of key-value pairs stored in the hash map.
     * 
     * @return The size of the hash map.
     */
    public int size() {
        return size;
    }

    /**
     * Checks if the hash map is empty (contains no key-value pairs).
     * 
     * @return true if the map is empty, false otherwise.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Replaces the value for the given key, if the key exists.
     * 
     * @param key The key whose associated value is to be replaced.
     * @param value The new value to be associated with the key.
     * @return The previous value associated with the key, or null if the key did not exist.
     */
    public T replace(String key, T value) {
        if (key == null) {
            return null;
        }
        int hashCode = key.hashCode();
        int hashValue = hashCode & 9;
        GenericQueue<T> queue = map.get(hashValue);

        if (queue == null) {
            return null;  // Return null if no queue exists at the index
        }
        // Traverse the queue to replace the value associated with the key
        GenericList<T>.Node<T> current = queue.getHead();
        while (current != null) {
            if (current.key != null && current.key.equals(key)) {
                T oldValue = current.data;
                current.data = value;  // Replace the old value with the new value
                return oldValue;       // Return the old value
            }
            current = current.next;
        }
        return null;  // Return null if the key is not found
    }

    /**
     * Returns an iterator to iterate over the values stored in the hash map.
     * 
     * @return An iterator for the hash map.
     */
    @Override
    public Iterator<T> iterator() {
        return new HMIterator<>(map);  // Return an HMIterator to iterate over the map
    }
}
