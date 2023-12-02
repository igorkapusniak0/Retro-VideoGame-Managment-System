package models;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import storing.Hashing;
import utils.DeveloperUtil;
import utils.ManufacturerUtil;
import utils.PublisherUtil;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {
    private Game game;

    @BeforeEach
    public void setup() {
        DeveloperUtil developer = new DeveloperUtil("Developer");
        PublisherUtil publisher = new PublisherUtil("Publisher");
        ManufacturerUtil manufacturer = new ManufacturerUtil("Machine");
        Machine machine = new Machine("name",manufacturer,"description","Computer","Tape",1950,120,"image",new Hashing<>(8));
        game = new OriginalGame("Title",publisher,"Description",developer,machine,2000,"asd",new Hashing<>(8));
    }

    @Test
    public void testSetTitle_ValidTitle() {
        game.setTitle("New Title");
        Assertions.assertEquals("New Title", game.getTitle());
    }

    @Test
    public void testSetTitle_NullTitle() {
        game.setTitle(null);
        Assertions.assertEquals("Title Error", game.getTitle());
    }

    @Test
    public void testSetTitle_EmptyTitle() {
        game.setTitle("");
        Assertions.assertEquals("Title Error", game.getTitle());
    }

    @Test
    public void testSetDescription_ValidDescription() {
        game.setDescription("New Description");
        Assertions.assertEquals("New Description", game.getDescription());
    }

    @Test
    public void testSetDescription_NullDescription() {
        game.setDescription(null);
        Assertions.assertEquals("Description Error", game.getDescription());
    }

    @Test
    public void testSetDescription_EmptyDescription() {
        game.setDescription("");
        Assertions.assertEquals("Description Error", game.getDescription());
    }
}
