package storing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HashingTest {

    private Hashing<String> hashing;

    @BeforeEach
    public void setUp() {

        hashing = new Hashing<>(10);
    }

    @Test
    public void testAdd() {
        assertEquals(0, hashing.add("Data1", 1955));
        assertEquals(1, hashing.add("Data2", 1963));
        assertEquals(5, hashing.add("Data3", 2008));
    }


    @Test
    public void testClearHashTable() {
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

        assertDoesNotThrow(() -> hashing.display());
    }
}
