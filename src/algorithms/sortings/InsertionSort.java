package algorithms.sortings;

import java.util.ArrayList;

public class InsertionSort<T extends Comparable<T>> implements ISort<T> {
    public void sort(ArrayList<T> array) {
        if (array == null){
            throw new NullPointerException("The array was null!");
        }

        if (array.size() == 0) {
            System.out.println("Array is empty");
            return;
        }

        int n = array.size();

        for (int i = 1; i < n; i++) {
            T key = array.get(i);
            int j = i - 1;

            // Shift elements greater than the key to the right
            while (j >= 0 && array.get(j).compareTo(key) > 0) {
                array.set(j + 1, array.get(j));
                j--;
            }

            // Place the key in its correct position
            array.set(j + 1, key);
        }
    }

    @Override
    public void printSortName() {
        System.out.println("Insertion sorting");
    }
}

