package algorithms.sortings;

import java.util.ArrayList;

public class CombSort<T extends Comparable<T>> implements ISort<T>  {

    public void sort(ArrayList<T> array) {
        if (array == null || array.size() == 0) {
            System.out.println("Array is null");
            return;
        }

        int n = array.size();
        int gap = n;
        boolean swapped = true;

        while (gap > 1 || swapped) {
            gap = nextGap(gap);
            swapped = false;

            for (int i = 0; i < n - gap; i++) {
                if (array.get(i).compareTo(array.get(i + gap)) > 0) {
                    swap(array, i, i + gap);
                    swapped = true;
                }
            }
        }
    }

    private int nextGap(int gap) {
        gap = (gap * 10) / 13;
        return Math.max(gap, 1);
    }

    private void swap(ArrayList<T> array, int i, int j) {
        T temp = array.get(i);
        array.set(i, array.get(j));
        array.set(j, temp);
    }
}
