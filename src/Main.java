import algorithms.sortings.CountingSort;

public class Main {
    public static void main(String[] args) {
        CountingSort countingSort = new CountingSort();
        countingSort.inputArray();
        countingSort.sort();
        countingSort.printArray();
    }
}
