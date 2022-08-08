package priorityqueues;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
//import java.util.Map;
import java.util.NoSuchElementException;

/**
 * @see ExtrinsicMinPQ
 */
public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T> {
    // IMPORTANT: Do not rename these fields or change their visibility.
    // We access these during grading to test your code.
    static final int START_INDEX = 1;
    List<PriorityNode<T>> items;
    HashMap<T, Integer> hashMap;

    public ArrayHeapMinPQ() {
        items = new ArrayList<>();
        hashMap = new HashMap<>();
        items.add(0, null);
    }

    // Here's a method stub that may be useful. Feel free to change or remove it, if you wish.
    // You'll probably want to add more helper methods like this one to make your code easier to read.
    /**
     * A helper method for swapping the items at two indices of the array heap.
     */
    private void swap(int a, int b) {
        PriorityNode<T> temp = items.get(a);
        hashMap.put(items.get(a).getItem(), b);
        items.set(a, items.get(b));
        hashMap.put(items.get(b).getItem(), a);
        items.set(b, temp);

    }

    private PriorityNode<T> parentNode(int i) {
        return items.get(i/2);
    }

    private int parentIndex(int i) {
        return i/2;
    }

    private int leftChildIndex(int i) {
        return 2*i;
    }

    private int rightChildIndex(int i) {
        return leftChildIndex(i) + 1;
    }

    private void percolateUp(int i) {
        int index = i;
        double priority = items.get(index).getPriority();
        while (parentNode(index) != null && priority < parentNode(index).getPriority()) {
            swap(parentIndex(index), index);
            index = parentIndex(index);
        }

    }

    private void percolateDown(int i) {
        int curIndex = i;
        double priority = items.get(curIndex).getPriority();
        int possibleSwapIndex = possibleSwap(curIndex);

        while (possibleSwapIndex != -1 && priority > items.get(possibleSwapIndex).getPriority()) {
            swap(possibleSwapIndex, curIndex);
            curIndex = possibleSwapIndex;
            possibleSwapIndex = possibleSwap(curIndex);
        }
    }

    private int possibleSwap(int index) {

        if (rightChildIndex(index) <= size()) {
            if (items.get(leftChildIndex(index)).getPriority() < items.get(rightChildIndex(index)).getPriority()) {
                return leftChildIndex(index);
            } else {
                return rightChildIndex(index);
            }
        } else if (leftChildIndex(index) <= size()) {
            return leftChildIndex(index);
        } else {
            return -1;
        }

    }

    @Override
    public void add(T item, double priority) {

        if (contains(item)) {
            throw new IllegalArgumentException();
        }

        PriorityNode<T> temp = new PriorityNode<>(item, priority);
        items.add(size()+1, temp);
        hashMap.put(item, size());
        percolateUp(size());

    }

    @Override
    public boolean contains(T item) {
        return hashMap.containsKey(item);
    }

    @Override
    public T peekMin() {
        if (size() == 0) {
            throw new NoSuchElementException("PQ is empty");
        } else {
            return items.get(START_INDEX).getItem();
        }
    }

    @Override
    public T removeMin() {
        if (size() == 0) {
            throw new NoSuchElementException("PQ is empty");
        }
        T temp = items.get(START_INDEX).getItem();
        hashMap.remove(temp);
        items.set(START_INDEX, items.get(size()));
        hashMap.put(items.get(START_INDEX).getItem(), START_INDEX);
        items.remove(size());
        if (size() > START_INDEX) {
            percolateDown(START_INDEX);
        }
        return temp;

    }

    @Override
    public void changePriority(T item, double priority) {

        if (!contains(item)) {
            throw new NoSuchElementException("PQ is empty");
        }
        int indexOfItem = hashMap.get(item);
        double oldPriority = items.get(indexOfItem).getPriority();
        items.get(indexOfItem).setPriority(priority);
        if (priority < oldPriority) {
            percolateUp(indexOfItem);
        } else {
            percolateDown(indexOfItem);
        }
    }

    @Override
    public int size() {
        return items.size()-1;
    }
}
