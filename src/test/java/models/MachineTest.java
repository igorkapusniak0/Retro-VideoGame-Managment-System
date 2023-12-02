package models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import storing.Hashing;
import utils.ManufacturerUtil;

import static org.junit.jupiter.api.Assertions.*;

class MachineTest {

    Machine machine = new Machine("Test Machine", new ManufacturerUtil("Manufacturer"), "Test Description", "Type", "Media", 2023, 999.99, "image.jpg", new Hashing<>(8));

    @AfterEach
    void setup(){
        Machine machine = new Machine("Test Machine", new ManufacturerUtil("Manufacturer"), "Test Description", "Type", "Media", 2023, 999.99, "image.jpg", new Hashing<>(8));
    }


    @Test
    void setName() {

        machine.setName("New Name");
        assertEquals("New Name", machine.getName());

        machine.setName(null);
        assertEquals("Name Error", machine.getName());
    }

    @Test
    void getName() {
        assertEquals("Test Machine", machine.getName());
    }

    @Test
    void setDescription() {
        machine.setDescription("New Description");
        assertEquals("New Description", machine.getDescription());

        machine.setDescription(null);
        System.out.println( machine.getDescription());
        assertEquals("Description Error", machine.getDescription());
    }

    @Test
    void getDescription() {
        assertEquals("Test Description", machine.getDescription());
    }

    @Test
    void setManufacturer() {
        ManufacturerUtil manufacturer = new ManufacturerUtil("Manufacturer");
        machine.setManufacturer(manufacturer);
        assertEquals(manufacturer, machine.getManufacturer());

        machine.setManufacturer(null);
        assertNull(machine.getManufacturer());
    }

    @Test
    void getManufacturer() {
        ManufacturerUtil manufacturer = new ManufacturerUtil("Manufacturer");
        machine.setManufacturer(manufacturer);
        assertEquals(manufacturer, machine.getManufacturer());
    }

    @Test
    void setType() {
        // Test setting a valid type
        machine.setType("New Type");
        assertEquals("New Type", machine.getType());

        // Test setting null type
        machine.setType(null);
        assertEquals("Type Error", machine.getType());
    }

    @Test
    void getType() {
        // Test getting type
        assertEquals("Type", machine.getType());
    }

    @Test
    void setMedia() {
        // Test setting a valid media
        machine.setMedia("New Media");

        assertEquals("New Media", machine.getMedia());

        // Test setting null media
        machine.setMedia(null);
        System.out.println(machine.getMedia());
        assertEquals("New Media", machine.getMedia());
    }

    @Test
    void getMedia() {
        // Test getting media
        assertEquals("Media", machine.getMedia());
    }

    @Test
    void setLaunchYear() {
        // Test setting a valid launch year
        machine.setLaunchYear(2025);
        assertEquals(2025, machine.getLaunchYear());

        // Test setting a launch year before 1950
        machine.setLaunchYear(1949);
        assertEquals(2000, machine.getLaunchYear());
    }

    @Test
    void getLaunchYear() {
        // Test getting launch year
        assertEquals(2023, machine.getLaunchYear());
    }

    @Test
    void setPrice() {
        // Test setting a valid price
        machine.setPrice(500.50);
        assertEquals(500.50, machine.getPrice());

        // Test setting a negative price
        machine.setPrice(-100.00);
        assertEquals(0.00, machine.getPrice());
    }

    @Test
    void getPrice() {
        // Test getting price
        assertEquals(999.99, machine.getPrice());
    }
    @Test
    void setImage() {
    }

    @Test
    void getImage() {
    }

    @Test
    void addGame() {
    }

    @Test
    void removeGame() {
    }

    @Test
    void addPortedGame() {
    }

    @Test
    void removePortedGame() {
    }

    @Test
    void setOriginalGames() {
    }

    @Test
    void getGames() {
    }

    @Test
    void setPortedGames() {
    }

    @Test
    void getPortedGames() {
    }

    @Test
    void testToString() {
    }

    @Test
    void compareTo() {
    }
}
