package algorithms.sortings;

import java.util.ArrayList;

public class RadixSort<T extends Comparable<T>> implements ISort<T> {
    public void sort(ArrayList<T> array) {
        if (array == null){
            throw new NullPointerException("The array was null!");
        }

        if (array.size() == 0) {
            System.out.println("Array is empty");
            return;
        }

        radixSort(array);
    }

    private void radixSort(ArrayList<T> array) {
        // Find the maximum value in the array
        T max = getMaxValue(array);

        // Perform counting sort for each digit
        for (int exp = 1; ; exp *= 10) {
            ArrayList<T> sortedArray = countingSort(array, exp);
            if (sortedArray.equals(array)) {
                break;
            }
            array = sortedArray;
        }
    }

    private ArrayList<T> countingSort(ArrayList<T> array, int exp) {
        int n = array.size();

        // Initialize count array
        int[] count = new int[10];
        for (int i = 0; i < 10; i++) {
            count[i] = 0;
        }

        // Store count of occurrences in count array
        for (int i = 0; i < n; i++) {
            T item = array.get(i);
            int digit = getDigit(item, exp);
            count[digit]++;
        }

        // Modify count array to store actual position
        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        // Build the output array
        ArrayList<T> output = new ArrayList<>(n);
        for (int i = n - 1; i >= 0; i--) {
            T item = array.get(i);
            int digit = getDigit(item, exp);
            int index = count[digit] - 1;
            output.set(index, item);
            count[digit]--;
        }

        return output;
    }

    private T getMaxValue(ArrayList<T> array) {
        T max = array.get(0);
        for (T item : array) {
            if (item.compareTo(max) > 0) {
                max = item;
            }
        }
        return max;
    }

    private int getDigit(T item, int exp) {
        int value = item.hashCode();
        return (value / exp) % 10;
    }
}

