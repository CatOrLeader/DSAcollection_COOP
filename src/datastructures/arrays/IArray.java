package datastructures.arrays;

import algorithms.sortings.ISort;

public interface IArray {
    void generate();
    void shuffle();
    void sort(ISort sorting);
    void print();
}
