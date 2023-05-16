import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Represents an expanded LinkedList that is circular (last element points to first)
 * @param <E> Data type of elements to be stored in the CircularLinkedList
 */
public class CircularLinkedList<E> implements CircularLinkedListInterface<E> {
    private Node<E> front;
    private Node<E> end;
    private int size;

    /**
     * Constructs new empty CircularLinkedList
     */
    public CircularLinkedList() {
        clear();
    }

    /**
     * Clears the list, emptying it of all elements.
     */
    public void clear() {
        front = null;
        end = null;
        size = 0;
    }

    /**
     * Retrieves a count of elements being maintained by the list.
     *
     * @return the size of the list (count of elements)
     */
    @Override
    public int getSize() {
        return size;
    }

    /**
     * Retrieves the data at the specified position in the list
     *
     * @param position 0-based index for the list; must be in the range 0 to size - 1
     * @return the data in the specified position in the list
     */
    @Override
    public E get(int position) {
        if (position < 0 || position >= size) {
            throw new IndexOutOfBoundsException("position must be in range 0 to size - 1");
        }
        int count = 0;
        Node<E> current = front;
        while (count < position) {
            current = current.next;
            count++;
        }
        return current.data;
    }

    /**
     * Adds a new node to the end of the list; by nature, this element's next will point to the first element
     *
     * @param value the element to add to the list
     */
    @Override
    public void add(E value) {
        Node<E> toAdd = new Node<>(value);
        if (size == 0) {
            front = toAdd;
        } else {
            end.next = toAdd;
        }
        end = toAdd;
        toAdd.next = front;
        size++;
    }

    /**
     * Removes the specified item from the list, if it exists there.
     *
     * @param value the element to remove from the list
     * @return true, if the element was found and removed; false, if not found or list is empty
     */
    @Override
    public boolean remove(E value) {
        return false;
    }

    /**
     * Removes the node at the specified position in the list
     *
     * @param position position in the list; must be in range 0 to size - 1
     */
    @Override
    public void remove(int position) {

    }

    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        } else {
            String rep = "[" + front.data;
            Node<E> current = front.next;
            while (current != front) {
                rep += ", " + current.data;
                current = current.next;
            }
            rep += "]";
            return rep;
        }
    }

    /**
     * Retrieves an iterator over the list's elements.  Do not do other list operations like add or remove
     * from within an iterator loop; the results are not guaranteed to function as you might expect
     *
     * @return a strongly typed iterator over elements in the list
     */
    @Override
    public Iterator<E> iterator() {
        return new CircularLinkedListIterator();
    }


    private class CircularLinkedListIterator implements Iterator<E> {
        public Node<E> current;
        public CircularLinkedListIterator() {
            current = front;
        }
//constructor with set next for node?
        /**
         * Returns {@code true} if the iteration has more elements.
         * (In other words, returns {@code true} if {@link #next} would
         * return an element rather than throwing an exception.)
         *
         * @return {@code true} if the iteration has more elements
         */
        @Override
        public boolean hasNext() {
            return size != 0;
        }

        /**
         * Returns the next element in the iteration.
         *
         * @return the next element in the iteration
         * @throws NoSuchElementException if the iteration has no more elements
         */
        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            } else {
                current = current.next;
                return current.data;
            }
        }
    }
    /**
     * Container for element data
     * @param <T> Data type of element data to be stored
     */
    private static class Node<T> {
        public T data;
        public Node<T> next;

        public Node(T data) {
            this.data = data;
            this.next = null;
        }
    }
}
