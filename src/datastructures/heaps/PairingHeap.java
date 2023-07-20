package datastructures.heaps;

import datastructures.arrays.IArray;

import java.util.LinkedList;
import java.util.Queue;

public class PairingHeap<T extends Comparable<T>> implements Heap<T> {

    private Node root;
    private int size;

    private class Node { //use just as a struct
        T key;
        Node leftChild;
        Node nextSibling;

        Node(T key) {
            this.key = key;
            this.leftChild = null;
            this.nextSibling = null;
        }
    }

    public PairingHeap() {
        this.root = null;
        this.size = 0;
    }

    public PairingHeap(IArray<T> array) {
        this.root = null;
        this.size = 0;
        for (T i: array) {
            insert(i);
        }
    }

    public void insert(T element) {
        Node newNode = new Node(element);
        if (isEmpty()) {
            root = newNode;
        } else {
            root = merge(root, newNode);
        }
        size++;
    }

    public T findMin() {
        if (isEmpty()) {
            throw new IllegalStateException("Empty Heap!");
        }
        return root.key;
    }

    public T deleteMin() {
        if (isEmpty()) {
            throw new IllegalStateException("Empty Heap!");
        }
        T min = root.key;
        root = mergePairs(root.leftChild);
        size--;
        return min;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void clear() {
        root = null;
        size = 0;
    }

    private Node merge(Node first, Node second) {
        if (first == null) {
            return second;
        }
        if (second == null) {
            return first;
        }
        if (first.key.compareTo(second.key) <= 0) {
            second.nextSibling = first.leftChild;
            first.leftChild = second;
            return first;
        } else {
            first.nextSibling = second.leftChild;
            second.leftChild = first;
            return second;
        }
    }

    private Node mergePairs(Node start) {
        if (start == null || start.nextSibling == null) {
            return start;
        }
        Queue<Node> queue = new LinkedList<>();
        while (start != null) {
            Node first = start;
            Node second = start.nextSibling;
            start = start.nextSibling.nextSibling;
            first.nextSibling = null;
            second.nextSibling = null;
            queue.add(merge(first, second));
        }
        Node result = queue.poll();
        while (!queue.isEmpty()) {
            result = merge(result, queue.poll());
        }
        return result;
    }
}
