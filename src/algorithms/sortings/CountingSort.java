package algorithms.sortings;

import java.util.Arrays;
import java.util.Scanner;

public class CountingSort {

    private int[] array;

    public void inputArray() {
        Scanner in = new Scanner(System.in);
        System.out.print("Input an array split by spaces: ");
        String[] arr = in.nextLine().split(" ");
        array = new int[arr.length];
        for (int i = 0; i < arr.length; ++i) {
            array[i] = Integer.parseInt(arr[i]);
        }
    }

    public void printArray() {
        System.out.print("Your array:");
        Arrays.stream(array).forEach(x-> System.out.print(" " + x));
    }

    public void sort() {
        if (array == null || array.length == 0) {
            System.out.println("Array is null");
            return;
        }
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        int[] count = new int[max + 1];
        for (int j : array) {
            count[j]++;
        }
        for (int i = 1; i <= max; i++) {
            count[i] += count[i - 1];
        }
        int[] sortedArray = new int[array.length];
        for (int i = array.length - 1; i >= 0; i--) {
            sortedArray[count[array[i]] - 1] = array[i];
            count[array[i]]--;
        }
        System.arraycopy(sortedArray, 0, array, 0, array.length);
    }
}

