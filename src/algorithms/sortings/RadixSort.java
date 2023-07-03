package algorithms.sortings;

import java.util.Scanner;

public class RadixSort implements ISort {

    private int[] array;

    public void inputArrayFromConsole() {
        Scanner in = new Scanner(System.in);
        System.out.print("Input an array split by spaces: ");
        String[] arr = in.nextLine().split(" ");
        array = new int[arr.length];
        for (int i = 0; i < arr.length; ++i) {
            array[i] = Integer.parseInt(arr[i]);
        }
    }

    @Override
    public void inputArray(int[] array) {
        this.array = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            this.array[i] = array[i];
        }
    }

    public void printArray() {
        System.out.print("Your array:");
        for (int j : array) {
            System.out.print(" " + j);
        }
        System.out.println();
    }

    public void sort() {
        if (array == null || array.length == 0) {
            System.out.println("Array is null");
            return;
        }
        int max = calculateMaxValue();

        // Perform counting sort for every digit
        for (int exp = 1; max / exp > 0; exp *= 10) {
            countingSort(exp);
        }
    }

    private int calculateMaxValue() {
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        return max;
    }

    private void countingSort(int exp) {
        int n = array.length;
        int[] output = new int[n];
        int[] count = new int[10];

        for (int i = 0; i < 10; i++) {
            count[i] = 0;
        }

        for (int j : array) {
            int digit = (j / exp) % 10;
            count[digit]++;
        }

        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        for (int i = n - 1; i >= 0; i--) {
            int digit = (array[i] / exp) % 10;
            output[count[digit] - 1] = array[i];
            count[digit]--;
        }

        for (int i = 0; i < n; i++) {
            array[i] = output[i];
        }
    }
}

