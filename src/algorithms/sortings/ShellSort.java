package algorithms.sortings;

import java.util.ArrayList;
import java.util.Scanner;

public class ShellSort<T extends Comparable<T>> implements ISort<T> {
    public void sort(ArrayList<T> array) {
        if (array == null){
            throw new NullPointerException("The array was null!");
        }

        if (array.size() == 0) {
            System.out.println("Array is empty");
            return;
        }

        shellSort(array);
    }

    @Override
    public void printSortName() {
        System.out.println("Shell sorting");
    }

    private void shellSort(ArrayList<T> array) {
        int n = array.size();

        // Start with a large gap and reduce it in each iteration
        for (int gap = n / 2; gap > 0; gap /= 2) {
            // Perform insertion sort with the current gap
            for (int i = gap; i < n; i++) {
                T temp = array.get(i);
                int j = i;

                // Shift elements that are greater than temp to the right
                while (j >= gap && array.get(j - gap).compareTo(temp) > 0) {
                    array.set(j, array.get(j - gap));
                    j -= gap;
                }

                // Place temp at its correct position
                array.set(j, temp);
            }
        }
    }
}
