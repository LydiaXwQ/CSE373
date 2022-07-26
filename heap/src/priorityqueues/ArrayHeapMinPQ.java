package priorityqueues;

import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.List;

/**
 * @see ExtrinsicMinPQ
 */
public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T> {
    // IMPORTANT: Do not rename these fields or change their visibility.
    // We access these during grading to test your code.
    static final int START_INDEX = 1;
    private int size = 0;
    private int addIndex = START_INDEX;
    List<PriorityNode<T>> items;


    public static void main(String[] args) {
        ArrayHeapMinPQ<String> test = new ArrayHeapMinPQ<>();
        test.add("I", 1.0);
        System.out.println();

    }

    public ArrayHeapMinPQ() {
        items = new ArrayList<>();
        items.add(0, null);

    }

    // Here's a method stub that may be useful. Feel free to change or remove it, if you wish.
    // You'll probably want to add more helper methods like this one to make your code easier to read.

    /**
     * A helper method for swapping the items at two indices of the array heap.
     */
    private void swap(int a, int b) {
        PriorityNode<T> temp = items.get(a);
        items.set(a, items.get(b));
        items.set(b, temp);
    }

    @Override
    public void add(T item, double priority) {
        items.add(addIndex, new PriorityNode<>(item, priority));
        size++;
        int parentIndex = size / 2;
        int index = size;
        while (parentIndex != 0 && items.get(parentIndex).getPriority() > items.get(index).getPriority()) {
            swap(parentIndex, size);
            index = parentIndex;
            parentIndex = parentIndex / 2;
        }

        addIndex++;

        // if (items.get(parentIndex).getPriority() > items.get(index).getPriority()) {
        //     swap(parentIndex, index);
        // }

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
        return size;
    }
}
