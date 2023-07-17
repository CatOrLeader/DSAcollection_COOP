package algorithms.sortings;

import java.util.ArrayList;

public class BubbleSort<T extends Comparable<T>> implements ISort<T> {
    public void sort(ArrayList<T> array) {
        if (array == null || array.size() == 0) {
            System.out.println("Array is null");
            return;
        }
        int n = array.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (array.get(j).compareTo(array.get(j + 1)) > 0) {
                    swap(array, j, j + 1);
                }
            }
        }
    }

    private void swap(ArrayList<T> array, int i, int j) {
        T temp = array.get(i);
        array.set(i, array.get(j));
        array.set(j, temp);
    }
}
