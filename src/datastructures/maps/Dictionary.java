package datastructures.maps;

import java.util.*;

public class Dictionary<K, V> implements MapADT<K, V> {
    private HashMap<K, V> map;

    public Dictionary() {
        map = new HashMap<>();
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
        return map.get(key);
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
        for (Map.Entry<K, V> entry : map.entrySet()) {
            entryList.add(new KeyValuePair<>(entry.getKey(), entry.getValue()));
        }
        return entryList;
    }
}

