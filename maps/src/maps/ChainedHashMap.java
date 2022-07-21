package maps;

import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * @see AbstractIterableMap
 * @see Map
 */
public class ChainedHashMap<K, V> extends AbstractIterableMap<K, V> {

    public static void main(String[] args)
    {
        // ChainedHashMap<Integer, Integer> map = new ChainedHashMap<>();
        // for (int i = 0; i < 100; i++) {
        //     map.put(i, i * i);
        // }
        //
        // for(int i = 0; i < 100;i++ )
        // {
        //     map.remove(i);
        // }
        // map.clear();
        // Iterator<Map.Entry<Integer, Integer>> itr = map.iterator();
        // System.out.println(map.remove(1));
        // System.out.println(itr.hasNext());

    }

    private static final double DEFAULT_RESIZING_LOAD_FACTOR_THRESHOLD = 3;
    private static final int DEFAULT_INITIAL_CHAIN_COUNT = 10;
    private static final int DEFAULT_INITIAL_CHAIN_CAPACITY = 30;
    private static double threshold = DEFAULT_RESIZING_LOAD_FACTOR_THRESHOLD;
    private static int chainCount = DEFAULT_INITIAL_CHAIN_COUNT;
    private static int chainCapacity = DEFAULT_INITIAL_CHAIN_CAPACITY;
    private int size = 0;
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
        for (int i = 0; i < initialChainCount; i++)
        {
            chains[i] = new ArrayMap<>(chainInitialCapacity);
        }
        threshold = resizingLoadFactorThreshold;
        chainCount = initialChainCount;
        chainCapacity = chainInitialCapacity;
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

    private int getHasCode(Object key)
    {
        if (key == null)
        {
            return 0;
        } else {
            return Math.abs(key.hashCode()) % chains.length;
        }
    }

    private void resize()
    {
        ChainedHashMap<K, V> temp = new ChainedHashMap<>(DEFAULT_RESIZING_LOAD_FACTOR_THRESHOLD,
            DEFAULT_INITIAL_CHAIN_COUNT*2, DEFAULT_INITIAL_CHAIN_CAPACITY);
        ChainedHashMapIterator<K, V> idk = new ChainedHashMapIterator<>(chains);
        while (idk.hasNext())
        {
            Entry<K, V> hi = idk.next();
            temp.put(hi.getKey(), hi.getValue());
        }
        chains = temp.chains;
        threshold *= 2;

    }

    @Override
    public V get(Object key) {

        return chains[getHasCode(key)].get(key);

        // if (containsKey(key))
        // {
        //     return chains[getHasCode(key)].get(key);
        // }
        // return null;
    }

    @Override
    public V put(K key, V value) {

        if (size >= threshold*chainCount)
        {
            resize();
        }

        V temp = chains[getHasCode(key)].put(key, value);
        if (temp == null)
        {
            size++;
            return null;
        }
        return temp;

        // if (containsKey(key))
        // {
        //     return chains[getHasCode(key)].put(key, value);
        // } else {
        //     chains[getHasCode(key)].put(key, value);
        //     size++;
        //
        //     return null;
        // }

    }

    @Override
    public V remove(Object key) {

        V temp = chains[getHasCode(key)].remove(key);
        if (temp != null)
        {
            size--;
            return temp;
        }
        return null;
        //
        // if (containsKey(key))
        // {
        //     size--;
        //     return chains[getHasCode(key)].remove(key);
        // } else {
        //     return null;
        // }
    }

    @Override
    public void clear() {

        for (AbstractIterableMap<K, V> chain : chains) {
            chain.clear();
        }
        size = 0;

    }

    @Override
    public boolean containsKey(Object key) {
        return chains[getHasCode(key)].containsKey(key);

    }

    @Override
    public int size() {
        return size;
    }



    @Override
    public Iterator<Map.Entry<K, V>> iterator() {
        // Note: you won't need to change this method (unless you add more constructor parameters)
        return new ChainedHashMapIterator<>(this.chains);
    }



    /*
    See the assignment webpage for tips and restrictions on implementing this iterator.
     */
    private static class ChainedHashMapIterator<K, V> implements Iterator<Map.Entry<K, V>> {
        private AbstractIterableMap<K, V>[] chains;
        private int index = 0;
        private Iterator<Map.Entry<K, V>> itr = null;

        public ChainedHashMapIterator(AbstractIterableMap<K, V>[] chains) {

            this.chains = chains;

        }

        @Override
        public boolean hasNext() {
            try
            {
                for (int i = index; i <= chains.length; i++)
                {

                    if (itr == null)
                    {
                        index = i;
                        itr = chains[index].entrySet().iterator();
                    }

                    if (itr.hasNext())
                    {
                        return true;
                    } else {
                        itr = null;
                    }
                }
            } catch (Exception ignored)
            {
                return false;
            }
            return false;
        }

        @Override
        public Map.Entry<K, V> next()
        {

            if (!hasNext()) {
                throw new NoSuchElementException("No more elements");
            } else {
                return itr.next();
            }

        }


    }


}
