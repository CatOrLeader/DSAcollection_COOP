package datastructures.graphs;

import algorithms.searchings.DFS;

public class DFSAlgorithm<N, E extends Comparable<E>>
        implements GraphAlgorithm<N, E>{
    @Override
    public void implement(GraphADT<N, E> graph) {
        DFS<N, E> dfs = new DFS<>();
        dfs.depthFirstSearchStartVertex(graph);
    }
}