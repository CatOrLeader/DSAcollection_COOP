package algorithms.sortings;

import java.util.ArrayList;

public class GnomeSort<T extends Comparable<T>> implements ISort<T> {
    public void sort(ArrayList<T> array) {
        if (array == null || array.size() == 0) {
            System.out.println("Array is null");
            return;
        }

        int n = array.size();
        int index = 0;

        while (index < n) {
            if (index == 0 || array.get(index).compareTo(array.get(index - 1)) >= 0) {
                index++;
            } else {
                swap(array, index, index - 1);
                index--;
            }
        }
    }

    private void swap(ArrayList<T> array, int i, int j) {
        T temp = array.get(i);
        array.set(i, array.get(j));
        array.set(j, temp);
    }
}