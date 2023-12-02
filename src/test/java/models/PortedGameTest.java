package models;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import storing.Hashing;
import utils.DeveloperUtil;
import utils.PublisherUtil;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PortedGameTest {
    private PortedGame game;

    @BeforeEach
    public void setUp() {
        PublisherUtil publisher = new PublisherUtil("PublisherName");
        DeveloperUtil developer = new DeveloperUtil("DeveloperName");
        Machine machine = new Machine("Machine", null, "", "", "", 0, 0, "", null,null);
        OriginalGame originalGame = new OriginalGame("OriginalTitle", publisher,"Description",developer,machine,2000,"cover",new Hashing<>(8));

        game = new PortedGame("PortedTitle", publisher, "Description", developer, machine, 2023, "CoverURL", originalGame);
    }

    @Test
    public void testGetters() {
        assertNotNull(game.getTitle());
        assertNotNull(game.getDescription());
        assertNotNull(game.getPublisher());
        assertNotNull(game.getDeveloper());
        assertNotNull(game.getOriginalGame());
        assertNotNull(game.getReleaseYear());
        assertNotNull(game.getCover());
        assertNotNull(game.getMachine());
    }

    @Test
    public void testReleaseYearSetter() {
        assertEquals(2023, game.getReleaseYear());
    }

    @Test
    public void testCoverSetter() {
        game.setCover(null);
        game.setCover(null);
        System.out.println(game.getCover());
        assertEquals("Cover Error", game.getCover());

        game.setCover("");
        assertEquals("Cover Error", game.getCover());
    }

    @Test
    public void testOriginalGameSetter() {
        assertNotNull(game.getOriginalGame());
    }

    @Test
    public void testPublisherSetter() {
        assertNotNull(game.getPublisher());
    }

    @Test
    public void testMachineSetter() {
        assertNotNull(game.getMachine());
    }


}