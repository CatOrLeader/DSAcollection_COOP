package algorithms.sortings;

import java.util.Scanner;

public class CombSort implements ISort {

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

        int n = array.length;
        int gap = n;
        boolean swapped = true;

        while (gap > 1 || swapped) {
            gap = nextGap(gap);
            swapped = false;

            for (int i = 0; i < n - gap; i++) {
                if (array[i] > array[i + gap]) {
                    swap(i, i + gap);
                    swapped = true;
                }
            }
        }
    }

    private int nextGap(int gap) {
        gap = (gap * 10) / 13;
        return Math.max(gap, 1);
    }

    private void swap(int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
