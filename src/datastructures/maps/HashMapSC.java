package datastructures.maps;

import java.util.LinkedList;
import java.util.List;

public final class HashMapSC<K, V> implements MapADT<K, V> {
    private int mapSize;
    private int capacity;
    private List<KeyValuePair<K, V>>[] hashTable;
    private final KeyValuePair<K, V> emptyPair;

    public HashMapSC() {
        this.capacity = 16;
        this.mapSize = 0;
        this.hashTable = new List[capacity];
        emptyPair = new KeyValuePair<>(null, null);
        for (int i = 0; i < capacity; i++) {
            this.hashTable[i] = new LinkedList<>();
        }
    }

    public HashMapSC(int capacity) {
        this.capacity = capacity;
        this.mapSize = 0;
        this.hashTable = new List[capacity];
        emptyPair = new KeyValuePair<>(null, null);
        for (int i = 0; i < capacity; i++) {
            this.hashTable[i] = new LinkedList<>();
        }
    }

    private KeyValuePair<K, V> entry(K key) {
        int hash = key.hashCode() % this.capacity;
        for (int i = 0; i < this.hashTable[hash].size(); i++) {
            KeyValuePair<K, V> myKeyValuePair = hashTable[hash].get(i);
            if (myKeyValuePair.key.equals(key)) {
                return myKeyValuePair;
            }
        }
        return emptyPair;
    }

    @Override
    public int size() {
        return this.mapSize;
    }

    @Override
    public boolean isEmpty() {
        return this.mapSize == 0;
    }

    @Override
    public V find(K key) {
        KeyValuePair<K, V> entry = entry(key);
        if (entry != emptyPair) {
            return entry.value;
        }
        return null;
    }

    @Override
    public void put(K key, V value) {
        KeyValuePair<K, V> entry = entry(key);
        if (entry != emptyPair) {
            entry.value = value;
        } else {
            int hash = key.hashCode() % this.capacity;
            this.hashTable[hash].add(new KeyValuePair<>(key, value));
            this.mapSize++;
            if (loadFactor() > 0.75) {
                rehash();
            }
        }
    }

    @Override
    public void remove(K key) {
        int hash = key.hashCode() % this.capacity;
        List<KeyValuePair<K, V>> bucket = this.hashTable[hash];
        for (KeyValuePair<K, V> entry : bucket) {
            if (entry.key.equals(key)) {
                bucket.remove(entry);
                this.mapSize--;
                return;
            }
        }
    }

    @Override
    public Iterable<K> keySet() {
        List<K> keys = new LinkedList<>();
        for (List<KeyValuePair<K, V>> bucket : hashTable) {
            for (KeyValuePair<K, V> entry : bucket) {
                keys.add(entry.key);
            }
        }
        return keys;
    }

    @Override
    public Iterable<V> values() {
        List<V> values = new LinkedList<>();
        for (List<KeyValuePair<K, V>> bucket : hashTable) {
            for (KeyValuePair<K, V> entry : bucket) {
                values.add(entry.value);
            }
        }
        return values;
    }

    @Override
    public Iterable<KeyValuePair<K, V>> entrySet() {
        List<KeyValuePair<K, V>> entries = new LinkedList<>();
        for (List<KeyValuePair<K, V>> bucket : hashTable) {
            entries.addAll(bucket);
        }
        return entries;
    }

    public double loadFactor() {
        return (double) mapSize / (double) capacity;
    }

    public void rehash() {
        int newCapacity = capacity * 2;
        List<KeyValuePair<K, V>>[] newHashTable = new List[newCapacity];
        for (int i = 0; i < newCapacity; i++) {
            newHashTable[i] = new LinkedList<>();
        }
        for (List<KeyValuePair<K, V>> bucket : hashTable) {
            for (KeyValuePair<K, V> entry : bucket) {
                int hash = entry.key.hashCode() % newCapacity;
                newHashTable[hash].add(entry);
            }
        }
        capacity = newCapacity;
        hashTable = newHashTable;
    }

    @Override
    public void print() {
        System.out.println("Hash Map SC:");
        for (KeyValuePair<K, V> pair: entrySet()) {
            System.out.println(pair.key + " : " + pair.value);
        }
    }

    @Override
    public void accept(MapAlgorithm<K, V> algorithm) {
        algorithm.implement(this);
    }
}
