package datastructures.graphs;

public class Edge<N, E extends Comparable<E>> {
    private final N src;
    private final N dest;
    private final E weight;

    public Edge(N src, N dest, E weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }

    public N source() {
        return src;
    }

    public N destination() {
        return dest;
    }

    public E weight() {
        return weight;
    }
}