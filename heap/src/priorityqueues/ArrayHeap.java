package priorityqueues;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

/**
 * @see ExtrinsicMinPQ
 */
public class ArrayHeap<T> implements ExtrinsicMinPQ<T> {
    // IMPORTANT: Do not rename these fields or change their visibility.
    // We access these during grading to test your code.
    static final int START_INDEX = 1;
    List<PriorityNode<T>> items;
    HashMap<T, PriorityNode<T>> hashMap;
    // TODO: add fields as necessary

    public ArrayHeap() {
        items = new ArrayList<>();
        hashMap = new HashMap<>();
        items.set(0, null);
        // TODO: add code as necessary
    }

    // Here's a method stub that may be useful. Feel free to change or remove it, if you wish.
    // You'll probably want to add more helper methods like this one to make your code easier to read.
    /**
     * A helper method for swapping the items at two indices of the array heap.
     */
    private void swap(int a, int b) {
        // TODO: replace this with your code
        PriorityNode<T> temp = items.get(a);
        items.set(a,items.get(b));
        items.set(b, temp);

    }

    @Override
    public void add(T item, double priority) {
        // TODO: replace this with your code
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    @Override
    public boolean contains(T item) {
        // TODO: replace this with your code
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    @Override
    public T peekMin() {
        // TODO: replace this with your code
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    @Override
    public T removeMin() {
        // TODO: replace this with your code
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    @Override
    public void changePriority(T item, double priority) {
        // TODO: replace this with your code
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    @Override
    public int size() {
        // TODO: replace this with your code
        throw new UnsupportedOperationException("Not implemented yet.");
    }
}
