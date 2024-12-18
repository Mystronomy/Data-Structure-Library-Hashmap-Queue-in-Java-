import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class HMTest {

    @Test
    public void testConstructor() {
        MyHashMap<String> map = new MyHashMap<>("key1", "value1");
        assertEquals(1, map.size(), "Size should be 1 after initialization");
        assertEquals("value1", map.get("key1"), "Value for 'key1' should be 'value1'");
    }

    @Test
    public void testPut() {
        MyHashMap<String> map = new MyHashMap<>("key1", "value1");
        map.put("key2", "value2");
        assertEquals(2, map.size(), "Size should be 2 after put");
        assertEquals("value2", map.get("key2"), "Value for 'key2' should be 'value2'");
    }

    @Test
    public void testContains() {
        MyHashMap<String> map = new MyHashMap<>("key1", "value1");
        map.put("key2", "value2");
        assertTrue(map.contains("key1"), "Map should contain 'key1'");
        assertTrue(map.contains("key2"), "Map should contain 'key2'");
        assertFalse(map.contains("key3"), "Map should not contain 'key3'");
    }

    @Test
    public void testGet() {
        MyHashMap<String> map = new MyHashMap<>("key1", "value1");
        map.put("key2", "value2");
        assertEquals("value1", map.get("key1"), "Value for 'key1' should be 'value1'");
        assertEquals("value2", map.get("key2"), "Value for 'key2' should be 'value2'");
        assertNull(map.get("key3"), "Value for 'key3' should be null");
    }    

    @Test
    public void testReplace() {
        MyHashMap<String> map = new MyHashMap<>("key1", "value1");
        map.put("key2", "value2");
        String oldValue = map.replace("key1", "newValue1");
        assertEquals("value1", oldValue, "Old value should be 'value1'");
        assertEquals("newValue1", map.get("key1"), "New value should be 'newValue1'");
        assertNull(map.replace("key3", "value3"), "Replacing non-existing key should return null");
    }

    @Test
    public void testIsEmpty() {
        MyHashMap<String> map = new MyHashMap<>("key1", "value1");
        assertFalse(map.isEmpty(), "Map should not be empty after initialization");
        map.replace("key1", null);
        assertFalse(map.isEmpty(), "Map should not be empty even if value is null");
    }

    @Test
    public void testIterator() {
        MyHashMap<String> map = new MyHashMap<>("key1", "value1");
        map.put("key2", "value2");
        map.put("key3", "value3");
        Iterator<String> iterator = map.iterator();
        ArrayList<String> iteratedValues = new ArrayList<>();
        while (iterator.hasNext()) {
            iteratedValues.add(iterator.next());
        }
        ArrayList<String> expectedValues = new ArrayList<>(Arrays.asList("value1", "value2", "value3"));
        assertEquals(expectedValues.size(), iteratedValues.size(), "Iterator should traverse all elements.");
        assertTrue(iteratedValues.containsAll(expectedValues), "Iterator should traverse all expected elements.");
    }

    @Test
    public void testHMIterator() {
        MyHashMap<String> map = new MyHashMap<>("keyA", "valueA");
        map.put("keyB", "valueB");
        map.put("keyC", "valueC");
        Iterator<String> iterator = map.iterator();
        assertTrue(iterator.hasNext(), "Iterator should have next");
        String first = iterator.next();
        assertNotNull(first, "First value should not be null");
        assertTrue(iterator.hasNext(), "Iterator should have next after first");
        String second = iterator.next();
        assertNotNull(second, "Second value should not be null");
        assertTrue(iterator.hasNext(), "Iterator should have next after second");
        String third = iterator.next();
        assertNotNull(third, "Third value should not be null");
        assertFalse(iterator.hasNext(), "Iterator should not have next after last");
    }

    @Test
    public void testCollision() {
        MyHashMap<String> map = new MyHashMap<>("key1", "value1");
        String key1 = "Aa";
        String key2 = "BB";
        map.put(key1, "valueAa");
        map.put(key2, "valueBB");
        assertEquals("valueAa", map.get(key1), "Value for 'Aa' should be 'valueAa'");
        assertEquals("valueBB", map.get(key2), "Value for 'BB' should be 'valueBB'");
        assertEquals(3, map.size(), "Map size should be 3 after adding colliding keys");
    }

    @Test
    public void testForEachLoop() {
        MyHashMap<String> map = new MyHashMap<>("key1", "value1");
        map.put("key2", "value2");
        map.put("key3", "value3");
        ArrayList<String> collected = new ArrayList<>();
        for (String value : map) {
            collected.add(value);
        }
        ArrayList<String> expected = new ArrayList<>(Arrays.asList("value1", "value2", "value3"));
        assertEquals(expected.size(), collected.size(), "forEach loop should collect all elements.");
        assertTrue(collected.containsAll(expected), "forEach loop should collect all expected elements.");
    }
}
