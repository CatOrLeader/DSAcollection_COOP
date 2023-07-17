import algorithms.sortings.BubbleSort;
import datastructures.arrays.IntArray;

public class Main {
    public static void main(String[] args) {
        IntArray array = new IntArray();
        array.generateRandom(20, 1, 100);
        array.sort(new BubbleSort());
        array.print();
    }
}