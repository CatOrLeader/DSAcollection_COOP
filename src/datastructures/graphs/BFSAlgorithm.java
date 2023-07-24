package datastructures.graphs;

import algorithms.searchings.BFS;

public class BFSAlgorithm<N, E extends Comparable<E>>
        implements GraphAlgorithm<N, E>{
    @Override
    public void implement(GraphADT<N, E> graph) {
        BFS<N, E> bfs = new BFS<>();
        bfs.breadthFirstSearchStartVertex(graph);
    }
}
