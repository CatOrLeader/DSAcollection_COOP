package datastructures.heaps;

public interface Heap<T extends Comparable<T>> {

    void insert(T element);

    T findMin();

    T deleteMin();

    boolean isEmpty();

    int size();

    void clear();
}

