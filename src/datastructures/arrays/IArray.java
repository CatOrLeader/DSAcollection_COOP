package datastructures.arrays;

import algorithms.sortings.ISort;

public interface IArray<T> {
    void shuffle();
    void sort(ISort sorting);
    void print();
    void clean();
    T valueByIndex(int index);
    void insertToIndex(int index, T element);
    void inputFromConsole();
}
