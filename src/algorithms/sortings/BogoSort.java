package algorithms.sortings;

import java.util.ArrayList;
import java.util.Random;

public class BogoSort<T extends Comparable<T>> implements ISort<T> {
    private final Random random;

    public BogoSort() {
        random = new Random();
    }

    public void sort(ArrayList<T> array) {
        if (array == null){
            throw new NullPointerException("The array was null!");
        }

        if (array.size() == 0) {
            System.out.println("Array is empty");
            return;
        }

        while (!isSorted(array)) {
            shuffle(array);
        }
    }

    private boolean isSorted(ArrayList<T> array) {
        int n = array.size();
        for (int i = 1; i < n; i++) {
            if (array.get(i).compareTo(array.get(i - 1)) < 0) {
                return false;
            }
        }
        return true;
    }

    private void shuffle(ArrayList<T> array) {
        int n = array.size();
        for (int i = 0; i < n; i++) {
            int j = random.nextInt(n);
            T temp = array.get(i);
            array.set(i, array.get(j));
            array.set(j, temp);
        }
    }
}