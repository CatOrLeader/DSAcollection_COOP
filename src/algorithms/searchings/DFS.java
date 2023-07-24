package algorithms.searchings;

import datastructures.graphs.GraphADT;

import java.util.ArrayList;
import java.util.List;

public class DFS<N, E extends Comparable<E>> {
    public void depthFirstSearchStartVertex(GraphADT<N, E> graph) {
        int numVertices = graph.nodes().size();

        boolean[] visited = new boolean[numVertices];

        int startIndex = 0;
        recursive(graph, startIndex, visited);
    }

    private void recursive(GraphADT<N, E> graph, int currentIndex, boolean[] visited) {
        visited[currentIndex] = true;
        System.out.print(currentIndex + " ");

        ArrayList<N> nodes = graph.nodes();
        N curNode = nodes.get(currentIndex);

        List<N> adjacent = graph.adjacentNodes(curNode);

        for (N vertex : adjacent) {
            int vertexIndex = nodes.indexOf(vertex);
            if (!visited[vertexIndex]) {
                recursive(graph, vertexIndex, visited);
            }
        }
    }
}
