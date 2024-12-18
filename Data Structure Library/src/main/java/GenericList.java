// GenericList.java
import java.util.Iterator;
import java.util.ArrayList;

/**
 * Abstract generic class representing a generic linked list structure.
 * It implements Iterable<T> to allow iteration over the elements.
 * The list has a head (first element) and a length (number of elements).
 */
public abstract class GenericList<T> implements Iterable<T> {
    // Private data members for the head of the list and the length of the list
    private Node<T> head;
    private int length;

    // Constructor initializes an empty list with no elements
    public GenericList() {
        this.head = null;
        this.length = 0;
    }

    // Getter for the head of the list
    public Node<T> getHead() {
        return head;
    }

    // Setter for the head of the list
    public void setHead(Node<T> head) {
        this.head = head;
    }

    // Getter for the length of the list
    public int getLength() {
        return length;
    }

    // Setter for the length of the list
    public void setLength(int length) {
        this.length = length;
    }

    // Abstract method to add data to the list (to be implemented by subclasses)
    public abstract void add(T data);

    // Abstract method to delete data from the list (to be implemented by subclasses)
    public abstract T delete();

    /**
     * Prints the elements of the list, one element per line.
     * If the list is empty, it prints "Empty List".
     */
    public void print() {
        if (head == null) {
            System.out.println("Empty List");
            return;
        }
        Node<T> current = head;
        while (current != null) {
            System.out.println(current.data);
            current = current.next;
        }
    }

    /**
     * Dumps all the elements of the list into an ArrayList and returns it.
     * This method does not modify the original list.
     * 
     * @return ArrayList containing all elements of the list.
     */
    public ArrayList<T> dumpList() {
        ArrayList<T> list = new ArrayList<>();
        Node<T> current = head;
        while (current != null) {
            list.add(current.data);
            current = current.next;
        }
        return list;
    }    

    /**
     * Returns the element at the specified index in the list.
     * 
     * @param index The index of the element to retrieve.
     * @return The element at the specified index, or null if out of bounds.
     */
    public T get(int index) {
        if (index < 0 || index >= length) {
            return null;
        }
        Node<T> current = head;
        int i = 0;
        while (i < index) {
            current = current.next;
            i++;
        }
        return current.data;
    }

    /**
     * Replaces the element at the specified index in the list with the given element.
     * 
     * @param index The index of the element to replace.
     * @param element The new element to place at the specified index.
     * @return The element previously at the specified index, or null if out of bounds.
     */
    public T set(int index, T element) {
        if (index < 0 || index >= length) {
            return null;
        }
        Node<T> current = head;
        int i = 0;
        while (i < index) {
            current = current.next;
            i++;
        }
        T oldData = current.data;
        current.data = element;
        return oldData;
    }

    /**
     * Returns an iterator to iterate over the elements in this list.
     * This iterator goes from the head to the tail.
     */
    @Override
    public Iterator<T> iterator() {
        return new GLLIterator<>(head);
    }

    /**
     * Returns an iterator to iterate over the elements of the list in reverse order.
     * This iterator goes from the tail to the head.
     */
    public Iterator<T> descendingIterator() {
        return new ReverseGLLIterator<>(head);
    }

    /**
     * Inner class representing a node in the linked list.
     * Each node holds the data, a code, and a reference to the next node.
     */
    public class Node<E> {
        String key;      // Optional key field (used in MyHashMap implementation)
        E data;          // Data stored in the node
        int code;        // Optional code field
        Node<E> next;    // Reference to the next node in the list

        // Constructor for nodes without keys (used in GenericQueue)
        public Node(E data) {
            this.key = null; // Initialize key as null for non-keyed nodes
            this.data = data;
            this.next = null;
            this.code = 0;
        }

        // Constructor for nodes with keys (used in MyHashMap)
        public Node(String key, E data, int code) {
            this.key = key;
            this.data = data;
            this.next = null;
            this.code = code;
        }
    }
}
