package storing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LinkedListTest {

    private LinkedList<String> linkedList;

    @BeforeEach
    public void setUp() {
        // Set up a new LinkedList instance before each test
        linkedList = new LinkedList<>();
    }

    @Test
    public void testAdd() {
        linkedList.add("Data1");
        linkedList.add("Data2");
        linkedList.add("Data3");

        assertEquals(3, linkedList.size());
        assertTrue(linkedList.contains("Data1"));
        assertTrue(linkedList.contains("Data2"));
        assertTrue(linkedList.contains("Data3"));
    }

    @Test
    public void testRemove() {
        linkedList.add("Data1");
        linkedList.add("Data2");
        linkedList.add("Data3");

        linkedList.remove("Data2");

        assertEquals(2, linkedList.size());
        assertTrue(linkedList.contains("Data1"));
        assertFalse(linkedList.contains("Data2"));
        assertTrue(linkedList.contains("Data3"));

        linkedList.remove("Data1");
        linkedList.remove("Data3");

        assertEquals(0, linkedList.size());
        assertFalse(linkedList.contains("Data1"));
        assertFalse(linkedList.contains("Data3"));
    }

    @Test
    public void testIsEmpty() {
        assertTrue(linkedList.isEmpty());

        linkedList.add("Data1");

        assertFalse(linkedList.isEmpty());

        linkedList.remove("Data1");

        assertTrue(linkedList.isEmpty());
    }

    @Test
    public void testGet() {
        linkedList.add("Data1");
        linkedList.add("Data2");
        linkedList.add("Data3");

        assertEquals("Data1", linkedList.get(0));
        assertEquals("Data2", linkedList.get(1));
        assertEquals("Data3", linkedList.get(2));
    }

    @Test
    public void testQuickSortRec() {
        linkedList.add("Data3");
        linkedList.add("Data1");
        linkedList.add("Data2");

        linkedList.sort(String::compareTo);

        assertEquals("Data1", linkedList.get(0));
        assertEquals("Data2", linkedList.get(1));
        assertEquals("Data3", linkedList.get(2));
    }

    @Test
    public void testDisplay() {
        linkedList.add("Data1");
        linkedList.add("Data2");
        linkedList.add("Data3");

        String expectedOutput = "0. Data1, 1. Data2, 2. Data3, ";

        assertEquals(expectedOutput, linkedList.display());
    }
}
