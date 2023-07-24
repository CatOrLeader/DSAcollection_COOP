package datastructures.queues;

import java.util.Arrays;

public class LinearQueue<E> implements QueueADT<E> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elements;
    private int front;
    private int rear;
    private int size;
    private final E tail;

    public LinearQueue() {
        this.elements = new Object[DEFAULT_CAPACITY];
        this.front = 0;
        this.rear = -1;
        this.size = 0;
        tail = null;
    }

    public LinearQueue(int initialCapacity) {
        if (initialCapacity <= 0) {
            throw new IllegalArgumentException("Initial capacity must be positive.");
        }
        this.elements = new Object[initialCapacity];
        this.front = 0;
        this.rear = -1;
        this.size = 0;
        tail = null;
    }

    @Override
    public boolean enqueue(E e) {
        if (size == elements.length) {
            ensureCapacity();
        }
        rear = (rear + 1) % elements.length;
        elements[rear] = e;
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
            throw new IllegalStateException("Queue is empty. Cannot dequeue.");
        }
        E removedElement = (E) elements[front];
        elements[front] = tail;
        front = (front + 1) % elements.length;
        size--;
        return removedElement;
    }

    @Override
    public E poll() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("Queue is empty");
        }
        return dequeue();
    }

    @Override
    public E front() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("Queue is empty");
        }
        return (E) elements[front];
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("Queue is empty");
        }
        return front();
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
        if (rear < front) {
            for (int i = 0; i < front; i++) {
                elements[i + elements.length] = elements[i];
                elements[i] = tail;
            }
            rear += elements.length;
        }
    }
}

