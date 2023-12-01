package models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import storing.Hashing;
import utils.DeveloperUtil;
import utils.PublisherUtil;

import static org.junit.jupiter.api.Assertions.*;

public class OriginalGameTest {

    @Test
    public void testGettersAndSetters() {
        DeveloperUtil developer = new DeveloperUtil("Some Developer");
        PublisherUtil publisher = new PublisherUtil("Some Publisher");
        Machine machine = new Machine("", null, "", "", "", 0, 0, "", null);

        Hashing<PortedGame> portedGames = new Hashing<>(10); // Adjust the size as needed

        OriginalGame originalGame = new OriginalGame(
                "Game Title",
                publisher,
                "Game Description",
                developer,
                machine,
                2022,
                "cover.jpg",
                portedGames
        );

        // Test getters
        assertEquals("Game Title", originalGame.getTitle());
        assertEquals("Game Description", originalGame.getDescription());
        assertEquals(developer, originalGame.getDeveloper());
        assertEquals(publisher, originalGame.getPublisher());
        assertEquals(machine, originalGame.getOriginalMachine());
        assertEquals(2022, originalGame.getReleaseYear());
        assertEquals("cover.jpg", originalGame.getCover());
        assertNotNull(originalGame.getPortedGames());

        // Test setters
        PublisherUtil newPublisher = new PublisherUtil("New Publisher");
        originalGame.setPublisher(newPublisher);
        assertEquals(newPublisher, originalGame.getPublisher());

        Machine newMachine = new Machine("New Machine", null, "", "", "", 0, 0, "", null);
        originalGame.setOriginalMachine(newMachine);
        assertEquals(newMachine, originalGame.getOriginalMachine());

        originalGame.setReleaseYear(2023);
        assertEquals(2023, originalGame.getReleaseYear());

        originalGame.setCover("new_cover.jpg");
        assertEquals("new_cover.jpg", originalGame.getCover());
    }

    @Test
    public void testAddAndRemovePortedGame() {
        Hashing<PortedGame> portedGames = new Hashing<>(10); // Adjust the size as needed

        OriginalGame originalGame = new OriginalGame(
                "Game Title",
                new PublisherUtil("Some Publisher"),
                "Game Description",
                new DeveloperUtil("Some Developer"),
                new Machine("", null, "", "", "", 0, 0, "", null),
                2022,
                "cover.jpg",
                portedGames
        );

        PortedGame portedGame = new PortedGame(
                "Ported Game",
                new PublisherUtil("Some Publisher"),
                "Ported Game Description",
                new DeveloperUtil("Some Developer"),
                new Machine("", null, "", "", "", 0, 0, "", null),
                2023,
                "cover_ported.jpg",
                originalGame
        );

        // Test add and remove ported game
        originalGame.addPortedGame(portedGame);
        assertEquals(1, originalGame.getPortedGames().hashTable[5].size()); // Assuming 10 as the size

        originalGame.removePortedGame(portedGame);
        assertEquals(0, originalGame.getPortedGames().hashTable[5].size()); // Assuming 10 as the size
    }

    @Test
    public void testToString() {
        Hashing<PortedGame> portedGames = new Hashing<>(10); // Adjust the size as needed

        OriginalGame originalGame = new OriginalGame(
                "Game Title",
                new PublisherUtil("Some Publisher"),
                "Game Description",
                new DeveloperUtil("Some Developer"),
                new Machine("", null, "", "", "", 0, 0, "", null),
                2022,
                "cover.jpg",
                portedGames
        );

        // Test toString method
        assertEquals("OriginalGame{" +
                "title='Game Title', " +
                "description='Game Description', " +
                "developer='Some Developer', " +
                "publisher='Some Publisher', " +
                "originalMachine=Machine{...}, " +
                "releaseYear=2022, " +
                "cover='cover.jpg'}", originalGame.toString());
    }
}