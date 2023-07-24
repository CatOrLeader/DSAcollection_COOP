package datastructures.maps;


import java.util.ArrayList;
import java.util.List;

public class LinkedHashMap<K, V> implements MapADT<K, V> {
    private LinkedHashMapNode head;
    private LinkedHashMapNode tail;
    private MapADT<K, LinkedHashMapNode> map;
    private final LinkedHashMapNode emptyPair;

    private class LinkedHashMapNode { //use just as a struct
        K key;
        V value;
        LinkedHashMapNode prev;
        LinkedHashMapNode next;

        LinkedHashMapNode(K key, V value) {
            this.key = key;
            this.value = value;
            prev = emptyPair;
            next = emptyPair;
        }
    }

    public LinkedHashMap(MapADT<K, LinkedHashMapNode> map) {
        emptyPair = new LinkedHashMapNode(null, null);
        head = emptyPair;
        tail = emptyPair;
        head.next = tail;
        tail.prev = head;
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
        LinkedHashMapNode node = map.find(key);
        if (node != emptyPair) {
            removeNode(node);
            addNodeToEnd(node);
            return node.value;
        }
        return emptyPair.value;
    }

    @Override
    public void put(K key, V value) {
        LinkedHashMapNode node = map.find(key);
        if (node != emptyPair) {
            node.value = value;
            removeNode(node);
            addNodeToEnd(node);
        } else {
            node = new LinkedHashMapNode(key, value);
            addNodeToEnd(node);
            map.put(key, node);
        }
    }

    @Override
    public void remove(K key) {
        LinkedHashMapNode node = map.find(key);
        if (node != emptyPair) {
            removeNode(node);
            map.remove(key);
        }
    }

    @Override
    public Iterable<K> keySet() {
        List<K> keys = new ArrayList<>();
        LinkedHashMapNode current = head.next;
        while (current != tail) {
            keys.add(current.key);
            current = current.next;
        }
        return keys;
    }

    @Override
    public Iterable<V> values() {
        List<V> values = new ArrayList<>();
        LinkedHashMapNode current = head.next;
        while (current != tail) {
            values.add(current.value);
            current = current.next;
        }
        return values;
    }

    @Override
    public Iterable<KeyValuePair<K, V>> entrySet() {
        List<KeyValuePair<K, V>> entries = new ArrayList<>();
        LinkedHashMapNode current = head.next;
        while (current != tail) {
            entries.add(new KeyValuePair<>(current.key, current.value));
            current = current.next;
        }
        return entries;
    }

    private void addNodeToEnd(LinkedHashMapNode node) {
        node.prev = tail.prev;
        node.next = tail;
        tail.prev.next = node;
        tail.prev = node;
    }

    private void removeNode(LinkedHashMapNode node) {
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
