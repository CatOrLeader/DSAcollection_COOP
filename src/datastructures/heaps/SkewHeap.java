package datastructures.heaps;

import datastructures.arrays.IArray;

public class SkewHeap<T extends Comparable<T>> implements Heap<T> {

    private Node root;
    private int size;
    private final Node leafNode;

    private class Node { //use just as a struct
        T key;
        Node left;
        Node right;

        Node(T key) {
            this.key = key;
            this.left = leafNode;
            this.right = leafNode;
        }
    }

    public SkewHeap() {
        leafNode = new Node(null);
        this.root = leafNode;
        this.size = 0;
    }

    public SkewHeap(IArray<T> array) {
        leafNode = new Node(null);
        this.root = leafNode;
        this.size = 0;
        for (T i: array) {
            insert(i);
        }
    }

    public void insert(T element) {
        Node newNode = new Node(element);
        root = merge(root, newNode);
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
        root = merge(root.left, root.right);
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

    private Node merge(Node heap1, Node heap2) {
        if (heap1 == leafNode) {
            return heap2;
        }
        if (heap2 == leafNode) {
            return heap1;
        }
        if (heap2.key.compareTo(heap1.key) < 0) {
            Node temp = heap1;
            heap1 = heap2;
            heap2 = temp;
        }
        Node temp = heap1.left;
        heap1.left = heap1.right;
        heap1.right = temp;
        heap1.left = merge(heap2, heap1.left);
        return heap1;
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
        printHeap(node.right, level + 1);

        for (int i = 0; i < level; i++) {
            System.out.print("    ");
        }

        System.out.println(node.key);

        printHeap(node.left, level);
    }
}

