import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class GQTest {

    @Test
    public void testConstructor() {
        GenericQueue<Integer> queue = new GenericQueue<>(10);
        assertEquals(1, queue.getLength(), "Length should be 1 after initialization");
        assertEquals(Integer.valueOf(10), queue.getHead().data, "Head data should be 10");
        assertEquals(Integer.valueOf(10), queue.getTail().data, "Tail data should be 10");
    }

    @Test
    public void testAdd() {
        GenericQueue<Integer> queue = new GenericQueue<>(10);
        queue.add(20);
        assertEquals(2, queue.getLength(), "Length should be 2 after adding an element");
        assertEquals(Integer.valueOf(20), queue.get(1), "Second element should be 20");
    }

    @Test
    public void testAddWithCode() {
        GenericQueue<String> queue = new GenericQueue<>("firstKey", "first", 0);
        queue.add("secondKey", "second", 123);
        
        assertEquals(2, queue.getLength(), "Length should be 2 after adding an element with code");
        assertEquals("second", queue.get(1), "Second element should be 'second'");
        assertEquals(123, queue.getHead().next.code, "Code of second node should be 123");
        assertEquals("secondKey", queue.getHead().next.key, "Key of second node should be 'secondKey'");
    }    

    @Test
    public void testDelete() {
        GenericQueue<Integer> queue = new GenericQueue<>(10);
        queue.add(20);
        Integer deleted = queue.delete();
        assertEquals(Integer.valueOf(10), deleted, "Deleted element should be 10");
        assertEquals(1, queue.getLength(), "Length should be 1 after deletion");
        assertEquals(Integer.valueOf(20), queue.getHead().data, "Tail should now be the first element");
    }    

    @Test
    public void testEnqueueDequeue() {
        GenericQueue<Integer> queue = new GenericQueue<>(10);
        queue.enqueue(20);
        queue.enqueue(30);
        Integer dequeued = queue.dequeue();
        assertEquals(Integer.valueOf(10), dequeued, "Dequeued element should be 10");
        assertEquals(2, queue.getLength(), "Length should be 2 after dequeue");
    }    

    @Test
    public void testPrint() {
        GenericQueue<Integer> queue = new GenericQueue<>(10);
        queue.add(20);
        queue.add(30);
        
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));
        
        queue.print();
        
        String lineSep = System.lineSeparator();
        String expectedOutput = "10" + lineSep + "20" + lineSep + "30" + lineSep;
        assertEquals(expectedOutput, outContent.toString(), "Print output should match expected");
        
        System.setOut(originalOut);
    }

    @Test
    public void testDumpList() {
        GenericQueue<Integer> queue = new GenericQueue<>(10);
        queue.add(20);
        queue.add(30);
        ArrayList<Integer> list = queue.dumpList();
        assertEquals(3, list.size(), "List size should be 3 after dump");
        assertEquals(Integer.valueOf(10), list.get(0), "First element should be 10");
        assertEquals(Integer.valueOf(20), list.get(1), "Second element should be 20");
        assertEquals(Integer.valueOf(30), list.get(2), "Third element should be 30");
        assertEquals(3, queue.getLength(), "Queue length should remain 3 after dump");
        assertEquals(Integer.valueOf(10), queue.getHead().data, "Head should still be 10 after dump");
    }  

    @Test
    public void testGetSet() {
        GenericQueue<String> queue = new GenericQueue<>("first");
        queue.add("second");
        queue.add("third");
        assertEquals("second", queue.get(1), "Element at index 1 should be 'second'");
        String oldValue = queue.set(1, "newSecond");
        assertEquals("second", oldValue, "Old value at index 1 should be 'second'");
        assertEquals("newSecond", queue.get(1), "New value at index 1 should be 'newSecond'");
        assertNull(queue.get(3), "Getting element at invalid index should return null");
    }

    @Test
    public void testIterator() {
        GenericQueue<Integer> queue = new GenericQueue<>(10);
        queue.add(20);
        queue.add(30);
        Iterator<Integer> iterator = queue.iterator();
        assertTrue(iterator.hasNext(), "Iterator should have next");
        assertEquals(Integer.valueOf(10), iterator.next(), "First element should be 10");
        assertEquals(Integer.valueOf(20), iterator.next(), "Second element should be 20");
        assertEquals(Integer.valueOf(30), iterator.next(), "Third element should be 30");
        assertFalse(iterator.hasNext(), "Iterator should not have next after last element");
    }

    @Test
    public void testDescendingIterator() {
        GenericQueue<Integer> queue = new GenericQueue<>(10);
        queue.add(20);
        queue.add(30);
        Iterator<Integer> iterator = queue.descendingIterator();
        assertTrue(iterator.hasNext(), "Descending iterator should have next");
        assertEquals(Integer.valueOf(30), iterator.next(), "First element should be 30");
        assertEquals(Integer.valueOf(20), iterator.next(), "Second element should be 20");
        assertEquals(Integer.valueOf(10), iterator.next(), "Third element should be 10");
        assertFalse(iterator.hasNext(), "Descending iterator should not have next after last element");
    }

    @Test
    public void testNodeClass() {
        GenericQueue<Integer> queue = new GenericQueue<>(10);
        GenericList<Integer>.Node<Integer> node = queue.new Node<>(20);
        assertEquals(Integer.valueOf(20), node.data, "Node data should be 20");
        assertEquals(0, node.code, "Node code should be 0 for generic queues");
        assertNull(node.next, "Node next should be null");
        assertNull(node.key, "Node key should be null for GenericQueue");
    }    

    @Test
    public void testForEachLoop() {
        GenericQueue<Integer> queue = new GenericQueue<>(10);
        queue.add(20);
        queue.add(30);
        ArrayList<Integer> collected = new ArrayList<>();
        for (Integer item : queue) {
            collected.add(item);
        }
        ArrayList<Integer> expected = new ArrayList<>(Arrays.asList(10, 20, 30));
        assertEquals(expected, collected, "forEach loop should collect all elements in order");
    }
}