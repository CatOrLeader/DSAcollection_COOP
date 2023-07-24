package datastructures.graphs;

import java.util.ArrayList;

public class GraphArray<N, E extends Comparable<E>> implements GraphADT<N, E> {
    // Constants
    private static final String incorrectIndex = "Incorrect index";
    private static final String srcNodeAbsent = "Source node is absent in the graph";
    private static final String destNodeAbsent = "Destination node is absent in the graph";
    private static final String noSuchEdge = "No such edge";
    private final E incorrectIndexObj = (E) new Object();

    private final ArrayList<N> nodes;
    private final ArrayList<Edge> edges;

    public GraphArray() {
        nodes = new ArrayList<>();
        edges = new ArrayList<>();
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
        Edge edge = new Edge(src, dest, weight);
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

        for (Edge value : edges) {
            int checkers = 0;
            Edge edge = value;

            src = edge.src;
            dest = edge.dest;

            if (srcNeeded.equals(src)) {
                checkers++;
            }

            if (destNeeded.equals(dest)) {
                checkers++;
            }

            if (checkers == 2) {
                E weight = edge.weight;
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
        for (Edge edge : edges) {
            src = edge.src;
            dest = edge.dest;
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
        for (Edge edge : edges) {
            src = edge.src;
            dest = edge.dest;
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
    public void accept(GraphVisitor<N, E> visitor) {
        visitor.visit(this);
    }

    class Edge {
        private final N src;
        private final N dest;
        private final E weight;

        public Edge(N src, N dest, E weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }
    }
}
