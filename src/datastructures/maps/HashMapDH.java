package datastructures.maps;

import datastructures.EmptyObject;
import datastructures.arrays.IArray;

import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * The main class, which implements the functionality of HashMap through the Open Addressing with Double Hashing.
 *
 * @param <K> - object which will be treated as key in HashMap.
 * @param <V> - object which will be treated as value in HashMap.
 */
public final class HashMapDH<K, V> implements MapADT<K, V> {
    /**
     * Minimal prime number for any HashMap.
     * <p>
     * Will be needed as a HashStep.
     */
    private final int minPrime = 3;

    /**
     * Factor of rehashing. That means that HashTable size multiply by 4 every time when HashTable is full.
     */
    private final int rehashingSize = 4;

    /**
     * Initial size for HashTable.
     */
    private final int maxHashtableSize = 100;
    private int size;
    private KeyValuePair<K, V>[] hashMap;
    private int closestLowerPrime;
    private final KeyValuePair<K, V> emptyPair;

    public HashMapDH() {
        size = maxHashtableSize;
        closestLowerPrime = closestLowerPrime(size);
        emptyPair = new KeyValuePair<>(null, null);
        hashMap = new KeyValuePair[size];
        for (int i = 0; i < size; ++i) {
            hashMap[i] = emptyPair;
        }
    }

    public HashMapDH(IArray keys, IArray entries) {
        size = maxHashtableSize;
        closestLowerPrime = closestLowerPrime(size);
        emptyPair = new KeyValuePair<>(null, null);
        hashMap = new KeyValuePair[size];
        for (int i = 0; i < size; ++i) {
            hashMap[i] = emptyPair;
        }

        putAll(keys, entries);
    }

    /**
     * Get the current size of the HashTable.
     *
     * @return int - size of HashTable.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Check if the HashTable is empty.
     *
     * @return boolean - true if Hashtable is empty; Otherwise, false.
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Get the value from the HashMap according to the received key.
     * Complexity: average case - O(1 / (1 - loadFactor)).
     *
     * @param key - object which will be treated as a key in HashMap.
     * @return V obj - object which is the value for the key in the current HashMap;
     * <p>
     * Otherwise, if no such key exists in HashMap - null.
     */
    @Override
    public V find(K key) {
        int startingIndex = startIndexWithHashing(key);
        int hashStep = specifiedHashStep(key);

        for (int i = 0; i < size(); i++) {
            int currentIndex = (startingIndex + i * hashStep) % size();

            if (hashMap[currentIndex] == emptyPair) {
                return (V) new EmptyObject();
            }

            if (hashMap[currentIndex].key.equals(key)) {
                return hashMap[currentIndex].value;
            }
        }
        throw new NoSuchElementException("No value with such key");
    }

    /**
     * Modified version of the insert function for HashMap.
     * In this program, we treat the date of the purchase as a key.
     * Hence, if no such date already inserted in HashMap - insert it as first.
     * Otherwise, if such date exists in HashMap - resubmit the V value for the current key.
     * (Sum of costs and adding ID in IDs array).
     * Last option, if HashTable is full --> rehash it.
     * Complexity: average case O(1 / (1 - loadFactor))
     *
     * @param key   - object which will be treated as a key in HashMap.
     * @param value - object which will be treated as a value in HashMap.
     */
    @Override
    public void put(K key, V value) {
        int startingIndex = startIndexWithHashing(key);
        int hashStep = specifiedHashStep(key);
        boolean noEmptySlots = true;

        for (int i = 0; i < size(); i++) {
            int currentIndex = (startingIndex + i * hashStep) % size();

            if (hashMap[currentIndex] == emptyPair) {
                noEmptySlots = false;
                hashMap[currentIndex] = new KeyValuePair<>(key, value);
                break;
            }
        }

        if (noEmptySlots) {
            rehash();
            put(key, value);
        }
    }

    public void putAll(IArray keys, IArray entries) {
        for (int i = 0; i < keys.size(); i++) {
            KeyValuePair<K, V> current = new KeyValuePair<>(
                    (K) keys.valueByIndex(i),
                    (V) entries.valueByIndex(i));
            put(current.key, current.value);
        }
    }

    @Override
    public void remove(K key) {
        int startingIndex = startIndexWithHashing(key);
        int hashStep = specifiedHashStep(key);

        for (int i = 0; i < size(); i++) {
            int currentIndex = (startingIndex + i * hashStep) % size();

            if (hashMap[currentIndex] == emptyPair) {
                return;
            }

            if (hashMap[currentIndex].key.equals(key)) {
                System.out.println("Removed successfully");
                hashMap[currentIndex] = emptyPair;
                return;
            }
        }
    }

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
     * Get the set of KeyValuePair<K, V>.
     *
     * @return List() - the set of KeyValuePair<K, V>
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
     * Rehash the table with a factor of 4. (Increase the size 4 times).
     */
    private void rehash() {
        size *= rehashingSize;
        closestLowerPrime = closestLowerPrime(size);

        KeyValuePair<K, V>[] oldHashMap = hashMap.clone();
        this.hashMap = new KeyValuePair[size];

        for (KeyValuePair<K, V> entry : oldHashMap) {
            if (entry != emptyPair) {
                put(entry.key, entry.value);
            }
        }
    }

    /**
     * Get the closest prime less than num.
     *
     * @param num - number
     * @return int - closest prime less than num.
     */
    private int closestLowerPrime(int num) {
        num--;
        while (!isPrime(num)) {
            num--;
        }

        return Math.max(num, minPrime);
    }

    /**
     * Check if the number is prime.
     *
     * @param num - int number.
     * @return boolean - true if the number is prime; Otherwise, false.
     */
    private boolean isPrime(int num) {
        for (int i = 2; i < (int) Math.sqrt(num) + 1; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Function which implements the function for finding the starting index.
     * Double Hashing: initial index (starting).
     *
     * @param key - object which will be treated as a key in HashMap.
     * @return int initial index.
     */
    private int startIndexWithHashing(K key) {
        return (key.hashCode() & 0xfffffff) % size;
    }

    /**
     * Function which implements the function for finding the hashing step.
     * Double Hashing: hash step.
     *
     * @param key - object which will be treated as a key in HashMap.
     * @return int hashStep.
     */
    private int specifiedHashStep(K key) {
        return closestLowerPrime - ((key.hashCode() & 0xfffffff) % closestLowerPrime);
    }

    @Override
    public void print() {
        System.out.println("Hasp Map DH:");
        for (KeyValuePair<K, V> pair: entrySet()) {
            System.out.println(pair.key + " : " + pair.value);
        }
    }

    @Override
    public void accept(MapAlgorithm<K, V> algorithm) {
        algorithm.implement(this);
    }
}
