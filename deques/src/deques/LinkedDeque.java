package deques;

/**
 * @see Deque
 */
public class LinkedDeque<T> extends AbstractDeque<T> {
    private int size;
    // IMPORTANT: Do not rename these fields or change their visibility.
    // We access these during grading to test your code.
    Node<T> front;
    Node<T> back;
    // Feel free to add any additional fields you may need, though.

    public LinkedDeque() {
        size = 0;
        front = new Node<>(null, null, null);
        back = new Node<>(null, front, null);
        front.next = back;
    }

    public void addFirst(T item) {
        size += 1;
        Node<T> node = new Node<>(item, front, front.next);
        front.next = node;
        node.next.prev = node;
    }

    public void addLast(T item) {
        size += 1;
        Node node = new Node<>(item, back.prev, back);
        node.prev.next = node;
        back.prev = node;
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        size -= 1;
        T value = front.next.value;
        front.next = front.next.next;
        front.next.prev = front;
        return value;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        size -= 1;
        T value = back.prev.value;
        back.prev = back.prev.prev;
        back.prev.next = back;
        return value;
    }

    public T get(int index) {
        if ((index >= size) || (index < 0)) {
            return null;
        }
        Node<T> curr = null;
        int mid = size / 2;
        if (index > mid) {
            curr = back;
            for (int i = size - 1; i >= index; i--) {
                curr = curr.prev;
            }
        } else {
            curr = front;
            for (int i = 0; i <= index; i++) {
                curr = curr.next;
            }
        }
        return curr.value;
    }

    public int size() {
        return size;
    }
}
