package datastructures.heaps;

public interface HeapVisitor<T extends Comparable<T>> {
    void visit(Heap<T> heap);
}
