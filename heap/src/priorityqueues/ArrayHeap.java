package priorityqueues;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.lang.Math;

/**
 * @see ExtrinsicMinPQ
 */
public class ArrayHeap<T> implements ExtrinsicMinPQ<T> {
    // IMPORTANT: Do not rename these fields or change their visibility.
    // We access these during grading to test your code.
    static final int START_INDEX = 1;
    List<PriorityNode<T>> items;
    HashMap<T, Integer>> hashMap;
    // TODO: add fields as necessary

    public ArrayHeap() {
        items = new ArrayList<>();
        hashMap = new HashMap<>();
        //items.set(0, null);
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
        hashMap.put(items.get(a).getItem(), b);
        items.set(a,items.get(b));
        hashMap.put(items.get(b).getItem, a);
        items.set(b, temp);

    }

    private PriorityNode<T> parentNode(int i){
        return items.get(i/2);
    }

    private int parentIndex(int i){
        return i/2;
    }

    private int leftChildIndex(int i){
        return 2*i; 
    }

    private int rightChildIndex(int i){
        return leftChild(i) + 1; 
    }

    @Override
    public void add(T item, double priority) {
        // TODO: replace this with your code

        if(containsKey(item)){
            throw new IllegalArgumentException();
        }

        PriorityNode<T> temp = new PriorityNode<>(item,priority);
        items.set(size()+1, temp);
        int index = size();
        hashMap.put(item,index);
        while(priority < parentNode(index).getPriority && parentIndex(index) != 0){
            swap(parentIndex(index), index)
            index = parentIndex(index);
        }

        //throw new UnsupportedOperationException("Not implemented yet.");
    }

    @Override
    public boolean contains(T item) {
        // TODO: replace this with your code
        return hashMap.containsKey(item);
        //throw new UnsupportedOperationException("Not implemented yet.");
    }

    @Override
    public T peekMin() {
        // TODO: replace this with your code
        if(size() == 0){
            throw new NoSuchElementException("PQ is empty");; 
        } else {
            return items.get(START_INDEX);
        }
        //throw new UnsupportedOperationException("Not implemented yet.");
    }

    @Override
    public T removeMin() {
        // TODO: replace this with your code
        if(size() == 0){
            throw new NoSuchElementException("PQ is empty");; 
        }
        T temp = items.get(START_INDEX);
        hashMap.remove(temp);
        items.set(START_INDEX, items.get(size()));
        hashMap.put(items.get(START_INDEX), START_INDEX);
        items.remove(size());
        int curIndex = START_INDEX;
        if(size() > START_INDEX){
            int possibleSwapIndex; 
            if(items.get(leftChildIndex(curIndex)).getPriority() < items.get(rightChildIndex(curIndex)).getPriority()){
                possibleSwapIndex = leftChildIndex(curIndex)
            } else {
                possibleSwapIndex = rightChildIndex(curIndex);
            }
            while(items.get(curIndex).getPriority() > items.get(possibleSwapIndex).getPriority()){
                swap(possibleSwapIndex, curIndex);
                curIndex = possibleSwapIndex; 
                if(items.get(leftChildIndex(curIndex)).getPriority() < items.get(rightChildIndex(curIndex)).getPriority()) {
                possibleSwapIndex = leftChildIndex(curIndex)
                } else {
                possibleSwapIndex = rightChildIndex(curIndex);
                }
            }
        }
        return temp; 
        

        //throw new UnsupportedOperationException("Not implemented yet.");
    }

    @Override
    public void changePriority(T item, double priority) {
        // TODO: replace this with your code
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    @Override
    public int size() {
        // TODO: replace this with your code
        return items.size();
        //throw new UnsupportedOperationException("Not implemented yet.");
    }
}
