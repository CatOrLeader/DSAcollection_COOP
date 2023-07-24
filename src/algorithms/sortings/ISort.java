package algorithms.sortings;

import java.util.ArrayList;

public interface ISort<T extends Comparable<T>> {
    void sort(ArrayList<T> array);
    void printSortName();
}
