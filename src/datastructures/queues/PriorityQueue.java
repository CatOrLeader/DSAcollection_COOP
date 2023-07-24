package datastructures.queues;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class PriorityQueue<E extends Comparable<E>> implements QueueADT<E> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elements;
    private int size;
    private final E tail;

    public PriorityQueue() {
        this.elements = new Object[DEFAULT_CAPACITY];
        this.size = 0;
        tail = null;
    }

    public PriorityQueue(int initialCapacity) {
        if (initialCapacity <= 0) {
            throw new IllegalArgumentException("Initial capacity must be positive.");
        }
        this.elements = new Object[initialCapacity];
        this.size = 0;
        tail = null;
    }

    @Override
    public boolean enqueue(E e) {
        if (size == elements.length) {
            ensureCapacity();
        }
        elements[size] = e;
        heapifyUp(size);
        size++;
        return true;
    }

    @Override
    public boolean offer(E e) {
        return enqueue(e);
    }

    @Override
    public E dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        E removedElement = (E) elements[0];
        elements[0] = elements[size - 1];
        elements[size - 1] = null;
        size--;
        heapifyDown(0);
        return removedElement;
    }

    @Override
    public E poll() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        return dequeue();
    }

    @Override
    public E front() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        return (E) elements[0];
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        return front();
    }

    @Override
    public void accept(QueueAlgorithm<E> algorithm) {
        algorithm.implement(this);
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    private void ensureCapacity() {
        int newCapacity = elements.length * 2;
        elements = Arrays.copyOf(elements, newCapacity);
    }

    private void heapifyUp(int index) {
        while (index > 0) {
            int parentIndex = (index - 1) / 2;
            if (((E) elements[index]).compareTo((E) elements[parentIndex]) <= 0) {
                break;
            }
            swap(index, parentIndex);
            index = parentIndex;
        }
    }

    private void heapifyDown(int index) {
        while (index < size) {
            int leftChild = 2 * index + 1;
            int rightChild = 2 * index + 2;
            int largest = index;

            if (leftChild < size && ((E) elements[leftChild]).compareTo((E) elements[largest]) > 0) {
                largest = leftChild;
            }

            if (rightChild < size && ((E) elements[rightChild]).compareTo((E) elements[largest]) > 0) {
                largest = rightChild;
            }

            if (largest == index) {
                break;
            }

            swap(index, largest);
            index = largest;
        }
    }

    private void swap(int i, int j) {
        Object temp = elements[i];
        elements[i] = elements[j];
        elements[j] = temp;
    }
}

