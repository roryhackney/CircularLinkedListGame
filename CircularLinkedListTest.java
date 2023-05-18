import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.Iterator;
import java.util.NoSuchElementException;
import static org.junit.jupiter.api.Assertions.*;

public class CircularLinkedListTest {
    public static CircularLinkedList<String> names;

    @BeforeAll
    public static void setUp() {
        names = new CircularLinkedList<>();
    }

    @AfterEach
    public void reset() {
        names.clear();
    }

    @Test
    public void test_constructor() {
        CircularLinkedList<Player> players = new CircularLinkedList<>();
        players.add(new Player("Anna"));
        players.add(new Player("Bartholomew"));
        players.add(new Player("Cassandra"));
        assertEquals(new Player("Cassandra"), players.get(2));
        assertEquals(3, players.getSize());
        //System.out.println(names);
    }

    @Test
    public void test_clear() {
        names.add("Dani");
        assertEquals(1, names.getSize());
        names.clear();
        assertEquals(0, names.getSize());
        assertThrows(IndexOutOfBoundsException.class, () -> names.get(0));
    }

    @Test
    public void test_getSize() {
        assertEquals(0, names.getSize());
        names.add("Anton");
        assertEquals(1, names.getSize());
        names.add("Beatrice");
        assertEquals(2, names.getSize());
    }

    @Test
    public void test_get() {
        assertThrows(IndexOutOfBoundsException.class, () ->
                names.get(0));
        names.add("Timmy");
        names.add("Tommy");
        assertThrows(IndexOutOfBoundsException.class, () ->
                names.get(-1));
        assertThrows(IndexOutOfBoundsException.class, () ->
                names.get(names.getSize()));
        assertThrows(IndexOutOfBoundsException.class, () ->
                names.get(2));
        assertEquals("Timmy", names.get(0));
        assertEquals("Tommy", names.get(names.getSize()-1));
    }

    @Test
    public void test_addAtFront() {
        assertEquals(0, names.getSize());
        names.addAtFront("Billy");
        assertEquals(1, names.getSize());
        names.addAtFront("Viola");
        assertEquals(2, names.getSize());
        names.addAtFront(null);
        names.addAtFront("Kiki");
        assertEquals(4, names.getSize());
        assertEquals("[Kiki, null, Viola, Billy]", names.toString());
    }

    @Test
    public void test_add_and_toString() {
       assertEquals(0, names.getSize());
       assertEquals("[]", names.toString());
       names.add("Gina");
       assertEquals(1, names.getSize());
       assertEquals("Gina", names.get(0));
       assertEquals("[Gina]", names.toString());
       names.add(null); //yes, null values are allowed
       names.add("Violet");
       assertEquals(3, names.getSize());
       assertEquals("Violet", names.get(2));
       assertEquals("[Gina, null, Violet]", names.toString());
    }

    @Test
    public void test_addListToList() {
        CircularLinkedList<String> empty = new CircularLinkedList<>();
        names.addListToList(empty);
        assertEquals("[]", names.toString());
        CircularLinkedList<String> oneName = new CircularLinkedList<>();
        oneName.add("Stone");
        names.addListToList(oneName);
        assertEquals("[Stone]", names.toString());
        oneName.clear();
        oneName.add("Smith");
        names.addListToList(oneName);
        assertEquals("[Stone, Smith]", names.toString());
        CircularLinkedList<String> moreNames = new CircularLinkedList<>();
        moreNames.add("Cedar");
        moreNames.add("Boots");
        names.addListToList(moreNames);
        assertEquals("[Stone, Smith, Cedar, Boots]", names.toString());
    }

    @Test
    public void test_indexOf() {
        assertEquals(-1, names.indexOf("Avocado"));
        names.add("Avocado");
        names.add("Blueberry");
        names.add("Rhubarb");
        names.add("Rhubarb");
        assertEquals(-1, names.indexOf("Tomato"));
        assertEquals(1, names.indexOf("Blueberry"));
        names.add(null);
        assertEquals(4, names.indexOf(null));
        assertEquals(2, names.indexOf("Rhubarb"));
    }

    @Test
    public void test_remove_byValue() {
        names.add("Potato");
        names.add("Garlic");
        names.add("Butter");
        assertTrue(names.remove("Garlic"));
        assertEquals(2, names.getSize());
        assertEquals("Potato", names.get(0));
        assertEquals("Butter", names.get(1));
        assertThrows(IndexOutOfBoundsException.class, () -> names.get(2));
        assertTrue(names.remove("Potato"));
        assertFalse(names.remove("Jalapenos"));
        assertEquals(1, names.getSize());
        assertEquals("Butter", names.get(0));
    }

    @Test
    public void test_remove_byPosition() {
        assertThrows(IndexOutOfBoundsException.class, () ->
                names.remove(0));
        names.add("Potato");
        names.add("Garlic");
        names.add("Butter");
        assertThrows(IndexOutOfBoundsException.class, () -> names.remove(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> names.remove(3));
        names.remove(1);
        assertEquals(2, names.getSize());
        assertEquals("Potato", names.get(0));
        assertEquals("Butter", names.get(1));
        assertThrows(IndexOutOfBoundsException.class, () -> names.get(2));
        names.remove(0);
        assertEquals(1, names.getSize());
        assertEquals("Butter", names.get(0));
        assertThrows(IndexOutOfBoundsException.class, () -> names.get(1));
    }
    //TODO: make game (BASIC)
    //TODO: go over project document
    //TODO: add sound if still alive and on this earth

    @Test
    public void test_iterator() {
        Iterator<String> it = names.iterator();
        assertFalse(it.hasNext());
        assertThrows(NoSuchElementException.class, () -> it.next());
        names.add("Pat");
        names.add("Ruby");
        names.add("Tara");
        assertTrue(it.hasNext());
        assertEquals("Pat", it.next());
        it.next();
        assertEquals("Tara", it.next());
        assertEquals("Pat", it.next());
    }
}