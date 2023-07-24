package algorithms.sortings;

import java.util.ArrayList;
import java.util.Scanner;

public class ShakerSort<T extends Comparable<T>> implements ISort<T> {
    public void sort(ArrayList<T> array) {
        if (array == null){
            throw new NullPointerException("The array was null!");
        }

        if (array.size() == 0) {
            System.out.println("Array is empty");
            return;
        }

        shakerSort(array);
    }

    @Override
    public void printSortName() {
        System.out.println("Shaker sorting");
    }

    private void shakerSort(ArrayList<T> array) {
        int left = 0;
        int right = array.size() - 1;

        while (left <= right) {
            // Move the largest element to the rightmost position
            for (int i = left; i < right; i++) {
                if (array.get(i).compareTo(array.get(i + 1)) > 0) {
                    swap(array, i, i + 1);
                }
            }
            right--;

            // Move the smallest element to the leftmost position
            for (int i = right; i > left; i--) {
                if (array.get(i).compareTo(array.get(i - 1)) < 0) {
                    swap(array, i, i - 1);
                }
            }
            left++;
        }
    }

    private void swap(ArrayList<T> array, int i, int j) {
        T temp = array.get(i);
        array.set(i, array.get(j));
        array.set(j, temp);
    }
}
