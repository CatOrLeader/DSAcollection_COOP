package datastructures.graphs;

import org.w3c.dom.Node;

import java.util.ArrayList;

public interface GraphADT<N, E extends Comparable<E>> {
    ArrayList<N> nodes();
    ArrayList<N> adjacentNodes(N node);
    void addNode(N node);
    void addNodes(ArrayList<N> nodes);
    int nodeCount();
    int edgeCount();
    void printData(int i);
    void setValue(int i, N data);
    void addEdge(int v, int w, E weight);
    E weight(int v, int w);
    void removeEdge(int v, int w);
    boolean hasEdge(int v, int w);
    void accept(GraphAlgorithm<N, E> algorithm);
}
