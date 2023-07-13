package datastructures.maps;

import java.util.*;

public class Trie implements MapADT<String, Integer> {
    private TrieNode root;

    private static class TrieNode { //use just as a struct
        Map<Character, TrieNode> children;
        int value;

        TrieNode() {
            children = new HashMap<>();
            value = 0;
        }
    }

    public Trie() {
        root = new TrieNode();
    }

    @Override
    public int size() {
        return countWords(root);
    }

    private int countWords(TrieNode node) {
        int count = node.value;
        for (TrieNode child : node.children.values()) {
            count += countWords(child);
        }
        return count;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public Integer find(String key) {
        TrieNode node = findNode(key);
        return (node != null && node.value != 0) ? node.value : null;
    }

    private TrieNode findNode(String key) {
        TrieNode current = root;
        for (char c : key.toCharArray()) {
            current = current.children.get(c);
            if (current == null) {
                return null;
            }
        }
        return current;
    }

    @Override
    public void put(String key, Integer value) {
        TrieNode current = root;
        for (char c : key.toCharArray()) {
            current = current.children.computeIfAbsent(c, k -> new TrieNode());
        }
        current.value = value;
    }

    @Override
    public void remove(String key) {
        TrieNode node = findNode(key);
        if (node != null && node.value != 0) {
            node.value = 0;
        }
    }

    @Override
    public Iterable<String> keySet() {
        List<String> keys = new ArrayList<>();
        collectKeys(root, "", keys);
        return keys;
    }

    private void collectKeys(TrieNode node, String prefix, List<String> keys) {
        if (node.value != 0) {
            keys.add(prefix);
        }
        for (Map.Entry<Character, TrieNode> entry : node.children.entrySet()) {
            collectKeys(entry.getValue(), prefix + entry.getKey(), keys);
        }
    }

    @Override
    public Iterable<Integer> values() {
        List<Integer> values = new ArrayList<>();
        collectValues(root, values);
        return values;
    }

    private void collectValues(TrieNode node, List<Integer> values) {
        if (node.value != 0) {
            values.add(node.value);
        }
        for (TrieNode child : node.children.values()) {
            collectValues(child, values);
        }
    }

    @Override
    public Iterable<KeyValuePair<String, Integer>> entrySet() {
        List<KeyValuePair<String, Integer>> entries = new ArrayList<>();
        collectEntries(root, "", entries);
        return entries;
    }

    private void collectEntries(TrieNode node, String prefix, List<KeyValuePair<String, Integer>> entries) {
        if (node.value != 0) {
            entries.add(new KeyValuePair<>(prefix, node.value));
        }
        for (Map.Entry<Character, TrieNode> entry : node.children.entrySet()) {
            collectEntries(entry.getValue(), prefix + entry.getKey(), entries);
        }
    }
}
