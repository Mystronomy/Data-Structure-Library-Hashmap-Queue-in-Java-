// ReverseGLLIterator.java
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * ReverseGLLIterator class provides an implementation of the Iterator interface
 * to iterate over a generic linked list in reverse order (tail to head).
 * This is achieved using a stack to store the elements in reverse.
 */
public class ReverseGLLIterator<T> implements Iterator<T> {
    // Stack to hold the elements of the list in reverse order
    private Stack<T> stack;

    /**
     * Constructor initializes the iterator by traversing the linked list
     * from head to tail and pushing each element onto the stack.
     * This allows the iteration to occur in reverse order (tail to head).
     * 
     * @param head The head node of the linked list.
     */
    public ReverseGLLIterator(GenericList<T>.Node<T> head) {
        stack = new Stack<>();  // Initialize the stack
        GenericList<T>.Node<T> current = head;
        // Traverse the list from head to tail, pushing each element onto the stack
        while (current != null) {
            stack.push(current.data);
            current = current.next;
        }
    }

    /**
     * Checks if there are more elements in the stack to iterate over.
     * 
     * @return true if there are more elements, false otherwise.
     */
    @Override
    public boolean hasNext() {
        return !stack.isEmpty();  // Return true if the stack is not empty
    }

    /**
     * Returns the next element in the reverse iteration and removes it from the stack.
     * 
     * @return The next element in reverse order.
     * @throws NoSuchElementException if there are no more elements to iterate over.
     */
    @Override
    public T next() {
        if (!hasNext()) {
            // Throw an exception if there are no more elements to iterate
            throw new NoSuchElementException("No more elements to iterate.");
        }
        return stack.pop();  // Return the top element from the stack (reverse order)
    }
}
