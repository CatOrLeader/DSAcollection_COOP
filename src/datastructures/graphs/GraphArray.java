package datastructures.graphs;

import datastructures.EmptyObject;
import datastructures.arrays.IArray;
import datastructures.arrays.IntArray;

import java.util.ArrayList;

public final class GraphArray<N, E extends Comparable<E>> implements GraphADT<N, E> {
    // Constants
    private static final String incorrectIndex = "Incorrect index";
    private static final String srcNodeAbsent = "Source node is absent in the graph";
    private static final String destNodeAbsent = "Destination node is absent in the graph";
    private static final String noSuchEdge = "No such edge";
    private final E incorrectIndexObj = (E) new EmptyObject();

    private final ArrayList<N> nodes;
    private final ArrayList<Edge<N, E>> edges;

    public GraphArray() {
        nodes = new ArrayList<>();
        edges = new ArrayList<>();
    }


    public GraphArray(IArray<Integer> nodes, ArrayList<Edge<N, E>> edges) {
        this.nodes = new ArrayList<>();
        for (Integer integer : nodes) {
            this.nodes.add((N) integer);
        }
        this.edges = edges;
    }

    @Override
    public ArrayList<N> nodes() {
        return nodes;
    }

    @Override
    public ArrayList<N> adjacentNodes(N node) {
        ArrayList<N> adjacentNodes = new ArrayList<>();

        for (int i = 0; i < edges.size(); i++) {
            Edge<N, E> current = edges.get(i);

            if (current.source().equals(node)) {
                N probableAdj = current.destination();
                if (!adjacentNodes.contains(probableAdj)) {
                    adjacentNodes.add(probableAdj);
                }
            }
        }

        return adjacentNodes;
    }

    @Override
    public void addNode(N node) {
        nodes.add(node);
    }

    @Override
    public void addNodes(ArrayList<N> nodes) {
        this.nodes.addAll(nodes);
    }

    @Override
    public int nodeCount() {
        return nodes.size();
    }

    @Override
    public int edgeCount() {
        return edges.size();
    }

    @Override
    public void printData(int i) {
        if (i > nodes.size()) {
            System.out.println(incorrectIndex);
        }

        System.out.println(nodes.get(i));
    }

    @Override
    public void setValue(int i, N data) {
        nodes.set(i, data);
    }

    @Override
    public void addEdge(int v, int w, E weight) {
        if (v > nodes.size()) {
            System.out.println(srcNodeAbsent);
        }

        if (w > nodes.size()) {
            System.out.println(destNodeAbsent);
        }

        N src = nodes.get(v);
        N dest = nodes.get(w);
        Edge<N, E> edge = new Edge<>(src, dest, weight);
        edges.add(edge);
    }

    @Override
    public E weight(int v, int w) {
        N srcNeeded;
        if (v > nodes.size()) {
            System.out.println(srcNodeAbsent);
            return incorrectIndexObj;
        }
        srcNeeded = nodes.get(v);

        N destNeeded;
        if (w > nodes.size()) {
            System.out.println(destNodeAbsent);
            return incorrectIndexObj;
        }
        destNeeded = nodes.get(w);

        N src;
        N dest;

        for (Edge<N, E> value : edges) {
            int checkers = 0;
            Edge<N, E> edge = value;

            src = edge.source();
            dest = edge.destination();

            if (srcNeeded.equals(src)) {
                checkers++;
            }

            if (destNeeded.equals(dest)) {
                checkers++;
            }

            if (checkers == 2) {
                E weight = edge.weight();
                return weight;
            }
        }

        System.out.println(noSuchEdge);
        return incorrectIndexObj;
    }

    @Override
    public void removeEdge(int v, int w) {
        N srcNeeded;
        if (v > nodes.size()) {
            System.out.println(srcNodeAbsent);
            return;
        }
        srcNeeded = nodes.get(v);

        N destNeeded;
        if (w > nodes.size()) {
            System.out.println(destNodeAbsent);
            return;
        }
        destNeeded = nodes.get(w);

        N src;
        N dest;
        for (Edge<N, E> edge : edges) {
            src = edge.source();
            dest = edge.destination();
            int checkers = 0;

            if (srcNeeded.equals(src)) {
                checkers++;
            }

            if (destNeeded.equals(dest)) {
                checkers++;
            }

            if (checkers == 2) {
                edges.remove(edge);
                return;
            }
        }

        System.out.println(noSuchEdge);
    }

    @Override
    public boolean hasEdge(int v, int w) {
        N srcNeeded;
        if (v > nodes.size()) {
            System.out.println(srcNodeAbsent);
            return false;
        }
        srcNeeded = nodes.get(v);

        N destNeeded;
        if (w > nodes.size()) {
            System.out.println(destNodeAbsent);
            return false;
        }
        destNeeded = nodes.get(w);

        N src;
        N dest;
        for (Edge<N, E> edge : edges) {
            src = edge.source();
            dest = edge.destination();
            int checkers = 0;

            if (srcNeeded.equals(src)) {
                checkers++;
            }

            if (destNeeded.equals(dest)) {
                checkers++;
            }

            if (checkers == 2) {
                edges.remove(edge);
                return true;
            }
        }

        return false;
    }

    @Override
    public void accept(GraphAlgorithm<N, E> algorithm) {
        algorithm.implement(this);
    }
}
