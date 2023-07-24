package datastructures.heaps;

import datastructures.arrays.IArray;

import java.util.ArrayList;
import java.util.List;

public class DHeap<T extends Comparable<T>> implements Heap<T> {

    private List<T> heap;
    private int d;

    public DHeap(int d) {
        if (d <= 1) {
            throw new IllegalArgumentException("d must be greater than 1.");
        }
        this.heap = new ArrayList<>();
        this.d = d;
    }

    public DHeap(int d, IArray<T> array) {
        if (d <= 1) {
            throw new IllegalArgumentException("d must be greater than 1.");
        }
        this.heap = new ArrayList<>();
        this.d = d;
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
        int parent = (index - 1) / d;
        while (index > 0 && heap.get(index).compareTo(heap.get(parent)) < 0) {
            swap(index, parent);
            index = parent;
            parent = (index - 1) / d;
        }
    }

    private void heapifyDown(int index) {
        int firstChild = d * index + 1;
        while (firstChild < heap.size()) {
            int minChild = firstChild;
            int lastChild = Math.min(firstChild + d, heap.size());

            for (int i = firstChild + 1; i < lastChild; i++) {
                if (heap.get(i).compareTo(heap.get(minChild)) < 0) {
                    minChild = i;
                }
            }
            if (heap.get(minChild).compareTo(heap.get(index)) < 0) {
                swap(minChild, index);
                index = minChild;
                firstChild = d * index + 1;
            } else {
                break;
            }
        }
    }

    private void swap(int i, int j) {
        T temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    @Override
    public void print() {
        printHeap(0, 0);
    }

    private void printHeap(int index, int level) {
        if (index >= heap.size()) {
            return;
        }

        for (int i = 0; i < level; i++) {
            System.out.print("  ");
        }

        System.out.println(heap.get(index));

        int firstChild = d * index + 1;
        for (int i = 0; i < d; i++) {
            printHeap(firstChild + i, level + 1);
        }
    }
}
