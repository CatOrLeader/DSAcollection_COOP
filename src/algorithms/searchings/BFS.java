package algorithms.searchings;

import datastructures.graphs.GraphADT;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BFS<N, E extends Comparable<E>> {
    public void breadthFirstSearchStartVertex(GraphADT<N, E> graph) {
        int numVertices = graph.nodeCount();
        ArrayList<N> nodes = graph.nodes();

        boolean[] visited = new boolean[numVertices];

        Queue<N> queue = new LinkedList<>();

        int startVertex = 0;
        visited[startVertex] = true;
        queue.add(nodes.get(startVertex));

        while (!queue.isEmpty()) {
            N currentNode = queue.poll();
            int currentIndex = nodes.indexOf(currentNode);

            System.out.print(currentIndex + " ");

            List<N> adjacent = graph.adjacentNodes(currentNode);

            for (N vertex : adjacent) {
                int vertexIndex = nodes.indexOf(vertex);
                if (!visited[vertexIndex]) {
                    visited[vertexIndex] = true;
                    queue.add(vertex);
                }
            }
        }
    }

    public void breadthFirstSearch(GraphADT<N, E> graph, N node) {
        int numVertices = graph.nodeCount();
        ArrayList<N> nodes = graph.nodes();

        boolean[] visited = new boolean[numVertices];

        Queue<N> queue = new LinkedList<>();

        int startVertex = nodes.indexOf(node);
        visited[startVertex] = true;
        queue.add(nodes.get(startVertex));

        while (!queue.isEmpty()) {
            N currentNode = queue.poll();
            int currentIndex = nodes.indexOf(currentNode);

            System.out.println(currentIndex + " ");

            List<N> adjacent = graph.adjacentNodes(currentNode);

            for (N vertex : adjacent) {
                int vertexIndex = nodes.indexOf(vertex);
                if (!visited[vertexIndex]) {
                    visited[vertexIndex] = true;
                    queue.add(vertex);
                }
            }
        }
    }
}
