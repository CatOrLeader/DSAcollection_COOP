package datastructures.maps;

import java.util.List;
import java.util.ArrayList;

public final class Dictionary<K, V> implements MapADT<K, V> {
    private MapADT<K, V> map;

    public Dictionary(MapADT<K, V> map) {
        this.map = map;
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public V find(K key) {
        System.out.println();
        return map.find(key);
    }

    @Override
    public void put(K key, V value) {
        map.put(key, value);
    }

    @Override
    public void remove(K key) {
        map.remove(key);
    }

    @Override
    public Iterable<K> keySet() {
        return map.keySet();
    }

    @Override
    public Iterable<V> values() {
        return map.values();
    }

    @Override
    public Iterable<KeyValuePair<K, V>> entrySet() {
        List<KeyValuePair<K, V>> entryList = new ArrayList<>();
        for (KeyValuePair<K, V> entry : map.entrySet()) {
            entryList.add(new KeyValuePair<>(entry.key, entry.value));
        }
        return entryList;
    }

    @Override
    public void print() {
        System.out.println("Dictionary:");
        for (KeyValuePair<K, V> pair: entrySet()) {
            System.out.println(pair.key + " : " + pair.value);
        }
    }

    @Override
    public void accept(MapAlgorithm<K, V> algorithm) {
        algorithm.implement(this);
    }
}

