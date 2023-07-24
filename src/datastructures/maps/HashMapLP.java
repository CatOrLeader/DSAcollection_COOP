package datastructures.maps;

import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * The main class, which implements the functionality of HashMap through Open Addressing with Linear Probing.
 *
 * @param <K> - object which will be treated as the key in HashMap.
 * @param <V> - object which will be treated as the value in HashMap.
 */
public class HashMapLP<K, V> implements MapADT<K, V> {
    private final int maxHashtableSize = 100;
    private int size;
    private KeyValuePair<K, V>[] hashMap;
    private final KeyValuePair<K, V> emptyPair;

    public HashMapLP() {
        size = maxHashtableSize;
        emptyPair = new KeyValuePair<>(null, null);
        hashMap = new KeyValuePair[size];
        for (int i = 0; i < size; ++i) {
            hashMap[i] = emptyPair;
        }
    }

    /**
     * Get the current size of the HashMap.
     *
     * @return int - size of the HashMap.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Check if the HashMap is empty.
     *
     * @return boolean - true if the HashMap is empty; Otherwise, false.
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Get the value from the HashMap based on the given key.
     * Complexity: average case - O(1).
     *
     * @param key - object which will be treated as a key in the HashMap.
     * @return V obj - the value associated with the key in the HashMap;
     * <p>
     * Otherwise, if no such key exists in the HashMap - null.
     */
    @Override
    public V find(K key) {
        int index = hashIndex(key);

        for (int i = 0; i < size(); i++) {
            int currentIndex = (index + i) % size();

            if (hashMap[currentIndex] == emptyPair) {
                throw new NoSuchElementException("No value with such key");
            }

            if (hashMap[currentIndex].key.equals(key)) {
                return hashMap[currentIndex].value;
            }
        }
        throw new NoSuchElementException("No value with such key");
    }

    /**
     * Insert a key-value pair into the HashMap using linear probing for collision resolution.
     * If the HashMap is full, it will not insert the pair.
     * Complexity: average case O(1).
     *
     * @param key   - object which will be treated as a key in the HashMap.
     * @param value - object which will be treated as a value in the HashMap.
     */
    @Override
    public void put(K key, V value) {
        int index = hashIndex(key);

        for (int i = 0; i < size(); i++) {
            int currentIndex = (index + i) % size();

            if (hashMap[currentIndex] == emptyPair) {
                hashMap[currentIndex] = new KeyValuePair<>(key, value);
                return;
            }
        }
    }

    /**
     * Remove the key-value pair from the HashMap based on the given key.
     *
     * @param key - object which will be treated as a key in the HashMap.
     */
    @Override
    public void remove(K key) {
        int index = hashIndex(key);

        for (int i = 0; i < size(); i++) {
            int currentIndex = (index + i) % size();

            if (hashMap[currentIndex] == emptyPair) {
                return;
            }

            if (hashMap[currentIndex].key.equals(key)) {
                hashMap[currentIndex] = emptyPair;
                return;
            }
        }
    }

    /**
     * Get an iterable of all the keys present in the HashMap.
     *
     * @return Iterable<K> - iterable of all keys in the HashMap.
     */
    @Override
    public Iterable<K> keySet() {
        ArrayList<K> keys = new ArrayList<>();

        for (KeyValuePair<K, V> entry : hashMap) {
            if (entry != emptyPair) {
                keys.add(entry.key);
            }
        }

        return keys;
    }

    /**
     * Get an iterable of all the values present in the HashMap.
     *
     * @return Iterable<V> - iterable of all values in the HashMap.
     */
    @Override
    public Iterable<V> values() {
        ArrayList<V> values = new ArrayList<>();

        for (KeyValuePair<K, V> entry : hashMap) {
            if (entry != emptyPair) {
                values.add(entry.value);
            }
        }

        return values;
    }

    /**
     * Get an iterable of all the key-value pairs present in the HashMap.
     *
     * @return Iterable<KeyValuePair<K, V>> - iterable of all key-value pairs in the HashMap.
     */
    @Override
    public Iterable<KeyValuePair<K, V>> entrySet() {
        ArrayList<KeyValuePair<K, V>> entries = new ArrayList<>();

        for (KeyValuePair<K, V> entry : hashMap) {
            if (entry != emptyPair) {
                entries.add(entry);
            }
        }

        return entries;
    }

    /**
     * Get the hash index for a given key.
     *
     * @param key - object which will be treated as a key in the HashMap.
     * @return int - hash index for the key.
     */
    private int hashIndex(K key) {
        return key.hashCode() % size();
    }

    @Override
    public void print() {
        System.out.println("Dictionary:");
        for (KeyValuePair<K, V> pair: entrySet()) {
            System.out.println(pair.key + " : " + pair.value);
        }
    }
}
