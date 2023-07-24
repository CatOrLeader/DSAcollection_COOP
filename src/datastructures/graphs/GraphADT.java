package datastructures.graphs;

public interface GraphADT<N, E extends Comparable<E>> {
    int nodeCount();
    int edgeCount();
    void printData(int i);
    void setValue(int i, N data);
    void addEdge(int v, int w, E weight);
    E weight(int v, int w);
    void removeEdge(int v, int w);
    boolean hasEdge(int v, int w);
    void accept(GraphVisitor<N, E> visitor);
}
