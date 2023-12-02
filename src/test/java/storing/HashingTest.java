package storing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HashingTest {

    private Hashing<String> hashing;

    @BeforeEach
    public void setUp() {
        // Set up a new Hashing instance before each test
        hashing = new Hashing<>(10); // Adjust the size as needed
    }

    @Test
    public void testAdd() {
        assertEquals(5, hashing.add("Data1", 1955)); // Assuming 10 as the size
        assertEquals(3, hashing.add("Data2", 1963)); // Assuming 10 as the size
        assertEquals(7, hashing.add("Data3", 1978)); // Assuming 10 as the size

        // Add more test cases based on your specific scenarios
    }

    @Test
    public void testRemove() {
        // Assuming 10 as the size
        hashing.add("Data1", 1955);
        hashing.add("Data2", 1963);
        hashing.add("Data3", 1978);

        assertTrue(hashing.); // Assuming 10 as the size
        assertEquals(1, hashing.remove("Data2")); // Assuming 10 as the size
        assertEquals(5, hashing.remove("Data3")); // Assuming 10 as the size

        // Add more test cases based on your specific scenarios
    }

    @Test
    public void testClearHashTable() {
        // Assuming 10 as the size
        hashing.add("Data1", 1955);
        hashing.add("Data2", 1963);
        hashing.add("Data3", 1978);

        hashing.clearHashTable();

        for (int i = 0; i < hashing.hashTable.length; i++) {
            assertNull(hashing.hashTable[i].head);
        }
    }

    @Test
    public void testDisplay() {
        // Assuming 10 as the size
        hashing.add("Data1", 1955);
        hashing.add("Data2", 1963);
        hashing.add("Data3", 1978);

        // You may redirect System.out to test the printed output
        // and check if it matches your expectations.
        // For simplicity, let's just test that it doesn't throw an exception.
        assertDoesNotThrow(() -> hashing.display());
    }
}
