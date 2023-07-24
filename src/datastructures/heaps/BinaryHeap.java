package datastructures.heaps;

import datastructures.arrays.IArray;

import java.util.ArrayList;
import java.util.List;

public class BinaryHeap<T extends Comparable<T>> implements Heap<T> {

    private List<T> heap;

    public BinaryHeap() {
        heap = new ArrayList<>();
    }
    public BinaryHeap(IArray<T> array) {
        heap = new ArrayList<>();
        for (T i: array) {
            insert(i);
        }
    }

    public void insert(T element) {
        heap.add(element);
        heapifyUp(heap.size() - 1);
    }

    public T findMin() {
        if (isEmpty()) {
            throw new IllegalStateException("Empty Heap!");
        }
        return heap.get(0);
    }

    public T deleteMin() {
        if (isEmpty()) {
            throw new IllegalStateException("Empty Heap!");
        }
        T min = heap.get(0);
        T lastElement = heap.remove(heap.size() - 1);
        if (!isEmpty()) {
            heap.set(0, lastElement);
            heapifyDown(0);
        }
        return min;
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

    public int size() {
        return heap.size();
    }

    public void clear() {
        heap.clear();
    }

    private void heapifyUp(int index) {
        int parent = (index - 1) / 2;
        while (index > 0 && heap.get(index).compareTo(heap.get(parent)) < 0) {
            swap(index, parent);
            index = parent;
            parent = (index - 1) / 2;
        }
    }

    private void heapifyDown(int index) {
        int leftChild, rightChild;
        leftChild = 2 * index + 1;
        rightChild = 2 * index + 2;
        int smallest = index;
        if (leftChild < heap.size() && heap.get(leftChild).compareTo(heap.get(smallest)) < 0) {
            smallest = leftChild;
        }
        if (rightChild < heap.size() && heap.get(rightChild).compareTo(heap.get(smallest)) < 0) {
            smallest = rightChild;
        }
        if (smallest != index) {
            swap(index, smallest);
            heapifyDown(smallest);
        }
    }

    private void swap(int i, int j) {
        T temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    @Override
    public void print() {
        for (T element : heap) {
            printElement(element);
        }
        System.out.println();
    }

    @Override
    public void accept(HeapVisitor<T> visitor) {
        visitor.visit(this);
    }

    private void printElement(T element) {
        System.out.print(element + " ");
    }
}
