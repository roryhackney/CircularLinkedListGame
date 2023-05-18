import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Represents an expanded LinkedList that is circular (last element points to first)
 * @param <E> Data type of elements to be stored in the CircularLinkedList
 */
public class CircularLinkedList<E> implements CircularLinkedListInterface<E>, Iterable<E> {
    /**
     * Front of the list, null if empty, or refers to an element
     */
    private Node<E> front;
    /**
     * End of the list, null if empty, or refers to an element
     */
    private Node<E> end;
    /**
     * Number of elements in the list
     */
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
        return getNode(position).data;
    }

    /**
     * Retrieves the Node at the specified position in the list
     * @param position 0 based index for the list; must be in the range 0 to size - 1
     * @return the Node at the specified position in the list
     */
    private Node<E> getNode(int position) {
        if (position < 0 || position >= size) {
            throw new IndexOutOfBoundsException("position must be in range 0 to size - 1");
        }
        Node<E> current = front;
        for (int currentPos = 1; currentPos <= position; currentPos++) {
            current = current.next;
        }
        return current;
    }

    /**
     * Adds a new node to the front of the list
     * @param data value to add to the list
     */
    public void addAtFront(E data) {
        Node<E> newNode = new Node<>(data, front);
        if (front == null) {
            newNode.next = newNode;
            end = newNode;
        } else {
            end.next = newNode;
        }
        front = newNode;
        size++;
    }
    /**
     * Adds a new node to the end of the list; by nature, this element's next will point to the first element
     *
     * @param data value to add to the list
     */
    @Override
    public void add(E data) {
        Node<E> newNode = new Node<>(data, front);
        if (front == null) {
            addAtFront(data);
        } else {
            end.next = newNode;
            end = newNode;
            size++;
        }
    }

    /**
     * Adds a second list to the end of the current list
     * @param listToAdd list to be added
     */
    public void addListToList(CircularLinkedList<E> listToAdd) {
        if (front == null) {
            front = listToAdd.front;
            end = listToAdd.end;
        } else {
            end.next = listToAdd.front;
            end = listToAdd.end;
            listToAdd.end.next = front;
        }
        size += listToAdd.getSize();
    }

    /**
     * Returns the index of the first instance of the value
     * @param value data value to look for
     * @return the index of the value, or -1 if not found
     */
    public int indexOf(E value) {
        if (front == null) {
            return -1;
        }
        Node<E> current = front;
        int position = 0;
        if (current.data == value) {
            return position;
        }
        while (current.next != front) {
            position++;
            current = current.next;
            if (current.data == value) {
                return position;
            }
        }
        return -1;
    }

//    /**
//     * Returns a reference to the first Node with the value
//     * @param value data value to look for
//     * @return a reference to the first Node with that value, or null if not found
//     */
//    private Node<E> nodeOf(E value) {
//        if (front == null)  {
//            return null;
//        }
//        Node<E> current = front;
//        if (current.data == value) {
//            return current;
//        }
//        while (current.next != front) {
//            current = current.next;
//            if (current.data == value) {
//                return current;
//            }
//        }
//        return null;
//    }

    /**
     * Removes the specified item from the list, if it exists there.
     *
     * @param value the element to remove from the list
     * @return true, if the element was found and removed; false, if not found or list is empty
     */
    @Override
    public boolean remove(E value) {
        int pos = indexOf(value);
        if (pos == -1) {
            return false;
        } else {
            remove(pos);
            return true;
        }
    }

    /**
     * Removes the node at the specified position in the list
     *
     * @param position position in the list; must be in range 0 to size - 1
     */
    @Override
    public void remove(int position) {
        if (position < 0 || position >= size) {
            throw new IndexOutOfBoundsException("position must be in range 0 to size - 1");
        }
        if (position == 0) {
            front = front.next;
            size--;
            return;
        }
        Node<E> current = front;
        for (int pos = 1; pos < position; pos++) {
            current = current.next;
        }
        if (position == size - 1) {
            end = current.next.next;
        }
        current.next = current.next.next;
        size--;

    }

    /**
     * Returns a String representation of the list
     * @return String of all the elements in the list
     */
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
     * @return a strongly typed iterator over elements in the list
     */
    @Override
    public Iterator<E> iterator() {
        return new CircularLinkedListIterator();
    }

    /**
     * Iterator for the CircularLinkedList
     */
    private class CircularLinkedListIterator implements Iterator<E> {

        /**
         * Current node being referenced by the iterator
         */
        public Node<E> current;

        /**
         * Constructor
         */
        public CircularLinkedListIterator() {
            current = null;
        }

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
                if (current == null) {
                    current = front;
                } else {
                    current = current.next;
                }
                return current.data;
            }
        }
    }
    /**
     * Container for element data
     * @param <T> Data type of element data to be stored
     */
    private static class Node<T> {
        /**
         * the data to be added to the list
         */
        public T data;
        /**
         * node that this node should connect to
         */
        public Node<T> next;

//basic constructors, did not use
//        public Node() {
//            this(null, null);
//        }
//        public Node(T data) {
//            this(data, null);
//        }

        /**
         * Constructor
         * @param data the data to be added to the list
         * @param next  node that this node should connect to
         */
        public Node(T data, Node<T> next) {
            this.data = data;
            this.next = next;
        }
    }
}
