import algorithms.sortings.*;
import datastructures.arrays.IArray;
import datastructures.arrays.IntArray;
import datastructures.arrays.structures.IntArrayStructure;
import statistic.ArraySortCompare;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<IArray<Integer>> integerArrays = new ArrayList<>();

        System.out.println();
        System.out.println("=================================");
        System.out.println("RANDOM ARRAYS");
        System.out.println("=================================");
        System.out.println();

        IntArrayStructure arrayStructure = new IntArrayStructure();
        arrayStructure.generateRandom(20000, -100, 100);

        for (int i = 0; i < 11; i++) {
            integerArrays.add(
                    new IntArray(
                            arrayStructure.currentArray()
                    )
            );
        }

        ArraySortCompare<Integer> intArrayCompare = new ArraySortCompare<>(integerArrays);

        intArrayCompare.compareAndReport();

        System.out.println();
        System.out.println("=================================");
        System.out.println("ARRAYS WITH REPETITIONS");
        System.out.println("=================================");
        System.out.println();

        arrayStructure = new IntArrayStructure();
        arrayStructure.generateWithRepetitions(20000, -100, 100);

        for (int i = 0; i < 11; i++) {
            integerArrays.add(
                    new IntArray(
                            arrayStructure.currentArray()
                    )
            );
        }

        intArrayCompare = new ArraySortCompare<>(integerArrays);

        intArrayCompare.compareAndReport();
        System.out.println();
        System.out.println("=================================");
        System.out.println("ARRAYS WITHOUT REPETITIONS");
        System.out.println("=================================");
        System.out.println();

        arrayStructure = new IntArrayStructure();
        arrayStructure.generateWithoutRepetitions(20000, -1000000000, 1000000000);

        for (int i = 0; i < 11; i++) {
            integerArrays.add(
                    new IntArray(
                            arrayStructure.currentArray()
                    )
            );
        }

        intArrayCompare = new ArraySortCompare<>(integerArrays);

        intArrayCompare.compareAndReport();

        System.out.println();
        System.out.println("=================================");
        System.out.println("SORTED ARRAYS REPETITIONS");
        System.out.println("=================================");
        System.out.println();

        arrayStructure = new IntArrayStructure();
        arrayStructure.generateSorted(20000, -100, 100);

        for (int i = 0; i < 11; i++) {
            integerArrays.add(
                    new IntArray(
                            arrayStructure.currentArray()
                    )
            );
        }

        intArrayCompare = new ArraySortCompare<>(integerArrays);

        intArrayCompare.compareAndReport();

        System.out.println();
        System.out.println("=================================");
        System.out.println("REVERSED ARRAYS REPETITIONS");
        System.out.println("=================================");
        System.out.println();

        arrayStructure = new IntArrayStructure();
        arrayStructure.generateReversed(20000, -100, 100);

        for (int i = 0; i < 11; i++) {
            integerArrays.add(
                    new IntArray(
                            arrayStructure.currentArray()
                    )
            );
        }

        intArrayCompare = new ArraySortCompare<>(integerArrays);

        intArrayCompare.compareAndReport();
    }
}
