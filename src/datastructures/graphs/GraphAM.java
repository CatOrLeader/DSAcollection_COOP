package datastructures.graphs;

import datastructures.EmptyObject;

import java.util.ArrayList;

/**
 * The class which represents the simple graph based on adjacency matrix
 *
 * @param <N> the type of the node key in the graph
 * @param <E> the type of value stored in the particular node
 */
public class GraphAM<N, E extends Comparable<E>> implements GraphADT<N, E> {
    // Constants
    private static final String incorrectIndex = "Incorrect index";
    private static final String srcNodeAbsent = "Source node is absent in the graph";
    private static final String destNodeAbsent = "Destination node is absent in the graph";
    private static final String noSuchEdge = "No such edge";
    private final E incorrectIndexObj = (E) new EmptyObject();


    private final ArrayList<N> nodes;
    private final ArrayList<MatrixCell>[] adjacencyMatrix;

    public GraphAM(int size) {
        nodes = new ArrayList<>();
        adjacencyMatrix = new ArrayList[size];
        fillMatrix(size);
    }

    private void fillMatrix(int size) {
        for (int i = 0; i < size; i++) {
            adjacencyMatrix[i] = new ArrayList<>();
            for (int j = 0; j < size; j++) {
                MatrixCell empty = new MatrixCell().emptyCell();
                adjacencyMatrix[i].add(empty);
            }
        }
    }

    @Override
    public ArrayList<N> nodes() {
        return nodes;
    }

    @Override
    public ArrayList<N> adjacentNodes(N node) {
        ArrayList<N> adjacentNodes = new ArrayList<>();

        int currentIndex = nodes.indexOf(node);
        ArrayList<MatrixCell> row = adjacencyMatrix[currentIndex];

        for (int i = 0; i < row.size(); i++) {
            MatrixCell current = row.get(i);

            if (!current.isEmptyCell()) {
                N nodeTBA = nodes.get(i);
                adjacentNodes.add(nodeTBA);
            }
        }

        return adjacentNodes;
    }

    @Override
    public void addNode(N node) {
        System.out.println("Graph based on adjacency matrix is immutable in terms of nodes");
    }

    @Override
    public void addNodes(ArrayList<N> nodes) {
        System.out.println("Graph based on adjacency matrix is immutable in terms of nodes");
    }

    @Override
    public int nodeCount() {
        return nodes.size();
    }

    @Override
    public int edgeCount() {
        int count = 0;

        for (ArrayList<MatrixCell> row : adjacencyMatrix) {
            for (MatrixCell cell : row) {
                if (cell.isEmptyCell()) {
                    count++;
                }
            }
        }

        int size = nodeCount();
        int mostPossible = size * size;

        return mostPossible - count;
    }

    @Override
    public void printData(int i) {
        if (i > nodes.size()) {
            System.out.println(incorrectIndex);
            return;
        }

        System.out.println(nodes.get(i));
    }

    @Override
    public void setValue(int i, N data) {
        if (i > nodes.size()) {
            System.out.println(incorrectIndex);
            return;
        }

        nodes.set(i, data);
    }

    @Override
    public void addEdge(int v, int w, E weight) {
        if (v > nodes.size()) {
            System.out.println(incorrectIndex);
            return;
        }

        if (w > nodes.size()) {
            System.out.println(incorrectIndex);
            return;
        }

        N dest = nodes.get(w);
        int destIndex = nodes.indexOf(dest);
        MatrixCell matrixCell = new MatrixCell(dest, weight);

        adjacencyMatrix[v].set(destIndex, matrixCell);
    }

    @Override
    public E weight(int v, int w) {
        if (!hasEdge(v, w)) {
            return incorrectIndexObj;
        }

        ArrayList<MatrixCell> srcNodeRow = adjacencyMatrix[v];
        MatrixCell cell = srcNodeRow.get(w);
        return cell.weight;
    }

    @Override
    public void removeEdge(int v, int w) {
        if (!hasEdge(v, w)) {
            System.out.println(incorrectIndex);
            return;
        }

        ArrayList<MatrixCell> srcNodeRow = adjacencyMatrix[v];
        MatrixCell cell = srcNodeRow.get(w);
        boolean flag = srcNodeRow.remove(cell);
        if (!flag) {
            System.out.println(noSuchEdge);
        }
        System.out.println("All done! Removed");
    }

    @Override
    public boolean hasEdge(int v, int w) {
        if (v > nodes.size()) {
            System.out.println(srcNodeAbsent);
            return false;
        }

        if (w > nodes.size()) {
            System.out.println(destNodeAbsent);
            return false;
        }

        ArrayList<MatrixCell> srcNodeRow = adjacencyMatrix[v];
        if (srcNodeRow.get(w).isEmptyCell()) {
            return false;
        }

        ArrayList<MatrixCell> destNodeRow = adjacencyMatrix[w];
        if (destNodeRow.get(v).isEmptyCell()) {
            return false;
        }

        return true;
    }

    @Override
    public void accept(GraphAlgorithm<N, E> algorithm) {
        algorithm.implement(this);
    }

    class MatrixCell {
        public N data;
        public E weight;

        public MatrixCell(N data, E weight) {
            this.data = data;
            this.weight = weight;
        }

        public MatrixCell() {

        }

        MatrixCell emptyCell() {
            N data = (N) new Object();
            E weight = (E) new Object();

            MatrixCell cell = new MatrixCell(data, weight);
            return cell;
        }

        public boolean isEmptyCell() {
            int checkers = 0;
            MatrixCell emptyCell = emptyCell();

            if (this.data == emptyCell.data) {
                checkers++;
            }

            if (this.weight == emptyCell.weight) {
                checkers++;
            }

            if (checkers == 2) {
                return true;
            }

            return false;
        }
    }
}

