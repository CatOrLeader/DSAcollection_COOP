package algorithms.sortings;

import java.util.Scanner;

public class QuickSort {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        long a, c, m, seed;
        String[] input = in.nextLine().split(" ");
        a = Long.parseLong(input[0]);
        c = Long.parseLong(input[1]);
        m = Long.parseLong(input[2]);
        seed = Long.parseLong(input[3]);
        int n = in.nextInt();
        double[] y = new double[n];
        for (int i = 0; i < n; i++) {
            seed = (a * seed + c) % m;
            y[i] = Math.abs(2 * (double) seed / m - 1);
        }
        double[] cpY = y.clone();
        quickSort(y);
        for (int i = 0; i < n; ++i) {
            if (cpY[i] == y[n / 2 - 1]) {
                System.out.println(i + 1);
                break;
            }
        }
    }

    public static void quickSort(double[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    private static void quickSort(double[] arr, int left, int right) {
        if (left < right) {
            int pivotIndex = partition(arr, left, right);
            quickSort(arr, left, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, right);
        }
    }

    private static int partition(double[] arr, int left, int right) {
        double pivotValue = arr[right];
        int i = left - 1;

        for (int j = left; j < right; j++) {
            if (arr[j] < pivotValue) {
                i++;
                swap(arr, i, j);
            }
        }

        swap(arr, i + 1, right);
        return i + 1;
    }

    private static void swap(double[] arr, int i, int j) {
        double temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
