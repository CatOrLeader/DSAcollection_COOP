package algorithms.sortings;

import java.util.Scanner;

public class ShellSort implements ISort {

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

        for (int gap = n / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i++) {
                int temp = array[i];
                int j;
                for (j = i; j >= gap && array[j - gap] > temp; j -= gap) {
                    array[j] = array[j - gap];
                }
                array[j] = temp;
            }
        }
    }
}
