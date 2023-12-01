package models;

import org.junit.jupiter.api.Test;
import storing.Hashing;
import utils.DeveloperUtil;
import utils.PublisherUtil;
import static org.junit.jupiter.api.Assertions.*;

public class PortedGameTest {

    @Test
    public void testGettersAndSetters() {
        DeveloperUtil developer = new DeveloperUtil("Some Developer");
        PublisherUtil publisher = new PublisherUtil("Some Publisher");
        Machine machine = new Machine("", null, "", "", "", 0, 0, "", null);
        OriginalGame originalGame = new OriginalGame(
                "Original Game",
                publisher,
                "Original Game Description",
                developer,
                machine,
                2020,
                "original_cover.jpg",
                new Hashing<>(6)
        );

        PortedGame portedGame = new PortedGame(
                "Ported Game",
                publisher,
                "Ported Game Description",
                developer,
                machine,
                2022,
                "ported_cover.jpg",
                originalGame
        );

        Hashing<PortedGame> portedGamesHashing = new Hashing<>(10);

        // Test getters
        assertEquals("Ported Game", portedGame.getTitle());
        assertEquals("Ported Game Description", portedGame.getDescription());
        assertEquals(developer, portedGame.getDeveloper());
        assertEquals(publisher, portedGame.getPublisher());
        assertEquals(machine, portedGame.getMachine());
        assertEquals(2022, portedGame.getReleaseYear());
        assertEquals("ported_cover.jpg", portedGame.getCover());
        assertNotNull(portedGame.getOriginalGame());

        // Test setters
        PublisherUtil newPublisher = new PublisherUtil("New Publisher");
        portedGame.setPublisher(newPublisher);
        assertEquals(newPublisher, portedGame.getPublisher());

        Machine newMachine = new Machine("New Machine", null, "", "", "", 0, 0, "", null);
        portedGame.setMachine(newMachine);
        assertEquals(newMachine, portedGame.getMachine());

        portedGame.setReleaseYear(2023);
        assertEquals(2023, portedGame.getReleaseYear());

        portedGame.setCover("new_cover.jpg");
        assertEquals("new_cover.jpg", portedGame.getCover());

        OriginalGame newOriginalGame = new OriginalGame(
                "New Original Game",
                publisher,
                "New Original Game Description",
                developer,
                machine,
                2021,
                "new_original_cover.jpg",
                new Hashing<>(6)
        );
        portedGame.setOriginalGame(newOriginalGame);
        assertEquals(newOriginalGame, portedGame.getOriginalGame());

        // Test Hashing operations
        portedGamesHashing.add(portedGame, portedGame.getReleaseYear());
        assertTrue(portedGamesHashing.hashTable[2].contains(portedGame));

        portedGamesHashing.remove(portedGame);
        assertFalse(portedGamesHashing.hashTable[2].contains(portedGame));
    }

    @Test
    public void testToString() {
        DeveloperUtil developer = new DeveloperUtil("Some Developer");
        PublisherUtil publisher = new PublisherUtil("Some Publisher");
        Machine machine = new Machine("", null, "", "", "", 0, 0, "", null);
        OriginalGame originalGame = new OriginalGame(
                "Original Game",
                publisher,
                "Original Game Description",
                developer,
                machine,
                2020,
                "original_cover.jpg",
                new Hashing<>(6)
        );

        PortedGame portedGame = new PortedGame(
                "Ported Game",
                publisher,
                "Ported Game Description",
                developer,
                machine,
                2022,
                "ported_cover.jpg",
                originalGame
        );

        // Test toString method
        assertEquals("PortedGame{" +
                "title='Ported Game', " +
                "description='Ported Game Description', " +
                "developer='Some Developer', " +
                "publisher='Some Publisher', " +
                "machine=Machine{...}, " +
                "releaseYear=2022, " +
                "cover='ported_cover.jpg', " +
                "originalGame=OriginalGame{...}}", portedGame.toString());
    }
}
