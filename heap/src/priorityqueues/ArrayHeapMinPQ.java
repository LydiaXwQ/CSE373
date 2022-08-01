package priorityqueues;

import edu.princeton.cs.algs4.StdOut;
import maps.ChainedHashMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

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

    private Map<T, Integer> map;

    public static void main(String[] args) {
        ExtrinsicMinPQ<String> test = new ArrayHeapMinPQ<>();
        test.add("a", 1.0);
        // test.add("c", 3.0);
        // test.add("d", 4.0);
        System.out.println(test.removeMin().toString());


    }

    public ArrayHeapMinPQ() {
        items = new ArrayList<>();
        items.add(0, new PriorityNode<>(null, -99999999));
        map = new HashMap<>();
    }

    // Here's a method stub that may be useful. Feel free to change or remove it, if you wish.
    // You'll probably want to add more helper methods like this one to make your code easier to read.

    /**
     * A helper method for swapping the items at two indices of the array heap.
     */
    private void swap(int parentIndex, int idk, T parentItem) {
        // only change parent's index
        PriorityNode<T> temp = items.get(parentIndex);
        items.set(parentIndex, items.get(idk));
        items.set(idk, temp);
        map.put(parentItem, idk);
    }

    @Override
    public void add(T item, double priority) {
        if (map.containsKey(item)) {
            throw new IllegalArgumentException();
        }

        //increase the size
        size++;
        //add new item at the end of the ArrayList
        items.add(size, new PriorityNode<>(item, priority));
        // while (items.get(parentIndex).getPriority() > items.get(index).getPriority()) {
        //     swap(parentIndex, index, items.get(parentIndex).getItem());
        //     index = parentIndex;
        //     parentIndex = parentIndex / 2;
        // }
        int index = percolateUp(size);
        //add the new item to the map
        map.put(item, index);
    }

    //return the final index of the new item
    // second condition for adding new item with negative infinity priority
    private int percolateUp(int index) {
        int parentIndex = index / 2;
        if (items.get(parentIndex).getPriority() > items.get(index).getPriority() && parentIndex != 0) {
            swap(parentIndex, index, items.get(parentIndex).getItem());
            index = parentIndex;
            percolateUp(index);
        }
        return index;
    }
    @Override
    public boolean contains(T item) {
        return map.containsKey(item);
        //throw new UnsupportedOperationException("Not implemented yet.");
    }

    @Override
    public T peekMin() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        return items.get(1).getItem();
        //throw new UnsupportedOperationException("Not implemented yet.");
    }

    @Override
    public T removeMin() {
        T minItem = peekMin();
        map.remove(minItem);
        swap(size, 1, items.get(size).getItem());
        items.remove(size);
        size--;

        //percolate down
        if (size != 0) {
            percolateDown(START_INDEX);
        }
        return minItem;
        //throw new UnsupportedOperationException("Not implemented yet.");
    }

    private void percolateDown(int index) {
        int leftChild = 2 * index;
        // initial index of right child
        int rightChild = 2 * index + 1;

        if (rightChild <= size) {
            double priority = items.get(index).getPriority();
            double leftPriority = items.get(leftChild).getPriority();
            double rightPriority = items.get(rightChild).getPriority();
            if (priority > leftPriority && leftPriority < rightPriority) {
                swap(leftChild, index, items.get(leftChild).getItem());
                percolateDown(leftChild);
            } else if(priority > rightPriority) {
                swap(rightChild, index, items.get(rightChild).getItem());
                percolateDown(rightChild);
            } else {
                map.put(items.get(index).getItem(), index);
            }
        } else if(leftChild <= size) {
            double priority = items.get(index).getPriority();
            double leftPriority = items.get(leftChild).getPriority();
            if (priority > leftPriority) {
                swap(leftChild, index, items.get(leftChild).getItem());
            } else {
                map.put(items.get(index).getItem(), index);
            }
        }else {
            map.put(items.get(index).getItem(), index);
        }

    }

    @Override
    public void changePriority(T item, double priority) {
        if (!map.containsKey(item)) {
            throw new NoSuchElementException();
        }
        int index = map.get(item);
        double oldPriority = items.get(index).getPriority();
        items.get(index).setPriority(priority);
        if (priority < oldPriority) {
            percolateUp(index);
        } else {
            percolateDown(index);
        }

        //throw new UnsupportedOperationException("Not implemented yet.");
    }

    @Override
    public int size() {
        return size;
    }
}
