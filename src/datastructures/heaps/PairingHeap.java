package datastructures.heaps;

import datastructures.arrays.IArray;

import java.util.LinkedList;
import java.util.Queue;

public final class PairingHeap<T extends Comparable<T>> implements Heap<T> {

    private Node root;
    private int size;
    private final Node leafNode;

    private class Node { //use just as a struct
        T key;
        Node leftChild;
        Node nextSibling;

        Node(T key) {
            this.key = key;
            this.leftChild = leafNode;
            this.nextSibling = leafNode;
        }
    }

    public PairingHeap() {
        leafNode = new Node(null);
        this.root = leafNode;
        this.size = 0;
    }

    public PairingHeap(IArray<T> array) {
        leafNode = new Node(null);
        this.root = leafNode;
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
        root = leafNode;
        size = 0;
    }

    private Node merge(Node first, Node second) {
        if (first == leafNode) {
            return second;
        }
        if (second == leafNode) {
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
        if (start == leafNode || start.nextSibling == leafNode) {
            return start;
        }
        Queue<Node> queue = new LinkedList<>();
        while (start != leafNode) {
            Node first = start;
            Node second = start.nextSibling;
            start = start.nextSibling.nextSibling;
            first.nextSibling = leafNode;
            second.nextSibling = leafNode;
            queue.add(merge(first, second));
        }
        Node result = queue.poll();
        while (!queue.isEmpty()) {
            result = merge(result, queue.poll());
        }
        return result;
    }

    @Override
    public void print() {
        if (isEmpty()) {
            System.out.println("Empty Heap!");
        } else {
            printHeap(root, 0);
        }
    }

    @Override
    public void accept(HeapAlgorithm<T> algorithm) {
        algorithm.implement(this);
    }

    private void printHeap(Node node, int level) {
        if (node == leafNode) {
            return;
        }

        printHeap(node.leftChild, level + 1);

        for (int i = 0; i < level; i++) {
            System.out.print("    ");
        }

        System.out.println(node.key);

        printHeap(node.nextSibling, level);
    }
}
