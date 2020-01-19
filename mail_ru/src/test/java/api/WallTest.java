package api;

import org.testng.Assert;
import org.testng.annotations.Test;

public class WallTest {
    Wall wall = new Wall();

    @Test()
    public void postMessageTest() {
        wall.postMessage();
        Assert.assertTrue(wall.postIsPresent());
    }

    @Test(priority = 1)
    public void editMessageTest() {
        wall.editMessage();
        Assert.assertTrue(wall.postIsEdited());
    }

    @Test(priority = 2)
    public void deleteMessageTest() {
        wall.deleteMessage();
        Assert.assertFalse(wall.postIsPresent());
    }
}