package models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import storing.Hashing;
import utils.DeveloperUtil;
import utils.PublisherUtil;

import static org.junit.jupiter.api.Assertions.*;

public class OriginalGameTest {

    private OriginalGame originalGame;

    @BeforeEach
    void setUp() {
        originalGame = new OriginalGame(
                "Title",
                new PublisherUtil("Publisher"),
                "Description",
                new DeveloperUtil("Developer"),
                new Machine("Machine", null, "", "", "", 0, 0, "", null,null),
                2023,
                "Cover",
                new Hashing<>(8)
        );
    }

    @Test
    void testGetPublisher() {
        PublisherUtil newPublisher = new PublisherUtil("New Publisher");
        originalGame.setPublisher(newPublisher);
        assertEquals(newPublisher, originalGame.getPublisher());
    }

    @Test
    void testSetPublisher() {
        PublisherUtil newPublisher = new PublisherUtil("New Publisher");
        originalGame.setPublisher(newPublisher);
        assertEquals(newPublisher, originalGame.getPublisher());
    }

    @Test
    void testGetReleaseYear() {
        assertEquals(2023, originalGame.getReleaseYear());
    }

    @Test
    void testSetReleaseYearValid() {
        originalGame.setReleaseYear(2022);
        assertEquals(2022, originalGame.getReleaseYear());
    }

    @Test
    void testSetReleaseYearInvalid() {
        originalGame.setReleaseYear(1800);
        assertEquals(2000, originalGame.getReleaseYear());
    }

    @Test
    void testGetCover() {
        assertEquals("Cover", originalGame.getCover());
    }

    @Test
    void testSetCoverValid() {
        originalGame.setCover("New Cover");
        assertEquals("New Cover", originalGame.getCover());
    }

    @Test
    void testSetCoverNull() {
        originalGame.setCover(null);
        assertEquals("Cover Error", originalGame.getCover());
    }


}
