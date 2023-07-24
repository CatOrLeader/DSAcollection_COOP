package datastructures.graphs;

public interface GraphVisitor<N, E extends Comparable<E>> {
    void visit(GraphADT<N, E> graph);
}
