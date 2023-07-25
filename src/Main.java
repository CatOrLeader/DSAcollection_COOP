import datastructures.arrays.CharArray;
import datastructures.arrays.IArray;
import datastructures.arrays.IntArray;
import datastructures.arrays.StringArray;
import datastructures.arrays.structures.CharArrayStructure;
import datastructures.arrays.structures.IntArrayStructure;
import datastructures.graphs.BFSAlgorithm;
import datastructures.graphs.DFSAlgorithm;
import datastructures.graphs.Edge;
import datastructures.graphs.GraphArray;
import datastructures.heaps.BinaryHeap;
import datastructures.heaps.HeapTestAlgorithm;
import datastructures.maps.HashMapDH;
import datastructures.maps.HashMapTestAlgorithm;
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

        System.out.println();
        System.out.println("=================================");
        System.out.println("GRAPH BFS AND DFS ALGORITHMS");
        System.out.println("=================================");
        System.out.println();

        ArrayList<Edge<Integer, Integer>> edges = new ArrayList<>();
        edges.add(new Edge<>(0, 1, 1));
        edges.add(new Edge<>(0, 2, 1));
        edges.add(new Edge<>(1, 3, 1));
        edges.add(new Edge<>(2, 4, 1));
        edges.add(new Edge<>(2, 5, 1));

        System.out.println("...zZzZzZzZzZzZz...");
        System.out.println("Generating graph with vertices from 0 to 5 and making some edges");
        System.out.println("...zZzZzZzZzZzZz...");
        System.out.println();

        System.out.println("-->BFS<-- traversal from source vertex");
        new GraphArray<>(
                new IntArray(
                        new IntArrayStructure()
                                .generateWithoutRepetitions(6, 0, 5)
                ),
                edges
        ).accept(
                new BFSAlgorithm<>()
        );

        System.out.println("\n\n-->DFS<-- traversal from source vertex");
        new GraphArray<>(
                new IntArray(
                        new IntArrayStructure()
                                .generateWithoutRepetitions(6, 0, 5)
                ),
                edges
        ).accept(
                new DFSAlgorithm<>()
        );

        System.out.println("\n");
        System.out.println("=================================");
        System.out.println("HEAP");
        System.out.println("=================================");

        new BinaryHeap<>(
                new IntArray(
                        new IntArrayStructure()
                                .generateWithoutRepetitions(5, 1, 5)
                )
        ).accept(
                new HeapTestAlgorithm<>()
        );

        System.out.println();
        System.out.println("=================================");
        System.out.println("MAP");
        System.out.println("=================================");
        System.out.println();

        new HashMapDH<String, Character>(
                new StringArray().inputFromConsoleChain(),
                new CharArray(
                        new CharArrayStructure()
                                .generateWithRepetitions(3, 'A', 'A')
                )
        ).accept(
                new HashMapTestAlgorithm<>()
        );
    }
}
