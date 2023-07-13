package datastructures.maps;

import java.util.*;
public class TreeMap<K extends Comparable<K>, V> implements MapADT<K, V> {
    private Node root;
    private int size;

    private class Node { //use just as a struct
        K key;
        V value;
        Node left, right;
        int height;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
            this.height = 1;
        }
    }

    public TreeMap() {
        root = null;
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
        Node node = findNode(root, key);
        return (node != null) ? node.value : null;
    }

    private Node findNode(Node node, K key) {
        if (node == null || key.equals(node.key)) {
            return node;
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            return findNode(node.left, key);
        } else {
            return findNode(node.right, key);
        }
    }

    @Override
    public void put(K key, V value) {
        root = putNode(root, key, value);
    }

    private Node putNode(Node node, K key, V value) {
        if (node == null) {
            size++;
            return new Node(key, value);
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = putNode(node.left, key, value);
        } else if (cmp > 0) {
            node.right = putNode(node.right, key, value);
        } else {
            node.value = value;
        }
        updateHeight(node);
        int balance = getBalance(node);
        if (balance > 1 && key.compareTo(node.left.key) < 0) {
            return rightRotate(node);
        }
        if (balance < -1 && key.compareTo(node.right.key) > 0) {
            return leftRotate(node);
        }
        if (balance > 1 && key.compareTo(node.left.key) > 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        if (balance < -1 && key.compareTo(node.right.key) < 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
        return node;
    }

    @Override
    public void remove(K key) {
        root = removeNode(root, key);
    }

    private Node removeNode(Node node, K key) {
        if (node == null) {
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = removeNode(node.left, key);
        } else if (cmp > 0) {
            node.right = removeNode(node.right, key);
        } else {
            if (node.left == null || node.right == null) {
                Node child = (node.left != null) ? node.left : node.right;
                if (child == null) {
                    return null;
                }
                node = child;
            } else {
                Node successor = getMinimumNode(node.right);
                node.key = successor.key;
                node.value = successor.value;
                node.right = removeNode(node.right, successor.key);
            }
        }
        if (node == null) {
            return null;
        }
        updateHeight(node);
        int balance = getBalance(node);
        if (balance > 1 && getBalance(node.left) >= 0) {
            return rightRotate(node);
        }
        if (balance > 1 && getBalance(node.left) < 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        if (balance < -1 && getBalance(node.right) <= 0) {
            return leftRotate(node);
        }
        if (balance < -1 && getBalance(node.right) > 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
        return node;
    }

    private Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;
        x.right = y;
        y.left = T2;
        updateHeight(y);
        updateHeight(x);
        return x;
    }

    private Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;
        y.left = x;
        x.right = T2;
        updateHeight(x);
        updateHeight(y);
        return y;
    }

    private int getHeight(Node node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }

    private void updateHeight(Node node) {
        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
    }

    private int getBalance(Node node) {
        if (node == null) {
            return 0;
        }
        return getHeight(node.left) - getHeight(node.right);
    }

    private Node getMinimumNode(Node node) {
        if (node.left == null) {
            return node;
        }
        return getMinimumNode(node.left);
    }

    @Override
    public Iterable<K> keySet() {
        List<K> keys = new ArrayList<>();
        inorderTraversal(root, keys);
        return keys;
    }

    private void inorderTraversal(Node node, List<K> keys) {
        if (node != null) {
            inorderTraversal(node.left, keys);
            keys.add(node.key);
            inorderTraversal(node.right, keys);
        }
    }

    @Override
    public Iterable<V> values() {
        List<V> values = new ArrayList<>();
        inorderValueTraversal(root, values);
        return values;
    }

    private void inorderValueTraversal(Node node, List<V> values) {
        if (node != null) {
            inorderValueTraversal(node.left, values);
            values.add(node.value);
            inorderValueTraversal(node.right, values);
        }
    }

    @Override
    public Iterable<KeyValuePair<K, V>> entrySet() {
        List<KeyValuePair<K, V>> entries = new ArrayList<>();
        inorderEntryTraversal(root, entries);
        return entries;
    }

    private void inorderEntryTraversal(Node node, List<KeyValuePair<K, V>> entries) {
        if (node != null) {
            inorderEntryTraversal(node.left, entries);
            entries.add(new KeyValuePair<>(node.key, node.value));
            inorderEntryTraversal(node.right, entries);
        }
    }
}
