package datastructures.maps;

import java.util.*;

public class LinkedHashMap<K, V> implements MapADT<K, V> {
    private LinkedHashMapNode<K, V> head;
    private LinkedHashMapNode<K, V> tail;
    private Map<K, LinkedHashMapNode<K, V>> map;

    private static class LinkedHashMapNode<K, V> { //use just as a struct
        K key;
        V value;
        LinkedHashMapNode<K, V> prev;
        LinkedHashMapNode<K, V> next;

        LinkedHashMapNode(K key, V value) {
            this.key = key;
            this.value = value;
            prev = null;
            next = null;
        }
    }

    public LinkedHashMap() {
        head = new LinkedHashMapNode<>(null, null);
        tail = new LinkedHashMapNode<>(null, null);
        head.next = tail;
        tail.prev = head;
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
        LinkedHashMapNode<K, V> node = map.get(key);
        if (node != null) {
            removeNode(node);
            addNodeToEnd(node);
            return node.value;
        }
        return null;
    }

    @Override
    public void put(K key, V value) {
        LinkedHashMapNode<K, V> node = map.get(key);
        if (node != null) {
            node.value = value;
            removeNode(node);
            addNodeToEnd(node);
        } else {
            node = new LinkedHashMapNode<>(key, value);
            addNodeToEnd(node);
            map.put(key, node);
        }
    }

    @Override
    public void remove(K key) {
        LinkedHashMapNode<K, V> node = map.get(key);
        if (node != null) {
            removeNode(node);
            map.remove(key);
        }
    }

    @Override
    public Iterable<K> keySet() {
        List<K> keys = new ArrayList<>();
        LinkedHashMapNode<K, V> current = head.next;
        while (current != tail) {
            keys.add(current.key);
            current = current.next;
        }
        return keys;
    }

    @Override
    public Iterable<V> values() {
        List<V> values = new ArrayList<>();
        LinkedHashMapNode<K, V> current = head.next;
        while (current != tail) {
            values.add(current.value);
            current = current.next;
        }
        return values;
    }

    @Override
    public Iterable<KeyValuePair<K, V>> entrySet() {
        List<KeyValuePair<K, V>> entries = new ArrayList<>();
        LinkedHashMapNode<K, V> current = head.next;
        while (current != tail) {
            entries.add(new KeyValuePair<>(current.key, current.value));
            current = current.next;
        }
        return entries;
    }

    private void addNodeToEnd(LinkedHashMapNode<K, V> node) {
        node.prev = tail.prev;
        node.next = tail;
        tail.prev.next = node;
        tail.prev = node;
    }

    private void removeNode(LinkedHashMapNode<K, V> node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    @Override
    public void print() {
        System.out.println("Dictionary:");
        for (KeyValuePair<K, V> pair: entrySet()) {
            System.out.println(pair.key + " : " + pair.value);
        }
    }
}
