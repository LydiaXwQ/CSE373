package maps;

import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
/**
 * @see AbstractIterableMap
 * @see Map
 */
public class ArrayMap<K, V> extends AbstractIterableMap<K, V> {
    // TODO: define a reasonable default value for the following field
    private int size;

    private int index;
    private static final int DEFAULT_INITIAL_CAPACITY = 10; //change 0 to 10;
    /*
    Warning:
    You may not rename this field or change its type.
    We will be inspecting it in our secret tests.
     */
    SimpleEntry<K, V>[] entries;

    // You may add extra fields or helper methods though!

    /**
     * Constructs a new ArrayMap with default initial capacity.
     */
    public ArrayMap() {
        this(DEFAULT_INITIAL_CAPACITY);
    }

    /**
     * Constructs a new ArrayMap with the given initial capacity (i.e., the initial
     * size of the internal array).
     *
     * @param initialCapacity the initial capacity of the ArrayMap. Must be > 0.
     */
    public ArrayMap(int initialCapacity) {
        this.entries = this.createArrayOfEntries(initialCapacity);
        size = 0;
    }

    /**
     * This method will return a new, empty array of the given size that can contain
     * {@code Entry<K, V>} objects.
     *
     * Note that each element in the array will initially be null.
     *
     * Note: You do not need to modify this method.
     */
    @SuppressWarnings("unchecked")
    private SimpleEntry<K, V>[] createArrayOfEntries(int arraySize) {
        /*
        It turns out that creating arrays of generic objects in Java is complicated due to something
        known as "type erasure."

        We've given you this helper method to help simplify this part of your assignment. Use this
        helper method as appropriate when implementing the rest of this class.

        You are not required to understand how this method works, what type erasure is, or how
        arrays and generics interact.
        */
        return (SimpleEntry<K, V>[]) (new SimpleEntry[arraySize]);
    }

    @Override
    public V get(Object key) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(entries[i].getKey(), key)) {
                this.index = i;
                return entries[i].getValue();
            }
        }
        return null;
    }

    @Override
    public V put(K key, V value) {
        // examine whether our arrayMap contains specified key.
        if (containsKey(key)) {
            // case1: our arrayMap contains specified key.
            return entries[index].setValue(value);
        } else {
            // case2: our ArrayMap doesn't contain the specified key

            // if there is no extra space for the new key and value. double entry length.
            if (entries.length == size) {
                SimpleEntry<K, V>[] newEntry = new SimpleEntry[entries.length * 2];
                for (int i = 0; i < size; i++) {
                    newEntry[i] = entries[i];
                }
                entries = newEntry;
            }
            entries[size] = new SimpleEntry(key, value);
            size++;
            return null;
        }
    }

    @Override
    public V remove(Object key) {
        //if arrayMap doesn't contain the specified key
        if (containsKey(key)) { // arrayMap contains the key
            V removeValue = get(key);
            entries[index] = entries[size - 1];
            size--;
            entries[size] = null;
            return removeValue;
        } else { // arrayMap doesn't contain the key
            return null;
        }
    }

    @Override
    public void clear() {
        for (int i = size - 1; i >= 0; i--) {
            entries[i] = null;
            size--;
        }
    }

    @Override
    public boolean containsKey(Object key) {
        for (int i = 0; i < this.size; i++) {
            if (Objects.equals(entries[i].getKey(), key)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<Map.Entry<K, V>> iterator() {
        // Note: You may or may not need to change this method, depending on whether you
        // add any parameters to the ArrayMapIterator constructor.
        return new ArrayMapIterator<>(this.entries);
    }

    // TODO: after you implement the iterator, remove this toString implementation
    // Doing so will give you a better string representation for assertion errors the debugger.
    @Override
    public String toString() {
        return super.toString();
    }

    private static class ArrayMapIterator<K, V> implements Iterator<Map.Entry<K, V>> {
        private final SimpleEntry<K, V>[] entries;
        // You may add more fields and constructor parameters
        private int index = 0;
        public ArrayMapIterator(SimpleEntry<K, V>[] entries) {
            this.entries = entries;
        }

        @Override
        public boolean hasNext() {
            /*first compare designed for the special case when the arrayMap is full,
            which means it doesn't contain null at end*/
            return index < entries.length && entries[index] != null;
        }

        @Override
        public Map.Entry<K, V> next() {
            if(!hasNext()) {
                throw new NoSuchElementException();
            } else {
                index++;
                return entries[index - 1];
            }

        }
    }
}
