package algorithms.sortings;

import java.util.Scanner;

public class CountingSort {

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

    public void printArray() {
        System.out.print("Your array:");

        for (int j : array) {
            System.out.print(" " + j);
        }
    }

    public void inputArray(int[] array) {
        this.array = new int[array.length];
        for (int i = 0; i < array.length; i++){
            this.array[i] = array[i];
        }
    }

    public void sort() {
        if (array == null){
            throw new NullPointerException("The array was null!");
        }

        if (array.length == 0) {
            System.out.println("Array is empty");
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
