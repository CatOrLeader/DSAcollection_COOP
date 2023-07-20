package datastructures.heaps;

import datastructures.arrays.IArray;

public class SkewHeap<T extends Comparable<T>> implements Heap<T> {

    private Node root;
    private int size;

    private class Node { //use just as a struct
        T key;
        Node left;
        Node right;

        Node(T key) {
            this.key = key;
            this.left = null;
            this.right = null;
        }
    }

    public SkewHeap() {
        this.root = null;
        this.size = 0;
    }

    public SkewHeap(IArray<T> array) {
        this.root = null;
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
        root = null;
        size = 0;
    }

    private Node merge(Node heap1, Node heap2) {
        if (heap1 == null) {
            return heap2;
        }
        if (heap2 == null) {
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
}

