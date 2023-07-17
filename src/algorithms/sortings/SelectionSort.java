package algorithms.sortings;

import java.util.ArrayList;

public class SelectionSort<T extends Comparable<T>> implements ISort<T> {
    public void sort(ArrayList<T> array) {
        if (array == null || array.size() == 0) {
            System.out.println("Array is null");
            return;
        }

        selectionSort(array);
    }

    private void selectionSort(ArrayList<T> array) {
        int n = array.size();

        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;

            // Find the index of the minimum element in the unsorted part of the array
            for (int j = i + 1; j < n; j++) {
                if (array.get(j).compareTo(array.get(minIndex)) < 0) {
                    minIndex = j;
                }
            }

            // Swap the minimum element with the first unsorted element
            if (minIndex != i) {
                swap(array, i, minIndex);
            }
        }
    }

    private void swap(ArrayList<T> array, int i, int j) {
        T temp = array.get(i);
        array.set(i, array.get(j));
        array.set(j, temp);
    }
}
