package algorithms.sortings;

import java.util.Scanner;

public class CountingSort {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = Integer.parseInt(in.nextLine());
        int[][] a = new int[n][3];
        int maxif = -1;
        int maxis = -1;
        for (int i = 0; i < n; ++i) {
            String[] s = in.nextLine().split(" ");
            a[i][0] = Integer.parseInt(s[0]);
            a[i][1] = Integer.parseInt(s[1]);
            a[i][2] = i + 1;
            maxif = Math.max(maxif, a[i][0]);
            maxis = Math.max(maxis, a[i][1]);
        }
        a = countingSort(a, maxis, n, 1);
        a = countingSort(a, maxif, n, 0);
        System.out.print(a[0][2]);
        for (int i = 1; i < n; ++i) {
            System.out.print(" " + a[i][2]);
        }
        System.out.println();
    }

    public static int[][] countingSort(int[][] a, int m, int n, int ind) {
        int[] c = new int[m + 1];
        for (int i = 0; i <= m; ++i) {
            c[i] = 0;
        }
        for (int i = 0; i < n; ++i) {
            c[a[i][ind]]++;
        }
        for (int i = 1; i < c.length; ++i) {
            c[i] += c[i - 1];
        }
        int[][] b = new int[n][3];
        for (int i = n - 1; i >= 0; --i) {
            b[c[a[i][ind]] - 1] = a[i];
            c[a[i][ind]]--;
        }
        reverse(b);
        return b;
    }

    public static void reverse(int[][] a) {
        for (int i = 0; i < a.length / 2; ++i) {
            int[] t = a[i];
            a[i] = a[a.length - i - 1];
            a[a.length - i - 1] = t;
        }
    }
}
