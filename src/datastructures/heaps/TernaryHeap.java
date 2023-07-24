package datastructures.heaps;

import datastructures.arrays.IArray;

import java.util.ArrayList;
import java.util.List;

public class TernaryHeap<T extends Comparable<T>> implements Heap<T> {

    private List<T> heap;

    public TernaryHeap() {
        heap = new ArrayList<>();
    }

    public TernaryHeap(IArray<T> array) {
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
            throw new IllegalStateException("Heap is empty.");
        }
        return heap.get(0);
    }

    public T deleteMin() {
        if (isEmpty()) {
            throw new IllegalStateException("Heap is empty.");
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
        int parent = (index - 1) / 3;
        while (index > 0 && heap.get(index).compareTo(heap.get(parent)) < 0) {
            swap(index, parent);
            index = parent;
            parent = (index - 1) / 3;
        }
    }

    private void heapifyDown(int index) {
        int firstChild = 3 * index + 1;
        int smallest = index;

        for (int i = firstChild; i <= firstChild + 2 && i < heap.size(); i++) {
            if (heap.get(i).compareTo(heap.get(smallest)) < 0) {
                smallest = i;
            }
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
        if (isEmpty()) {
            System.out.println("Heap is empty.");
        } else {
            printHeap(0, 0);
        }
    }

    @Override
    public void accept(HeapAlgorithm<T> algorithm) {
        algorithm.implement(this);
    }

    private void printHeap(int index, int level) {
        if (index >= heap.size()) {
            return;
        }

        printHeap(3 * index + 1, level + 1);
        printIndent(level);
        System.out.println(heap.get(index));

        printHeap(3 * index + 2, level + 1);
        printHeap(3 * index + 3, level + 1);
    }

    private void printIndent(int level) {
        for (int i = 0; i < level; i++) {
            System.out.print("    ");
        }
    }
}
