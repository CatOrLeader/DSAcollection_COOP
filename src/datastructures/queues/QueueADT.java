package datastructures.queues;

import java.util.Collection;

public interface QueueADT<E> {
    boolean enqueue(E e);
    boolean offer(E e);
    E dequeue();
    E poll();
    E front();
    E peek();
    void accept(QueueAlgorithm<E> algorithm);
}

