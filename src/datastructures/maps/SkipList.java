package datastructures.maps;


import java.util.ArrayList;
import java.util.List;

public final class SkipList<K extends Comparable<K>, V> implements MapADT<K, V> {
    private static final int MAX_LEVEL = 32;
    private Node head;
    private int size;
    private final Node emptyPair;

    private class Node { //use just as a struct
        K key;
        V value;
        List<Node> next;

        Node(K key, V value, int level) {
            this.key = key;
            this.value = value;
            this.next = new ArrayList<>(level);
        }
    }

    public SkipList() {
        emptyPair = new Node(null, null, MAX_LEVEL);
        head = emptyPair;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public V find(K key) {
        Node node = findNode(key);
        if (node != emptyPair && node.key.equals(key)) {
            return node.value;
        }
        return emptyPair.value;
    }

    private Node findNode(K key) {
        Node current = head;
        for (int level = head.next.size() - 1; level >= 0; level--) {
            while (current.next.get(level) != null && key.compareTo(current.next.get(level).key) > 0) {
                current = current.next.get(level);
            }
        }
        return current.next.get(0);
    }

    @Override
    public void put(K key, V value) {
        Node current = head;
        List<Node> update = new ArrayList<>(MAX_LEVEL);
        for (int level = head.next.size() - 1; level >= 0; level--) {
            while (current.next.get(level) != emptyPair
                    && key.compareTo(current.next.get(level).key) > 0) {
                current = current.next.get(level);
            }
            update.add(current);
        }
        current = current.next.get(0);
        if (current != emptyPair && current.key.equals(key)) {
            current.value = value;
        } else {
            int newLevel = randomLevel();
            Node newNode = new Node(key, value, newLevel);
            for (int level = 0; level < newLevel; level++) {
                newNode.next.add(level, update.get(level).next.get(level));
                update.get(level).next.set(level, newNode);
            }
            size++;
        }
    }

    @Override
    public void remove(K key) {
        Node current = head;
        List<Node> update = new ArrayList<>(MAX_LEVEL);
        for (int level = head.next.size() - 1; level >= 0; level--) {
            while (current.next.get(level) != emptyPair && key.compareTo(current.next.get(level).key) > 0) {
                current = current.next.get(level);
            }
            update.add(current);
        }
        current = current.next.get(0);
        if (current != emptyPair && current.key.equals(key)) {
            for (int level = 0; level < current.next.size(); level++) {
                update.get(level).next.set(level, current.next.get(level));
            }
            size--;
        }
    }

    @Override
    public Iterable<K> keySet() {
        List<K> keys = new ArrayList<>();
        Node current = head.next.get(0);
        while (current != emptyPair) {
            keys.add(current.key);
            current = current.next.get(0);
        }
        return keys;
    }

    @Override
    public Iterable<V> values() {
        List<V> values = new ArrayList<>();
        Node current = head.next.get(0);
        while (current != emptyPair) {
            values.add(current.value);
            current = current.next.get(0);
        }
        return values;
    }

    @Override
    public Iterable<KeyValuePair<K, V>> entrySet() {
        List<KeyValuePair<K, V>> entries = new ArrayList<>();
        Node current = head.next.get(0);
        while (current != emptyPair) {
            entries.add(new KeyValuePair<>(current.key, current.value));
            current = current.next.get(0);
        }
        return entries;
    }

    private int randomLevel() {
        int level = 1;
        while (Math.random() < 0.5 && level < MAX_LEVEL) {
            level++;
        }
        return level;
    }

    @Override
    public void print() {
        System.out.println("Skip List:");
        for (KeyValuePair<K, V> pair: entrySet()) {
            System.out.println(pair.key + " : " + pair.value);
        }
    }

    @Override
    public void accept(MapAlgorithm<K, V> algorithm) {
        algorithm.implement(this);
    }
}
