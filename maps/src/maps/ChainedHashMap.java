package maps;

import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * @see AbstractIterableMap
 * @see Map
 */
public class ChainedHashMap<K, V> extends AbstractIterableMap<K, V> {

    public static void main(String[] args){
        int temp = "someKey".hashCode() % 2;
        System.out.println(temp);
    }

    private static final double DEFAULT_RESIZING_LOAD_FACTOR_THRESHOLD = 0.80;
    private static final int DEFAULT_INITIAL_CHAIN_COUNT = 2;
    private static final int DEFAULT_INITIAL_CHAIN_CAPACITY = 3;
    private static double maxLoad;
    private int size = 0;
    private int index = 0;
    /*
    Warning:
    You may not rename this field or change its type.
    We will be inspecting it in our secret tests.
     */
    AbstractIterableMap<K, V>[] chains;

    // You're encouraged to add extra fields (and helper methods) though!

    /**
     * Constructs a new ChainedHashMap with default resizing load factor threshold,
     * default initial chain count, and default initial chain capacity.
     */
    public ChainedHashMap() {
        this(DEFAULT_RESIZING_LOAD_FACTOR_THRESHOLD, DEFAULT_INITIAL_CHAIN_COUNT, DEFAULT_INITIAL_CHAIN_CAPACITY);
    }

    /**
     * Constructs a new ChainedHashMap with the given parameters.
     *
     * @param resizingLoadFactorThreshold the load factor threshold for resizing. When the load factor
     *                                    exceeds this value, the hash table resizes. Must be > 0.
     * @param initialChainCount the initial number of chains for your hash table. Must be > 0.
     * @param chainInitialCapacity the initial capacity of each ArrayMap chain created by the map.
     *                             Must be > 0.
     */
    public ChainedHashMap(double resizingLoadFactorThreshold, int initialChainCount, int chainInitialCapacity) {
        this.chains = createArrayOfChains(initialChainCount);
        for(int i = 0; i < initialChainCount; i++){
            chains[i] = new ArrayMap<>(chainInitialCapacity);
        }
        maxLoad = initialChainCount * chainInitialCapacity * resizingLoadFactorThreshold;

    }

    /**
     * This method will return a new, empty array of the given size that can contain
     * {@code AbstractIterableMap<K, V>} objects.
     *
     * Note that each element in the array will initially be null.
     *
     * Note: You do not need to modify this method.
     * @see ArrayMap createArrayOfEntries method for more background on why we need this method
     */
    @SuppressWarnings("unchecked")
    private AbstractIterableMap<K, V>[] createArrayOfChains(int arraySize) {
        return (AbstractIterableMap<K, V>[]) new AbstractIterableMap[arraySize];
    }

    /**
     * Returns a new chain.
     *
     * This method will be overridden by the grader so that your ChainedHashMap implementation
     * is graded using our solution ArrayMaps.
     *
     * Note: You do not need to modify this method.
     */
    protected AbstractIterableMap<K, V> createChain(int initialSize) {
        return new ArrayMap<>(initialSize);
    }

    private int getHasCode(Object key){
        if(key == null){
            return 0;
        } else {
            return Math.abs(key.hashCode()) % chains.length;
        }
    }

    @Override
    public V get(Object key) {
        // TODO: replace this with your code
        if(containsKey(key)){
            return chains[getHasCode(key)].get(key);
        }
        return null;
        //throw new UnsupportedOperationException("Not implemented yet.");
    }

    @Override
    public V put(K key, V value) {

        if(containsKey(key)){
            return chains[getHasCode(key)].put(key, value);
        } else {
            chains[getHasCode(key)].put(key, value);
            size++;
            return null;
        }

    }

    @Override
    public V remove(Object key) {
        // TODO: replace this with your code

        if(containsKey(key)){
            size--;
            return chains[getHasCode(key)].remove(key);
        } else {
            return null;
        }
    }

    @Override
    public void clear() {
        // TODO: replace this with your code
        // for(int i = 0; i < chains.length; i++){
        //     chains[i].clear();
        // }

        chains = createArrayOfChains(chains.length);
        //throw new UnsupportedOperationException("Not implemented yet.");
    }

    @Override
    public boolean containsKey(Object key) {
        // TODO: replace this with your code
        return chains[getHasCode(key)].containsKey(key);

        // int temp = getHasCode(key);
        // if(chains[temp] != null){
        //     for(Entry<K,V> idk: chains[temp]){
        //         if(idk.getKey().equals(key)){
        //             return true;
        //         }
        //     }
        // }
        //
        // return false;

        //throw new UnsupportedOperationException("Not implemented yet.");
    }

    @Override
    public int size() {
        // TODO: replace this with your code
        return size;
        //throw new UnsupportedOperationException("Not implemented yet.");
    }

    public int index() {
        // TODO: replace this with your code
        return index;
        //throw new UnsupportedOperationException("Not implemented yet.");
    }

    @Override
    public Iterator<Map.Entry<K, V>> iterator() {
        // Note: you won't need to change this method (unless you add more constructor parameters)
        return new ChainedHashMapIterator<>(this.chains);
    }

    // TODO: after you implement the iterator, remove this toString implementation
    // Doing so will give you a better string representation for assertion errors the debugger.
    @Override
    public String toString() {
        return super.toString();
    }

    /*
    See the assignment webpage for tips and restrictions on implementing this iterator.
     */
    private static class ChainedHashMapIterator<K, V> implements Iterator<Map.Entry<K, V>> {
        private AbstractIterableMap<K, V>[] chains;
        private int index = 0;
        //private Entry<K,V> entry = null;
        private final Iterator<Entry<K,V>>[] mapIteratorArray;
        // You may add more fields and constructor parameters

        public ChainedHashMapIterator(AbstractIterableMap<K, V>[] chains) {

            this.chains = chains;
            this.mapIteratorArray = new Iterator[chains.length];
        }

        @Override
        public boolean hasNext() {
            // TODO: replace this with your code
            for(int i = index; i < chains.length; i++){
                if(mapIteratorArray[i] == null){
                    mapIteratorArray[i] = chains[i].iterator();
                }
                if(mapIteratorArray[i].hasNext()){
                    return true;
                }
            }
            return false;
            //throw new UnsupportedOperationException("Not implemented yet.");
        }

        @Override
        public Map.Entry<K, V> next() {
            // TODO: replace this with your code

            if(hasNext()){

                for(int i = index; i < chains.length; i++){
                    if(mapIteratorArray[i].hasNext()){
                        return mapIteratorArray[i].next();
                    }
                }

            }
            throw new NoSuchElementException() ;

            //throw new UnsupportedOperationException("Not implemented yet.");
        }
    }


    //                 for(int j = 0; j < chains[i].entrySet())
    //
    //
    //     for(Entry<K,V> temp: chains[i]){
    //     if(temp == null){
    //         break;
    //     }
    //     entry = temp;
    //     index = i;
    //     return true;
    // }

}
