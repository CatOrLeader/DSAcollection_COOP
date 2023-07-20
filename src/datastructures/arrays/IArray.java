package datastructures.arrays;

import algorithms.sortings.ISort;

public interface IArray<T> extends Iterable<T> {
    IArray<T> shuffle();
    IArray<T> sort(ISort sorting);
    void print();
    void clean();
    T valueByIndex(int index);
    void insertToIndex(int index, T element);
    void inputFromConsole();
    int size();
}
