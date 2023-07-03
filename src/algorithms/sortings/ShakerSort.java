package algorithms.sortings;

import java.util.Scanner;

public class ShakerSort implements ISort {

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
        int left = 0;
        int right = array.length - 1;
        boolean swapped;

        while (left <= right) {
            swapped = false;

            for (int i = left; i < right; i++) {
                if (array[i] > array[i + 1]) {
                    swap(i, i + 1);
                    swapped = true;
                }
            }
            right--;

            if (!swapped) {
                break;
            }

            swapped = false;
            for (int i = right; i > left; i--) {
                if (array[i] < array[i - 1]) {
                    swap(i, i - 1);
                    swapped = true;
                }
            }
            left++;

            if (!swapped) {
                break;
            }
        }
    }

    private void swap(int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
