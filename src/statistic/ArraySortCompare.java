package statistic;

import algorithms.sortings.*;
import datastructures.arrays.IArray;

import java.util.ArrayList;

public class ArraySortCompare<T extends Comparable<T>> {
    private ArrayList<IArray<T>> arrays = new ArrayList<>();

    public ArraySortCompare(ArrayList<IArray<T>> arrays) {
        this.arrays = arrays;
    }

    public void addArray(IArray<T> array) {
        arrays.add(array);
    }

    public void compareAndReport() {
        ArrayList<ISort<T>> sortings = new ArrayList<>();

        sortings.add(new BubbleSort<>());
        sortings.add(new CombSort<>());
        sortings.add(new GnomeSort<>());
        sortings.add(new HeapSort<>());
        sortings.add(new InsertionSort<>());
        sortings.add(new MergeSort<>());
        sortings.add(new SelectionSort<>());
        sortings.add(new ShakerSort<>());
        sortings.add(new ShellSort<>());

        int sortIdx = 0;

        for (IArray<T> array : arrays) {
            if (sortIdx >= sortings.size()) {
                sortIdx -= sortings.size();
            }

            ISort<T> currSorting = sortings.get(sortIdx);
            currSorting.printSortName();
            long startTime = System.currentTimeMillis();
            array.sort(sortings.get(sortIdx));
            long endTime = System.currentTimeMillis();
            System.out.println("Time taken: " + (endTime - startTime) + " milliseconds");
            sortIdx++;
            System.out.println("-------------------------------");
        }
    }

    public void clear() {
        arrays.clear();
    }
}
