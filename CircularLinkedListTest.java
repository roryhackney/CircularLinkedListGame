import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CircularLinkedListTest {

    @Test
    public void test_constructor() {
        CircularLinkedList<String> names = new CircularLinkedList<>();
        names.add("Anna");
        names.add("Bartholomew");
        names.add("Cassandra");
        assertEquals("Cassandra", names.get(2));
        assertEquals(3, names.getSize());
        System.out.println(names);
    }

    @Test
    void test_clear() {
        CircularLinkedList<String> names = new CircularLinkedList<>();
        names.add("Timmy");
        names.add("Sandy");
        assertEquals(2, names.getSize());
        names.clear();
        assertEquals(0, names.getSize());
        assertThrows(IndexOutOfBoundsException.class, () -> names.get(0));
    }

    @Test
    void getSize() {
    }

    @Test
    void get() {
    }

    @Test
    void add() {
    }

    @Test
    void remove() {
    }

    @Test
    void testRemove() {
    }

    @Test
    void iterator() {
    }
}