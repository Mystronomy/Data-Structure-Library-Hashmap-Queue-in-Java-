// GenericQueue.java
import java.util.Iterator;

/**
 * GenericQueue class that represents a queue (FIFO - First In, First Out) data structure.
 * This class extends the abstract class GenericList<T> and uses linked nodes.
 * It supports generic data types and includes special methods for MyHashMap usage.
 */
public class GenericQueue<T> extends GenericList<T> {
    // Reference to the tail (end) of the queue
    private Node<T> tail;

    // No-argument constructor for creating an empty queue
    public GenericQueue() {
        this.setHead(null);
        this.tail = null;
        this.setLength(0);
    }

    /**
     * Constructor for initializing a generic queue with one element.
     * 
     * @param data The data for the first node in the queue.
     */
    public GenericQueue(T data) {
        Node<T> newNode = new Node<>(data);  // Create a new node with the provided data
        this.setHead(newNode);               // Set the new node as the head (and tail, since it's the only element)
        this.tail = newNode;
        this.setLength(1);                   // Queue length is 1 after initialization
    }

    /**
     * Constructor for initializing a generic queue with a key, data, and code.
     * This constructor is primarily used for the MyHashMap class to handle key-value pairs.
     * 
     * @param key The key associated with the node.
     * @param data The data to be stored in the node.
     * @param code The hash code associated with the key.
     */
    public GenericQueue(String key, T data, int code) {
        Node<T> newNode = new Node<>(key, data, code);  // Create a new node with key, data, and code
        this.setHead(newNode);                          // Set the new node as the head and tail
        this.tail = newNode;
        this.setLength(1);                              // Queue length is 1 after initialization
    }

    // Getter for the tail of the queue
    public Node<T> getTail() {
        return tail;
    }

    /**
     * Adds a new element to the back of the queue (FIFO).
     * 
     * @param data The data to be added to the queue.
     */
    @Override
    public void add(T data) {
        Node<T> newNode = new Node<>(data);  // Create a new node with the provided data
        if (this.getHead() == null) {
            this.setHead(newNode);           // If the queue is empty, set the new node as both head and tail
            tail = newNode;
        } else {
            tail.next = newNode;             // Otherwise, add the new node to the end and update the tail
            tail = newNode;
        }
        this.setLength(this.getLength() + 1);  // Increment the length of the queue
    }

    /**
     * Adds a new element with a key and code to the back of the queue.
     * This method is primarily used by MyHashMap to handle key-value pairs.
     * 
     * @param key The key associated with the node.
     * @param data The data to be added to the queue.
     * @param code The hash code associated with the key.
     */
    public void add(String key, T data, int code) {
        Node<T> newNode = new Node<>(key, data, code);  // Create a new node with key, data, and code
        if (this.getHead() == null) {
            this.setHead(newNode);                      // If the queue is empty, set the new node as both head and tail
            tail = newNode;
        } else {
            tail.next = newNode;                        // Otherwise, add the new node to the end and update the tail
            tail = newNode;
        }
        this.setLength(this.getLength() + 1);  // Increment the length of the queue
    }

    /**
     * Deletes and returns the element from the front of the queue (FIFO).
     * 
     * @return The data from the front of the queue, or null if the queue is empty.
     */
    @Override
    public T delete() {
        if (this.getHead() == null) {
            return null;  // Return null if the queue is empty
        }

        T deletedData = this.getHead().data;      // Get the data from the head (first node)
        this.setHead(this.getHead().next);        // Move the head to the next node
        if (this.getHead() == null) {
            tail = null;  // If the queue is now empty, set the tail to null
        }
        this.setLength(this.getLength() - 1);     // Decrement the length of the queue
        return deletedData;                       // Return the deleted data
    }

    /**
     * Enqueues (adds) an element to the back of the queue.
     * 
     * @param data The data to be added.
     */
    public void enqueue(T data) {
        add(data);  // Enqueue is simply an alias for adding to the back of the queue
    }

    /**
     * Dequeues (removes) and returns the element from the front of the queue.
     * 
     * @return The data from the front of the queue, or null if the queue is empty.
     */
    public T dequeue() {
        return delete();  // Dequeue is simply an alias for deleting from the front of the queue
    }

    /**
     * Returns an iterator to iterate over the elements of the queue.
     * This iterator goes from head to tail.
     * 
     * @return An iterator for the queue.
     */
    @Override
    public Iterator<T> iterator() {
        return new GLLIterator<>(this.getHead());
    }

    /**
     * Returns an iterator to iterate over the elements of the queue in reverse order.
     * This iterator goes from tail to head.
     * 
     * @return A reverse iterator for the queue.
     */
    @Override
    public Iterator<T> descendingIterator() {
        return new ReverseGLLIterator<>(this.getHead());
    }
}
