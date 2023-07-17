package algorithms.sortings;

import java.util.ArrayList;

public class CycleSort<T extends Comparable<T>> implements ISort<T> {
    public void sort(ArrayList<T> array) {
        if (array == null || array.size() == 0) {
            System.out.println("Array is null");
            return;
        }

        int n = array.size();

        for (int cycleStart = 0; cycleStart < n - 1; cycleStart++) {
            T item = array.get(cycleStart);
            int pos = cycleStart;

            // Find the correct position for the current item
            for (int i = cycleStart + 1; i < n; i++) {
                if (array.get(i).compareTo(item) < 0) {
                    pos++;
                }
            }

            // If the item is already in the correct position, continue to the next cycle
            if (pos == cycleStart) {
                continue;
            }

            // Skip duplicates
            while (item.compareTo(array.get(pos)) == 0) {
                pos++;
            }

            // Swap the current item with the item at the correct position
            if (pos != cycleStart) {
                swap(array, cycleStart, pos);
            }

            // Perform subsequent swaps to place all the items in their correct positions
            while (pos != cycleStart) {
                pos = cycleStart;

                // Find the correct position for the current item
                for (int i = cycleStart + 1; i < n; i++) {
                    if (array.get(i).compareTo(item) < 0) {
                        pos++;
                    }
                }

                // Skip duplicates
                while (item.compareTo(array.get(pos)) == 0) {
                    pos++;
                }

                // Swap the current item with the item at the correct position
                if (item.compareTo(array.get(cycleStart)) != 0) {
                    swap(array, cycleStart, pos);
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