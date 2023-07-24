package algorithms.sortings;

import java.util.ArrayList;

public class MergeSort<T extends Comparable<T>> implements ISort<T> {
    public void sort(ArrayList<T> array) {
        if (array == null){
            throw new NullPointerException("The array was null!");
        }

        if (array.size() == 0) {
            System.out.println("Array is empty");
            return;
        }

        mergeSort(array, 0, array.size() - 1);
    }

    private void mergeSort(ArrayList<T> array, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;

            // Sort the left and right halves recursively
            mergeSort(array, left, mid);
            mergeSort(array, mid + 1, right);

            // Merge the sorted halves
            merge(array, left, mid, right);
        }
    }

    private void merge(ArrayList<T> array, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        // Create temporary arrays for the left and right halves
        ArrayList<T> leftArray = new ArrayList<>(n1);
        ArrayList<T> rightArray = new ArrayList<>(n2);

        // Copy the elements into the temporary arrays
        for (int i = 0; i < n1; i++) {
            leftArray.add(array.get(left + i));
        }
        for (int j = 0; j < n2; j++) {
            rightArray.add(array.get(mid + 1 + j));
        }

        // Merge the temporary arrays back into the original array
        int i = 0, j = 0, k = left;

        while (i < n1 && j < n2) {
            if (leftArray.get(i).compareTo(rightArray.get(j)) <= 0) {
                array.set(k, leftArray.get(i));
                i++;
            } else {
                array.set(k, rightArray.get(j));
                j++;
            }
            k++;
        }

        // Copy the remaining elements from the left array, if any
        while (i < n1) {
            array.set(k, leftArray.get(i));
            i++;
            k++;
        }

        // Copy the remaining elements from the right array, if any
        while (j < n2) {
            array.set(k, rightArray.get(j));
            j++;
            k++;
        }
    }
}
