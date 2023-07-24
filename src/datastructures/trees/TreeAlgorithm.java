package datastructures.trees;

public interface TreeAlgorithm<T extends Comparable<T>> {
    void implement(AVLTree<T> tree);
    void implement(RedBlackTree<T> tree);
}
