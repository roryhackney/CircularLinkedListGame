import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.awt.Color;
import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    public static Player p;

    @BeforeAll
    public static void setUp() {
        p = new Player("Timmy");
    }

    @Test
    public void getName() {
        assertEquals("Timmy", p.getName());
    }

    @Test
    public void getScore() {
        assertEquals(0, p.getScore());
    }

    @Test
    public void increaseScore() {
        Player p2 = new Player("Ashley");
        assertEquals(0, p2.getScore());
        assertEquals(7, p2.increaseScore(7));
        assertEquals(7, p2.getScore());
    }

    @Test
    public void test_equals() {
        assertEquals(p, new Player("Timmy"));
        assertNotEquals(p, null);
        assertNotEquals(p, Color.BLUE);
        assertNotEquals(p, new Player("Ashley"));

    }

    @Test
    public void testPreconditions() {
        assertThrows(IllegalArgumentException.class, () -> new Player(null));
        assertThrows(IllegalArgumentException.class, () -> new Player(""));
        assertThrows(IllegalArgumentException.class, () -> p.increaseScore(-1));
        assertThrows(IllegalArgumentException.class, () -> p.increaseScore(0));

    }
}